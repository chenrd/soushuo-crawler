/*
 * 文件名：BookDetailed.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.dto;

import java.util.Date;

public class BookDetailed {
	
	private Long id; // = Book.id
	private Boolean free; // 是否免费
	private Boolean done; // 是否完结
	private Integer wordNumber; //文字数量
	private String lastChapter; //最后一章
	private Date lastChapterTime;
	private String chapterContent; //章节内容
	
	private Date createTime;
	private Date updateTime;
	
	
	/**
	 * @param free
	 * @param done
	 * @param wordNumber
	 * @param lastChapter
	 * @param lastChapterTime
	 */
	public BookDetailed(Boolean free, Boolean done, Integer wordNumber, String lastChapter, Date lastChapterTime) {
		super();
		this.free = free;
		this.done = done;
		this.wordNumber = wordNumber;
		this.lastChapter = lastChapter;
		this.lastChapterTime = lastChapterTime;
	}
	/**
	 * 
	 */
	public BookDetailed() {
		super();
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the free.
	 */
	public Boolean getFree() {
		return free;
	}
	/**
	 * @param free The free to set.
	 */
	public void setFree(Boolean free) {
		this.free = free;
	}
	/**
	 * @return Returns the done.
	 */
	public Boolean getDone() {
		return done;
	}
	/**
	 * @param done The done to set.
	 */
	public void setDone(Boolean done) {
		this.done = done;
	}
	/**
	 * @return Returns the wordNumber.
	 */
	public Integer getWordNumber() {
		return wordNumber;
	}
	/**
	 * @param wordNumber The wordNumber to set.
	 */
	public void setWordNumber(Integer wordNumber) {
		this.wordNumber = wordNumber;
	}
	/**
	 * @return Returns the lastChapter.
	 */
	public String getLastChapter() {
		return lastChapter;
	}
	/**
	 * @param lastChapter The lastChapter to set.
	 */
	public void setLastChapter(String lastChapter) {
		this.lastChapter = lastChapter;
	}
	/**
	 * @return Returns the lastChapterTime.
	 */
	public Date getLastChapterTime() {
		return lastChapterTime;
	}
	/**
	 * @param lastChapterTime The lastChapterTime to set.
	 */
	public void setLastChapterTime(Date lastChapterTime) {
		this.lastChapterTime = lastChapterTime;
	}
	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return Returns the updateTime.
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime The updateTime to set.
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return Returns the chapterContent.
	 */
	public String getChapterContent() {
		return chapterContent;
	}
	/**
	 * @param chapterContent The chapterContent to set.
	 */
	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}
	
}
