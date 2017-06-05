/*
 * 文件名：JDBCHelper.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.db;

import java.util.HashMap;
import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCHelper {

	public static HashMap<String, JdbcTemplate> templateMap = new HashMap<String, JdbcTemplate>();

	public static JdbcTemplate createMysqlTemplate(String templateName, 
        String url, String username, String password, 
        int initialSize, int maxActive) {

	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl(url);
	    dataSource.setUsername(username);
	    dataSource.setPassword(password);
	    dataSource.setInitialSize(initialSize);
	    dataSource.setMaxActive(maxActive);
	    JdbcTemplate template = new JdbcTemplate(dataSource);
	    templateMap.put(templateName, template);
	    return template;
	}

	public static JdbcTemplate getJdbcTemplate(String templateName){
	    return templateMap.get(templateName);
	}

}
