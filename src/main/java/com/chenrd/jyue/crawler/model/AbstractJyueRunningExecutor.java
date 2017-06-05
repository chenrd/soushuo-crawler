/*
 * 文件名：AbstractBookCrawler.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import com.chenrd.jyue.crawler.JyueRunningExecutor;
import com.chenrd.jyue.crawler.db.BookDbService;
import com.chenrd.jyue.crawler.db.impl.BookDbServiceImpl;
import com.chenrd.jyue.crawler.example.BookCrawler17K;
import com.chenrd.jyue.crawler.feeder.BookCrawlerGenerator;
import com.chenrd.jyue.crawler.feeder.BookCrawlerItemFeeder;
import com.chenrd.jyue.crawler.feeder.BookCrawlerItemQueue;
import com.chenrd.jyue.crawler.feeder.BookCrawlerItemQueue.BookCrawlerItem;

/**
 * 
 * @author chenrd
 * @version 2017年4月20日
 * @see AbstractJyueRunningExecutor
 * @since
 */
public class AbstractJyueRunningExecutor implements JyueRunningExecutor {

	protected ExecutorService executor;
	
	private BookCrawlerItemFeeder feeder;
	
	private BookDbService bookDbService = null;
	
	public void init() {
		feeder = new BookCrawlerItemFeeder(new BookCrawlerItemQueue());
		feeder.start();
		
		BookCrawlerGenerator bookCrawlerGenerator = new BookCrawlerGenerator(new AtomicInteger(0), 1);
		bookDbService = new BookDbServiceImpl().init();
		
		feeder.setBookCrawlerGenerator(bookCrawlerGenerator);
		
		BookCrawler17K bookCrawler17K = new BookCrawler17K("crawl-17k-1", true, bookCrawlerGenerator, bookDbService);
		bookCrawler17K.init();
		bookCrawlerGenerator.addCrawler(bookCrawler17K);
		
	}
	
	public void start() throws InterruptedException {
		while (feeder.running) {
			BookCrawlerItem item = feeder.next();
			if (item != null) {
				executor.execute(item);
			}
		}
		executor.execute(bookDbService);
		if (bookDbService.isRunning()) {
			executor.shutdown();
		}
	}

}
