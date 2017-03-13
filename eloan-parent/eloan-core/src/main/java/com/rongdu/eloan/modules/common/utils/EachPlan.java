package com.rongdu.eloan.modules.common.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 每期还款计划
 * 
 * @author：xx
 * @version 1.0
 * @since 2014年7月15日
 */
public interface EachPlan {


	/**
	 * 获取本金
	 * 
	 * @return 本金
	 */
	public double getCapital() ;

	/**
	 * 设置本金
	 * 
	 * @param capital 要设置的本金
	 */
	public void setCapital(double capital);

	/**
	 * 获取利息
	 * 
	 * @return 利息
	 */
	public double getInterest() ;

	/**
	 * 设置利息
	 * 
	 * @param interest 要设置的利息
	 */
	public void setInterest(double interest) ;
	/**
	 * 获取净收利息（扣除管理费后）
	 * 
	 * @return 净收利息（扣除管理费后）
	 */
	public double getNetInterest() ;
	

	/**
	 * 设置净收利息（扣除管理费后）
	 * 
	 * @param netInterest 要设置的净收利息（扣除管理费后）
	 */
	public void setNetInterest(double netInterest) ;

	/**
	 * 获取本息
	 * 
	 * @return 本息
	 */
	public double getTotal() ;

	/**
	 * 设置本息
	 * 
	 * @param total 要设置的本息
	 */
	public void setTotal(double total) ;

	/**
	 * 获取净收本息（扣除管理费后）
	 * 
	 * @return 净收本息（扣除管理费后）
	 */
	public double getNetTotal() ;

	/**
	 * 设置净收本息（扣除管理费后）
	 * 
	 * @param netTotal 要设置的净收本息（扣除管理费后）
	 */
	public void setNetTotal(double netTotal);

	/**
	 * 获取未还本息
	 * 
	 * @return 未还本息
	 */
	public double getNeedRepay() ;

	/**
	 * 设置未还本息
	 * 
	 * @param needRepay 要设置的未还本息
	 */
	public void setNeedRepay(double needRepay) ;

	/**
	 * 获取开始计息日
	 * 
	 * @return 开始计息日
	 */
	public Date getInterestTime() ;

	/**
	 * 设置开始计息日
	 * 
	 * @param interestTime 要设置的开始计息日
	 */
	public void setInterestTime(Date interestTime) ;

	/**
	 * 获取还款日
	 * 
	 * @return 还款日
	 */
	public Date getRepayTime() ;

	/**
	 * 设置还款日
	 * 
	 * @param repayTime 要设置的还款日
	 */
	public void setRepayTime(Date repayTime) ;
	/**
     * 获取服务费
     * 
     * @return 服务费
     */
	public BigDecimal getServiceFee();
	/**
     * 设置服务费
     * 
     * @param serviceFee 要设置的服务费
     */
	public void setServiceFee(BigDecimal serviceFee);
	 /**
     * 获取停车费
     * 
     * @return 停车费
     */
	public BigDecimal getParkingFee() ;
	/**
     * 设置停车费
     * 
     * @param parkingFee 要设置的停车费
     */
    public void setParkingFee(BigDecimal parkingFee) ;
    /**
     * 获取其他费用
     * 
     * @return 其他费用
     */
    public BigDecimal getOtherFee() ;
    /**
     * 设置其他费用
     * 
     * @param otherFee 要设置的其他费用
     */
    public void setOtherFee(BigDecimal otherFee) ;
    /**
     * 获取违约金
     * 
     * @return 违约金
     */
	public BigDecimal getLiquidatedDamages() ;
	/**
     * 设置违约金
     * 
     * @param liquidatedDamages 要设置的违约金
     */
	public void setLiquidatedDamages(BigDecimal liquidatedDamages) ;
}
