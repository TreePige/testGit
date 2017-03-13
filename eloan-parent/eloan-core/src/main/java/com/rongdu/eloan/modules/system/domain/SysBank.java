package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司银行账号表实体
 * @author wgc
 * @version 1.0
 * @since 2015-01-22
 */
public class SysBank implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date updateTime;
	private Byte isDelete;
	private String bankCode;//银行代码-国标
	private String bankName;//银行名称
	private String openNet;//开户网点
	private String openName;//开户名
	private String account;//开户账号
	private Byte isLoan;//是否放款用
	private Byte isRepayment;//是否还款用
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Byte getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getOpenNet() {
		return openNet;
	}
	public void setOpenNet(String openNet) {
		this.openNet = openNet;
	}
	public String getOpenName() {
		return openName;
	}
	public void setOpenName(String openName) {
		this.openName = openName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Byte getIsLoan() {
		return isLoan;
	}
	public void setIsLoan(Byte isLoan) {
		this.isLoan = isLoan;
	}
	public Byte getIsRepayment() {
		return isRepayment;
	}
	public void setIsRepayment(Byte isRepayment) {
		this.isRepayment = isRepayment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}