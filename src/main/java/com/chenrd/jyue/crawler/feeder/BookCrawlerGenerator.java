/*
 * 文件名：TaskItemGenerator.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.feeder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.chenrd.jyue.crawler.BookCrawler;
import com.chenrd.jyue.crawler.feeder.BookCrawlerItemQueue.BookCrawlerItem;

public class BookCrawlerGenerator {
	
	private Object lock = new Object();
	
	private List<BookCrawler> list = Collections.synchronizedList(new ArrayList<BookCrawler>());
	
	AtomicInteger doneNumber;
	
	int totalNumber;
	
	/**
	 * @param doneNumber
	 */
	public BookCrawlerGenerator(AtomicInteger doneNumber, int totalNumber) {
		super();
		this.doneNumber = doneNumber;
		this.totalNumber = totalNumber;
	}
	
	/**
	 * 是否全部爬虫结束
	 * 
	 * @return 
	 * @see
	 */
	public boolean isEnd() {
		return doneNumber.get() >= totalNumber;
	}

	public void addCrawler(BookCrawler bookCrawler) {
		synchronized (lock) {
			list.add(bookCrawler);
		}
	}
	
	public BookCrawlerItem next() {
		BookCrawler crawler = null;
		synchronized (lock) {
			if (list.size() > 0) {
				crawler = list.remove(0);
			}
		}
		
		if (crawler != null) {
			return new BookCrawlerItem(crawler, doneNumber);
		}
		return null;
	}
}
