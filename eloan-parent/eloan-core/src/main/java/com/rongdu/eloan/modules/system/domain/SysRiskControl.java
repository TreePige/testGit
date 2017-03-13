package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 五级风控表实体
 * 
 * @author ccf
 * @version 1.0
 * @since 2015-12-17
 */

public class SysRiskControl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;// id
	private Byte riskControlType;// 风控类型
	private Integer startDay;
	private Integer endDay;
	private Byte riskLevel;// 风险等级
	private String ratingStandard;// 评级标准
	private Byte is_enabled;// 是否启用
	private String remark;// 备注
	private String creator;// 创建者
	private Date createTime;// 创建时间
	private String modifier;// 修改者
	private Date modifyTime;// 修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getRiskControlType() {
		return riskControlType;
	}

	public void setRiskControlType(Byte riskControlType) {
		this.riskControlType = riskControlType;
	}

	public Integer getStartDay() {
		return startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}

	public Byte getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Byte riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRatingStandard() {
		return ratingStandard;
	}

	public void setRatingStandard(String ratingStandard) {
		this.ratingStandard = ratingStandard;
	}

	public Byte getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Byte is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
