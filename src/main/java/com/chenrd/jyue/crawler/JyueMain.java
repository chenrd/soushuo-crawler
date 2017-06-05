/*
 * 文件名：CrawlerSystem.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler;

import com.chenrd.jyue.crawler.db.JDBCHelper;
import com.chenrd.jyue.crawler.model.FixedJueRunningExecutor;

/**
 * 爬虫项目的入口 main函数
 * @author chenrd
 * @version 2017年4月21日
 * @see JyueMain
 * @since
 */
public class JyueMain {

	public static void main(String[] args) throws InterruptedException {
		JDBCHelper.createMysqlTemplate("soushuo",
	            "jdbc:mysql://192.168.2.99:3306/soushuo?useUnicode=true&characterEncoding=utf8",
	            "root", "root@ocean", 1, 5);
		
		JyueRunningExecutor bookCrawler = new FixedJueRunningExecutor();
		bookCrawler.init();
		bookCrawler.start();
	}
}
