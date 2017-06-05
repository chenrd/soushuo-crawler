/*
 * 文件名：TaskFetcher.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.feeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenrd.jyue.crawler.feeder.BookCrawlerItemQueue.BookCrawlerItem;

/**
 * 任务生产者 采用生产者与消费者模式
 * 
 * @author chenrd
 * @version 2017年4月20日
 * @see BookCrawlerItemFeeder
 * @since
 */
public class BookCrawlerItemFeeder extends Thread {
	
	private BookCrawlerItemQueue bookCrawlerItemQueue;
	
	public boolean running = true;
	
	private Object lock = new Object();
	
	private BookCrawlerGenerator bookCrawlerGenerator;
	
	private static Logger LOG = LoggerFactory.getLogger(BookCrawlerItemFeeder.class);
	
	public BookCrawlerItemFeeder(BookCrawlerItemQueue bookCrawlerItemQueue) {
		this.bookCrawlerItemQueue = bookCrawlerItemQueue;
	}
	
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(1000);
				LOG.debug("循环 BookCrawlerItemFeeder......");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(bookCrawlerGenerator.isEnd()) {
				LOG.debug("构造器已经完成所有的主爬虫任务，开始结束....");
				running = false;
				synchronized(lock) {
					lock.notify();
					LOG.debug("这里释放资源是要结束了....");
				}
			}
			
			synchronized (lock) {
				BookCrawlerItem crawler = bookCrawlerGenerator.next();
				if (crawler != null) {
					bookCrawlerItemQueue.addFetchItem(crawler);
					LOG.debug("feeder仓库循环到Item，调用notify唤醒next");
					lock.notify();
				}
			}
		}
	}
	
	public BookCrawlerItem next() throws InterruptedException {
		synchronized(lock) {
			if (bookCrawlerItemQueue.getSize() == 0) {
				LOG.debug("线程：[{}] 进入Feeder仓库获取Item等待...., time:[{}]", Thread.currentThread().getName(), System.currentTimeMillis());
				lock.wait();
				LOG.debug("线程：[{}] 进入Feeder仓库获取Item被唤醒, time:[{}]", Thread.currentThread().getName(), System.currentTimeMillis());
			}
			return bookCrawlerItemQueue.getFetchItem();
		}
	}

	/**
	 * @param bookCrawlerGenerator The bookCrawlerGenerator to set.
	 */
	public void setBookCrawlerGenerator(BookCrawlerGenerator bookCrawlerGenerator) {
		this.bookCrawlerGenerator = bookCrawlerGenerator;
	}
	
}
