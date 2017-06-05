/*
 * 文件名：PropertiesUtil.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties pro = null;
	
	static {
		try {
			Properties properties = new Properties();
			InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/config.properties");
			properties.load(inputStream);
			inputStream.close();
			pro = properties;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return pro.getProperty(key);
	}
	
	private PropertiesUtil() {
		// TODO Auto-generated constructor stub
	}

}
