/*
 * 文件名：PropertiesConstant.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.constant;

public class PropertiesConstant {
	
	public static PropertiesConstant constant = null;
	
	static {
		constant = new PropertiesConstant();
	}
	
	private int totalRows17k;
	
	private int pageSize17k;

	/**
	 * @return Returns the totalRows17k.
	 */
	public int getTotalRows17k() {
		return totalRows17k;
	}

	/**
	 * @param totalRows17k The totalRows17k to set.
	 */
	public void setTotalRows17k(int totalRows17k) {
		this.totalRows17k = totalRows17k;
	}

	/**
	 * @return Returns the pageSize17k.
	 */
	public int getPageSize17k() {
		return pageSize17k;
	}

	/**
	 * @param pageSize17k The pageSize17k to set.
	 */
	public void setPageSize17k(int pageSize17k) {
		this.pageSize17k = pageSize17k;
	}
	
}
