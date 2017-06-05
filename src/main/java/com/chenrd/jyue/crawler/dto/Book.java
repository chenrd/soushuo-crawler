/*
 * 文件名：Book.java
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

public class Book {
	
	private Long id;
	private String title; // 作品名称
	private String author; // 作者
	private String introduce; //内容介绍
	private int type; //类别
	private int sex; //男生 女生
	private String indexUrl; //主页URL
	private String imgUrl; //图片的链接地址
	
	private String source; // 来源，是从哪里抓取过来的
	private Date createTime;
	
	private BookDetailed detailed;
	
	
	/**
	 * 
	 */
	public Book() {
		super();
	}
	/**
	 * @param id
	 * @param title
	 * @param author
	 * @param introduce
	 * @param type
	 * @param source
	 */
	public Book(String title, String author, String introduce, int type, int sex, String indexUrl, String imgUrl, String source, BookDetailed detailed) {
		super();
		this.title = title;
		this.author = author;
		this.introduce = introduce;
		this.type = type;
		this.sex = sex;
		this.indexUrl = indexUrl;
		this.imgUrl = imgUrl;
		this.source = source;
		this.detailed = detailed;
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
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the author.
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author The author to set.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return Returns the sex.
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @return Returns the introduce.
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * @param introduce The introduce to set.
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return Returns the indexUrl.
	 */
	public String getIndexUrl() {
		return indexUrl;
	}
	/**
	 * @param indexUrl The indexUrl to set.
	 */
	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}
	/**
	 * @return Returns the source.
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source The source to set.
	 */
	public void setSource(String source) {
		this.source = source;
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
	 * @return Returns the detailed.
	 */
	public BookDetailed getDetailed() {
		return detailed;
	}
	/**
	 * @param detailed The detailed to set.
	 */
	public void setDetailed(BookDetailed detailed) {
		this.detailed = detailed;
	}
	/**
	 * @return Returns the imgUrl.
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl The imgUrl to set.
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
