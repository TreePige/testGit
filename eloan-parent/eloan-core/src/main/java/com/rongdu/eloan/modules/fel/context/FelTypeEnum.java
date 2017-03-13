/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.context;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年3月19日 下午5:09:07
 *  @since version 1.0.0
 */
public enum FelTypeEnum {
	/**
	 * 月还金额
	 */
	MONTH_REPAY(FelConstant.MONTH_REPAY),
	
	/**
	 * 总换金额
	 */
	AMOUNT_REPAY(FelConstant.AMOUNT_REPAY),
	
	/**
	 * 提前还款违约金
	 */
	PREPAYMENT_PENALTY(FelConstant.PREPAYMENT_PENALTY),
	
	/**
	 * 月利息
	 */
	MONTHLY_INTEREST(FelConstant.MONTHLY_INTEREST),
	
	/**
	 * 服务费
	 */
	SERVICE_CHARGE(FelConstant.SERVICE_CHARGE),
	
	/**
	 * 咨询费
	 */
	CONSULT_CHARGE(FelConstant.CONSULT_CHARGE),
	
	/**
	 * 逾期违约金
	 */
	OVERDUE_PENALTY(FelConstant.OVERDUE_PENALTY),
	
	/**
	 * 罚息
	 */
	PENALTY(FelConstant.PENALTY),
	
	/**
	 * 前中期费用
	 */
	MIDTERM_FEE(FelConstant.MIDTERM_FEE),
	
	/**
	 * 放款金额
	 */
	LOANED_MONEY(FelConstant.LOANED_MONEY);
	
	private String value;
	
	private FelTypeEnum(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
}
