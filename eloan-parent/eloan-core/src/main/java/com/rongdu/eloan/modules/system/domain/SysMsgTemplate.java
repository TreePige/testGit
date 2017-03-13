package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;

/**
 * 消息模板实体
 * 
 * @author wujing
 * @version 1.0
 * @since 2014-04-21
 */
public class SysMsgTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 模板名
	 */
	private String name;
	/**
	 * 模板类型:1：短信，2：邮件
	 */
	private Byte type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 模板内容
	 */
	private String content;
	/**
	 * 是否启用:0禁用，1启用
	 */
	private Byte isUse;
	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取模板名
	 * 
	 * @return 模板名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置模板名
	 * 
	 * @param name 要设置的模板名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取模板类型:1：短信，2：邮件
	 * 
	 * @return 模板类型:1：短信，2：邮件
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 设置模板类型:1：短信，2：邮件
	 * 
	 * @param type 要设置的模板类型:1：短信，2：邮件
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title 要设置的标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取模板内容
	 * 
	 * @return 模板内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置模板内容
	 * 
	 * @param content 要设置的模板内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取是否启用::0禁用，1启用
	 * 
	 * @return 是否启用::0禁用，1启用
	 */
	public Byte getIsUse() {
		return isUse;
	}

	/**
	 * 设置是否启用::0禁用，1启用
	 * 
	 * @param isUse 要设置的是否启用::0禁用，1启用
	 */
	public void setIsUse(Byte isUse) {
		this.isUse = isUse;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark 要设置的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
