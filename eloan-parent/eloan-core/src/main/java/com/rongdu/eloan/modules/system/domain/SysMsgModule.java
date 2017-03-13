package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;

/**
 * 模块信息实体
 * 
 * @author wujing
 * @version 1.0
 * @since 2014-04-21
 */
public class SysMsgModule implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 模块编码
	 */
	private String nid;
	/**
	 * 模块名
	 */
	private String name;
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
	 * 获取模块编码
	 * 
	 * @return 模块编码
	 */
	public String getNid() {
		return nid;
	}

	/**
	 * 设置模块编码
	 * 
	 * @param nid 要设置的模块编码
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

	/**
	 * 获取模块名
	 * 
	 * @return 模块名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置模块名
	 * 
	 * @param name 要设置的模块名
	 */
	public void setName(String name) {
		this.name = name;
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
