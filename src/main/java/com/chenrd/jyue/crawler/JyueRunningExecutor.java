/*
 * 文件名：TaskItemExecutor.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler;

public interface JyueRunningExecutor {
	
	void start() throws InterruptedException;
	
	void init();
}
