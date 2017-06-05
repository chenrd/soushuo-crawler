/*
 * 文件名：CrawlerBook.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 单本书的内容抓取，目前没有用到
 * 
 * @author chenrd
 * @version 2017年4月20日
 * @see BookContentCrawler
 * @since
 */
public class BookContentCrawler extends BreadthCrawler {

	public BookContentCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		
		this.addSeed("http://www.17k.com/chapter/2433637/28218932.html");

	    /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
	    this.addRegex("http://www.17k.com/chapter/2433637/.*\\.html");
	    
	    /*不要爬取jpg|png|gif*/
	    this.addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
	    this.addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.getUrl();
        /*we use jsoup to parse page*/

        /*extract title and content of news by css selector*/
        String title = page.select("h1[itemprop=headline]").first().text();
        String content = page.select("div#chapterContentWapper", 0).text();

        System.out.println("URL:\n" + url);
        System.out.println("title:\n" + title);
        System.out.println("content:\n" + content);

	}

	public static void main(String[] args) throws Exception {
		BookContentCrawler crawler = new BookContentCrawler("crawl", true);
	    crawler.setThreads(1);
	   // crawler.setTopN(100);
	    //crawler.setResumable(true);
	    /*start crawl with depth of 4*/
	    crawler.start(1000);
    }
}
