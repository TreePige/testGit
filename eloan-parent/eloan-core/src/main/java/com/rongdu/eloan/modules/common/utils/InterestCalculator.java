package com.rongdu.eloan.modules.common.utils;

import java.util.Date;
import java.util.List;

/**
 * 利息计算接口
 * 
 */
public interface InterestCalculator {
	
	/**
	 * 初始化
	 * @param account 借款金额
	 * @param yearApr 年利率
	 * @param interestTime 开始计息日
	 * @param periods 期数
	 * @param manageFee 年利率管理费
	 */
	void init(double account, double yearApr, Date interestTime, int periods, double manageFee);

	/**
	 * 计算每期还款信息
	 * 
	 * @return 还款计划
	 */
	List<EachPlan> calculator();

	/**
	 * 计算按天计算还款信息
	 * 
	 * @param days 天数
	 * @return 还款计划
	 */
	List<EachPlan> calculator(int days);

	/**
	 * 取得每期还款计划
	 * 
	 * @return 每期还款计划
	 */
	List<EachPlan> getEachPlan();

	/**
	 * 还款总额
	 * 
	 * @return 还款总额
	 */
	double repayTotal();

	/**
	 * 最后一期还款时间
	 * 
	 * @return 最后一期还款时间
	 */
	Date repayTime();

	/**
	 * 还款总期数
	 * 
	 * @return 还款总期数
	 */
	int repayPeriods();

	/**
	 * 还款时间计算公式
	 * 
	 * @param date 投资时间
	 * @return 还款时间
	 */
	Date calculatorRepaytime(Date date);

}
