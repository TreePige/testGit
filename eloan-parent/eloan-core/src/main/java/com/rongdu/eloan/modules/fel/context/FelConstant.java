/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.context;

import java.math.RoundingMode;

/**
 *	@Description 公式相关的常量定义
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年3月8日 下午4:13:18
 *  @since version 1.0.0
 */
public class FelConstant {
	public static final RoundingMode FEL_ROUNDINGMODE = RoundingMode.CEILING;
	
	public static final int FEL_SCALE = 2;
	/**********************************删除状态*****************************************/
	public static final int DELETE = 1;
	public static final int UNDELETE = 0;
	
	/**********************************参数来源*****************************************/
	public static final String DATA_SOURCE_FEL = "fel"; 
	
	public static final String DATA_SOURCE_SYS = "sys";
	
	/**************************************参数类型定义***********************************/
	public static final String DOUBLE = "double";
	
	public static final String BIGDECIMAL = "bigDecimal";
	
	public static final String INT = "int";
	
	public static final String LONG = "long";
	
	/*****************************************公式类型**********************************************/
	
	/**
	 * 月还金额
	 */
	public static final String MONTH_REPAY = "monthRepay";
	
	/**
	 * 总换金额
	 */
	public static final String AMOUNT_REPAY = "amountRepay";
	
	/**
	 * 提前还款违约金
	 */
	public static final String PREPAYMENT_PENALTY = "prepaymentPenalty";
	
	/**
	 * 月利息
	 */
	public static final String MONTHLY_INTEREST = "monthlyInterest";
	
	/**
	 * 服务费
	 */
	public static final String SERVICE_CHARGE = "serviceCharge";
	
	/**
	 * 咨询费
	 */
	public static final String CONSULT_CHARGE = "consultCharge";
	
	/**
	 * 逾期违约金
	 */
	public static final String OVERDUE_PENALTY = "overduePenalty";
	
	/**
	 * 罚息
	 */
	public static final String PENALTY = "penalty";
	
	/**
	 * 放款金额
	 */
	public static final String LOANED_MONEY = "loanedMoney";
	
	/**
	 * 前中期费用
	 */
	public static final String MIDTERM_FEE = "midTermFee";
	
	/***********************************************参数名********************************************/
	
	/**
	 * 逾期天数
	 */
	public static final String OVERDUE_DATE = "overdue_date";
	
	/**
	 * 已还期数
	 */
	public static final String HAVEPAY_PERIOD = "havepay_period";	
	
	/**
	 * 逾期期数
	 */
	public static final String OVERDUE_PERIOD = "overdue_period";
}
