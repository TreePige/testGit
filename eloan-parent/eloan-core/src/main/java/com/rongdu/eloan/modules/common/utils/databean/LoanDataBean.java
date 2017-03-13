package com.rongdu.eloan.modules.common.utils.databean;

import java.math.BigDecimal;
import java.util.Date;

import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;

public class LoanDataBean extends BasicServiceDataBean {
	/**
	 * 放款金额
	 */
	private BigDecimal loanAmount;
	/**
	 * 账号
	 */
	private String bankCardId;
	/**
	 * 银行标识
	 */
	private Integer bankFlag;
	/**
	 * 实际借款金额
	 */
	private BigDecimal realLendAccount;
	/**
	 * 管理费
	 */
	private BigDecimal totalManagementFee;
	/**
	 * 放款时间
	 */
	private Date loanTime;
	/**
	 * 备注
	 */
	private String remark;

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Integer getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(Integer bankFlag) {
		this.bankFlag = bankFlag;
	}

	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public BigDecimal getTotalManagementFee() {
		return totalManagementFee;
	}

	public void setTotalManagementFee(BigDecimal totalManagementFee) {
		this.totalManagementFee = totalManagementFee;
	}

	public BigDecimal getRealLendAccount() {
		return realLendAccount;
	}

	public void setRealLendAccount(BigDecimal realLendAccount) {
		this.realLendAccount = realLendAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
}
