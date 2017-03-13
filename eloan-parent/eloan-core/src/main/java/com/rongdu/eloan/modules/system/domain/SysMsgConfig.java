package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;

/**
 * 模块模板关系实体
 * 
 * @author wujing
 * @version 1.0
 * @since 2014-04-21
 */
public class SysMsgConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 通知模块ID
	 */
	public Long msgModuleId;
	/**
	 * 通知模板ID
	 */
	private Long msgTemplateId;
	/**
	 * 是否启用:0禁用,1启用
	 */
	private Integer isUse;
	/**
	 * 发放方式:0自动，2手动
	 */
	private Byte sendType;

	private Byte type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMsgModuleId() {
		return msgModuleId;
	}

	public void setMsgModuleId(Long msgModuleId) {
		this.msgModuleId = msgModuleId;
	}

	/**
	 * 获取通知模板ID
	 * 
	 * @return 通知模板ID
	 */

	/**
	 * 获取是否启用:1：启用，2：禁用
	 * 
	 * @return 是否启用:1：启用，2：禁用
	 */
	public Integer getIsUse() {
		return isUse;
	}

	/**
	 * @return the msgTemplateId
	 */

	/**
	 * 设置是否启用:1：启用，2：禁用
	 * 
	 * @param isUse 要设置的是否启用:1：启用，2：禁用
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Long getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(Long msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

	/**
	 * 获取发放方式:0自动，2手动
	 * 
	 * @return 发放方式:0自动，2手动
	 */
	public Byte getSendType() {
		return sendType;
	}

	/**
	 * 设置发放方式:0自动，2手动
	 * 
	 * @param sendType 要设置的发放方式:0自动，2手动
	 */
	public void setSendType(Byte sendType) {
		this.sendType = sendType;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

}
