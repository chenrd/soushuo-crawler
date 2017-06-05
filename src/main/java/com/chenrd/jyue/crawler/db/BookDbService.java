/*
 * 文件名：BookDbService.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.db;

import com.chenrd.jyue.crawler.dto.Book;

public interface BookDbService extends Runnable {
	
	void addBook(String key, Book book);
	
	void addChapterDetail(String key, String chapterContent);
	
	boolean isRunning();
}
