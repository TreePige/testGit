package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构账号用户数表实体
 * @author yaoyy
 * @version 1.0
 * @since 2015-12-24
 */
public class SysOfficeUserCount implements Serializable{

	private static final long serialVersionUID = -3040745247486826430L;
	
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 机构ID
	 */
	private Long officeId;
	/**
	 * 机构账号
	 */
	private String officeNumber;
	/**
	 * 机构用户数
	 */
	private Integer userCount;
	
	/**
	 * 推送时间
	 */
	private Date pushTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	
}
