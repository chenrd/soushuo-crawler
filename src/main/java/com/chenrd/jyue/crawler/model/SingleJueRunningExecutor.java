/*
 * 文件名：SingleThreadCrawler.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.model;

import java.util.concurrent.Executors;

/**
 * 单线程抓取，所有需要抓取的网站一个一个轮流抓取，计算机配置一般的用这个实现抓取
 * 
 * @author chenrd
 * @version 2017年4月20日
 * @see SingleJueRunningExecutor
 * @since
 */
public class SingleJueRunningExecutor extends AbstractJyueRunningExecutor {

	public void init() {
		super.init();
		executor = Executors.newSingleThreadExecutor();
	}
}
