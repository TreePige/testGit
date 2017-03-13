/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.context;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年3月19日 下午3:31:19
 *  @since version 1.0.0
 */
public enum FelParamEnum {
	/**
	 * 逾期天数
	 */
	OVERDUE_DATE(FelConstant.OVERDUE_DATE),
	
	/**
	 * 已还期数
	 */
	HAVEPAY_PERIOD(FelConstant.HAVEPAY_PERIOD),
	
	/**
	 * 逾期期数
	 */
	OVERDUE_PERIOD(FelConstant.OVERDUE_PERIOD);
	
	private String oldMode;
	
	private FelParamEnum(String oldMode){
		this.oldMode = oldMode;
	}
	
	public String value(){
		return this.oldMode;
	}
}