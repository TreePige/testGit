package com.rongdu.eloan.modules.common.domain;

/**
 * 经过分页的结果
 * @author FHJ
 *
 */
public class PagedResult {
	private long total;
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
