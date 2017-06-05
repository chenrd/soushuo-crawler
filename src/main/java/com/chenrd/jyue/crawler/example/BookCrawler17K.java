/*
 * 文件名：BookCrawler17K.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenrd.jyue.crawler.BookCrawler;
import com.chenrd.jyue.crawler.constant.BookType;
import com.chenrd.jyue.crawler.db.BookDbService;
import com.chenrd.jyue.crawler.dto.Book;
import com.chenrd.jyue.crawler.dto.BookDetailed;
import com.chenrd.jyue.crawler.feeder.BookCrawlerGenerator;
import com.chenrd.jyue.crawler.util.PropertiesUtil;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 采用类继承实现适配器模式
 * 17K书城的抓取
 * 
 * @author chenrd
 * @version 2017年4月20日
 * @see BookCrawler17K
 * @since
 */
public class BookCrawler17K extends BreadthCrawler implements BookCrawler {
	
	private static Logger LOG = LoggerFactory.getLogger(BookCrawler17K.class);
	
	private static Object lock = new Object();
	
	private int reFailNumber = 4; //失败重复次数
	
	private boolean first = true;
	
	private BookCrawler17K crawler17k; //失败之后的处理
	
	private BookDbService bookDbService;
	
	
	private int threads;
	
	private int totalRows;
	
	private int topN = -1;
	
	private List<String> links = Collections.synchronizedList(new ArrayList<String>());
	
	private BookCrawlerGenerator bookCrawlerGenerator;
	
	public BookCrawler17K(String crawlPath, boolean autoParse, BookCrawlerGenerator bookCrawlerGenerator, BookDbService bookDbService) {
		super(crawlPath, autoParse);
		this.bookCrawlerGenerator = bookCrawlerGenerator;
		this.bookDbService = bookDbService;
		
	    this.addRegex("http://www.17k.com/book/.*\\.html");
	    this.addRegex("-" + PropertiesUtil.getProperty("17k.no.crawler.href"));
	    this.addRegex("-.*\\.(jpg|png|gif).*");
	    this.addRegex("-.*#.*");
	}
	
	/*@Override
	public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
	   HttpRequest request = new HttpRequest(crawlDatum);
	   Proxys proxys=new Proxys();
	   proxys.add("113.5.80.91", 8080);
	   proxys.add(ip, port);
	   proxys.add(ip, port);
	   proxys.add(ip, port);
	   proxys.add(ip, port);
	   request.setProxy(proxys.nextRandom());
	   return request.getResponse();
	}*/
	
	public void init() {
		crawler17k = new BookCrawler17K("17k-Crawler-" + (5 - reFailNumber), true, bookCrawlerGenerator, bookDbService);
		crawler17k.first = false;
		crawler17k.reFailNumber = reFailNumber - 1;
		
		if (first) {
			totalRows = Integer.parseInt(PropertiesUtil.getProperty("17k.total.rows"));
			threads = Integer.parseInt(PropertiesUtil.getProperty("17k.first.threads"));
			for (int i = 1; i <= totalRows; i++) {
				this.addSeed("http://all.17k.com/lib/book/2_0_0_0_0_0_0_0_" + i + ".html");
			}
		} else {
			threads = Integer.parseInt(PropertiesUtil.getProperty("17k.fail.threads"));
		}
		for (int i = 0; i < links.size(); i++) {
			this.addSeed(links.get(i));
		}
		this.setThreads(threads);
	    this.setTopN(topN);
	}
	
	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.getUrl();
		if (page.matchUrl("http://www.17k.com/book/.*\\.html")) {
			try {
	        	String s1 = page.select("div[class=BookInfo]").select("h1").first().select("a").first().text();
	        	Elements elements = page.select("div[class=Main]").select("div[class=label]>a");
	        	String s2 = "连载小说", s3 = "免费小说";
	        	if (elements != null) {
	        		if (elements.size() == 1) {
	        			String str = elements.get(0).select("span").first().text();
	        			if (!"连载小说".equals(str) && !"完本小说".equals(str)) {
	        				s3 = str;
	        			} else {
	        				s2 = str;
	        			}
					}
	        		if (elements.size() > 1) {
	        			s2 = elements.get(0).select("span").first().text();
	        			s3 = elements.get(1).select("span").first().text();
	        		}
	        	}
		        
		        String s4 = page.select("div[class=AuthorInfo]>div[class=author]>a[class=name]").first().text();
		        String s5 = page.select("div[class=cont]>a").first().text();
		        String s6 = page.select("div[class=Main]").select("table>tbody>tr").first().select("td").get(1).text();
		        
		        String s7 = page.select("div[class=BookData]>p").get(1).select("em").first().text();
		        String s8 = page.select("dl[class=NewsChapter]>dd>ul>li>a").first().text();
		        String s9 = page.select("dl[class=NewsChapter]>dd>ul>li>span").first().text();
		        
		        String sex = page.select("div[class=infoPath]").select("a").get(1).text();
		        String imgUrl = page.select("div[class=Props]>div[class=cover]").select("img").first().attr("src");
		        
		        String href = page.select("div[class=Props]>dl[class=Bar]>dt>a").first().attr("href");
		        
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		        
		        BookDetailed detailed = new BookDetailed("完本小说".equals(s2), "免费小说".equals(s3), Integer.parseInt(s7.trim()), s8, format.parse(s9.substring(6)));
		        Book book = new Book(s1, s4, s5, BookType.getTypeIndex(s6.trim()), "男频".equals(sex) ? 1 : 2, page.getUrl(), imgUrl, "17k", detailed);
		        
		        bookDbService.addBook(href.substring(href.lastIndexOf("/") + 1, href.length() - 5), book);
		        crawler17k.links.add(href);
	        } catch (Exception e) {
				LOG.debug("出错了，线程：[{}]", Thread.currentThread().getName(), e);
				crawler17k.links.add(page.getUrl());
			}
		} else if (page.matchUrl("http://www.17k.com/list/.*\\.html")) {
			Elements elements = page.select("div[class=Main List]>dl[class=Volume]");
			Elements els = null;
			JSONObject json = null;
			JSONArray array = new JSONArray();
			for (int i = 0; i < elements.size(); i++) {
				els = elements.get(i).select("dd>a");
				for (Element element : els) {
					json = new JSONObject();
					json.put("href", element.attr("href"));
					json.put("title", element.attr("title"));
					json.put("text", element.text());
					array.put(json);
				}
			}
			bookDbService.addChapterDetail(url.substring(url.lastIndexOf("/") + 1, url.length() - 5), array.toString());
		}
        
	}
	
	public void execute(AtomicInteger doneNumber) throws Exception {
		LOG.debug("17k爬虫：[{}]，线程：[{}] 开始拉取数据", "17k-Crawler-" + (5 - reFailNumber), Thread.currentThread().getName());
		this.start(first ? 2 : 1);
		
		if (crawler17k.links.size() > 0 && reFailNumber > 0) {
			crawler17k.init();
			bookCrawlerGenerator.addCrawler(crawler17k);
			if (first) {
				synchronized (lock) {
					LOG.debug("17k爬虫：[{}]，线程：[{}] 进入等待子任务全部完成", "17k-Crawler-" + (5 - reFailNumber), Thread.currentThread().getName());
					lock.wait();
					LOG.debug("17k爬虫：[{}]，线程：[{}] 主爬虫被唤醒，17K抓取完成！", "17k-Crawler-" + (5 - reFailNumber), Thread.currentThread().getName());
					doneNumber.incrementAndGet();
				}
			}
		}
		if (crawler17k.links.size() == 0 || reFailNumber == 0) {
			synchronized (lock) {
				lock.notify();
				if (first) {
					doneNumber.incrementAndGet();
				}
				LOG.debug("17k爬虫：[{}]，线程：[{}] 唤醒等待的线程", "17k-Crawler-" + (5 - reFailNumber), Thread.currentThread().getName());
			}
		}
	}
}
