package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 预警设置表实体
 * 
 * @author htz
 * @version 1.0
 * @since 2015-12-10
 */
public class SysAlarmSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long customerId;
	private String creator;
	private Date createTime;// 创建时间
	private String modifier;
	private Date modifyTime;
	private Byte isDelete;
//	private String serialNumber;// 序号
	private Byte earlyWarningCategory;// 预警类型
	private String	earlyWarningContent;// 预警内容
	private Byte earlyWarningPeriod;// 预警周期
	private Byte riskLevel;// 风险等级
	private Byte processingMode;// 处理方式
	private Byte inform;// 通知给
	private String remark;// 备注
	private Integer days;//	剩余(逾期)时间
	private Byte level;// 等级	(1..  2..  3..)
	private Byte isEnabled;// 是否启用		(0-启用 1-未启用)
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

//	public String getSerialNumber() {
//		return serialNumber;
//	}

//	public void setSerialNumber(String serialNumber) {
//		this.serialNumber = serialNumber;
//	}

	public Byte getEarlyWarningCategory() {
		return earlyWarningCategory;
	}

	public void setEarlyWarningCategory(Byte earlyWarningCategory) {
		this.earlyWarningCategory = earlyWarningCategory;
	}

	public String getEarlyWarningContent() {
		return earlyWarningContent;
	}

	public void setEarlyWarningContent(String earlyWarningContent) {
		this.earlyWarningContent = earlyWarningContent;
	}

	public Byte getEarlyWarningPeriod() {
		return earlyWarningPeriod;
	}

	public void setEarlyWarningPeriod(Byte earlyWarningPeriod) {
		this.earlyWarningPeriod = earlyWarningPeriod;
	}

	public Byte getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Byte riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Byte getProcessingMode() {
		return processingMode;
	}

	public void setProcessingMode(Byte processingMode) {
		this.processingMode = processingMode;
	}

	public Byte getInform() {
		return inform;
	}

	public void setInform(Byte inform) {
		this.inform = inform;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Byte getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Byte isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
	

}
