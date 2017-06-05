/*
 * 文件名：BookDbServiceImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.db.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.jdbc.core.JdbcTemplate;

import com.chenrd.jyue.crawler.db.BookDbService;
import com.chenrd.jyue.crawler.db.JDBCHelper;
import com.chenrd.jyue.crawler.dto.Book;

public class BookDbServiceImpl implements BookDbService {
	
	private Map<String, Book> bookMaps = Collections.synchronizedMap(new HashMap<String, Book>(400));
	
	private Object lock = new Object();
	
	private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public synchronized void addBook(String key, Book book) {
		bookMaps.put(key, book);
	}
	
	@Override
	public synchronized void addChapterDetail(String key, String chapterContent) {
		Book book = bookMaps.get(key);
		if (book != null) {
			book.getDetailed().setChapterContent(chapterContent);
		}
	}
	
	public BookDbServiceImpl init() {
		jdbcTemplate = JDBCHelper.getJdbcTemplate("soushuo");
		return this;
	}

	@Override
	public void run() {
		synchronized (lock) {
			
			int id = jdbcTemplate.queryForInt("select max(id) from book");
			List<Object[]> list = new ArrayList<Object[]>(bookMaps.size());
			List<Object[]> details = new ArrayList<Object[]>(bookMaps.size());
			
			Object[] objects = null, detail = null;
			Date date = new Date();
			for (Entry<String, Book> entry: bookMaps.entrySet()) {
				objects = new Object[10];
				detail = new Object[9];
				objects[0] = ++id;
				objects[1] = entry.getValue().getTitle();
				objects[2] = entry.getValue().getAuthor();
				objects[3] = entry.getValue().getSource();
				objects[4] = entry.getValue().getIntroduce();
				objects[5] = entry.getValue().getType();
				objects[6] = entry.getValue().getSex();
				objects[7] = entry.getValue().getIndexUrl();
				objects[8] = entry.getValue().getImgUrl();
				objects[9] = date;
				list.add(objects);
				
				detail[0] = id;
				detail[1] = entry.getValue().getDetailed().getFree();
				detail[2] = entry.getValue().getDetailed().getDone();
				detail[3] = entry.getValue().getDetailed().getWordNumber();
				detail[4] = entry.getValue().getDetailed().getLastChapter();
				detail[5] = entry.getValue().getDetailed().getLastChapterTime();
				detail[6] = entry.getValue().getDetailed().getChapterContent();
				detail[7] = date;
				detail[8] = date;
				details.add(detail);
			}
			jdbcTemplate.batchUpdate("insert into book(id,title,author,source,introduce,type,sex,index_url,img_url,create_time) values(?,?,?,?,?,?,?,?,?,?)", list);
			jdbcTemplate.batchUpdate("insert into book_detail(id,free,done,word_number,last_chapter,last_chapter_time,chapter_content,create_time,update_time) values(?,?,?,?,?,?,?,?,?)", details);
			
			atomicBoolean.set(true);
			lock.notify();
		}
	}

	@Override
	public boolean isRunning() {
		if (!atomicBoolean.get()) {
			synchronized(lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}
