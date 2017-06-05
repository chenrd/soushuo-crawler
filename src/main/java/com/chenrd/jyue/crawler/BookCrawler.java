/*
 * 文件名：BookCrawler.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler;

import java.util.concurrent.atomic.AtomicInteger;

public interface BookCrawler {
	
	/**
	 * 主任务完成之后doneNumber自增，目的是记录所有的爬虫都完成任务，关闭系统
	 * 
	 * @param doneNumber 完成度
	 * @throws Exception 
	 * @see
	 */
	void execute(AtomicInteger doneNumber) throws Exception;
	
}
