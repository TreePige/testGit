package com.rongdu.eloan.common.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组装extjs分页的bean
 * 
 * @author zzb
 * 
 */

public class PageBean extends BaseDomain {

	private static final long serialVersionUID = 9112130117774453697L;
	/**
	 * 查询回的数据
	 */
	private static final String DATAS = "datas";

	/**
	 * 总记录数
	 */
	private static final String DATA_COUNT = "dataCount";

	/**
	 * 开始行数
	 */
	private static final String START_ROW = "startRow";

	/**
	 * 每页记录数
	 */
	private static final String PAGE_SIZE = "pageSize";
	/**
	 * 开始记录数据
	 */
	private int start;
	
	private int currentPageNum;

	/**
	 * 每页记录数
	 */

	private int limit;

	private Map<String, Object> arg;

	private Long count;

	private List<? extends Object> data;

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

    public PageBean () {}

	public PageBean(int start, int limit) {

		this.start = start;

		this.limit = limit;

	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Map<String, Object> getArg() {
		return arg;
	}

	public void setArg(Map<String, Object> arg) {
		this.arg = arg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * 查询返回的数据
	 * 
	 * @return
	 */
	public Map<String, Object> resData() {

		Map<String, Object> res = new HashMap<String, Object>();

		res.put(DATAS, this.getData());
		res.put(DATA_COUNT, this.getCount());

		return res;
	}

	/**
	 * 查询请求数据
	 * 
	 * @return
	 */
	public Map<String, Object> reqData() {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put(START_ROW, this.getStart());

		req.put(PAGE_SIZE, this.getLimit());

		if (this.getArg() != null) {
			req.putAll(this.getArg());
		}

		return req;

	}
}
