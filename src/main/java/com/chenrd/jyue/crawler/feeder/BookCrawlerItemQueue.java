/*
 * 文件名：TaskFetcherQueue.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.feeder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenrd.jyue.crawler.BookCrawler;

public class BookCrawlerItemQueue {
	
	private static Logger LOG = LoggerFactory.getLogger(BookCrawlerItemQueue.class);
	
	public AtomicInteger totalSize = new AtomicInteger(0);

    public final List<BookCrawlerItem> queue = Collections.synchronizedList(new LinkedList<BookCrawlerItem>());
    
    public void clear() {
        queue.clear();
    }

    public int getSize() {
        return queue.size();
    }

    public synchronized void addFetchItem(BookCrawlerItem item) {
        if (item == null) {
            return;
        }
        queue.add(item);
        totalSize.incrementAndGet();
    }

    public synchronized BookCrawlerItem getFetchItem() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }
    
    public static class BookCrawlerItem extends Thread {
    	
    	private BookCrawler crawler;
    	
    	private AtomicInteger doneNumber;
    	
    	public BookCrawlerItem(BookCrawler crawler, AtomicInteger doneNumber) {
    		this.crawler = crawler;
    		this.doneNumber = doneNumber;
    	}
    	
    	@Override
    	public void run() {
    		try {
    			LOG.debug("============= 开始爬虫 thread-id=[" + Thread.currentThread().getName() + "]" + crawler.getClass().getSimpleName() + " ===========");
				crawler.execute(doneNumber);
				LOG.debug("============= 结束爬虫 thread-id=[" + Thread.currentThread().getName() + "]" + crawler.getClass().getSimpleName() + " ===========");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
