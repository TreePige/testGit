package com.rongdu.eloan.modules.common.utils.databean;

public class BasicDataBean {
	/** 登录用户ID */
	protected Long loginUserId;
	
	/** 登录用户名 */
	protected String userName;
	
	/** 登录用户角色ID */
	protected Long loginUserRoleId;

	/** 所属机构ID */
	protected String officeId;
	
	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	public Long getLoginUserRoleId() {
		return loginUserRoleId;
	}

	public void setLoginUserRoleId(Long loginUserRoleId) {
		this.loginUserRoleId = loginUserRoleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

    @Override
    public String toString() {
        return "BasicDataBean{" +
                "loginUserId=" + loginUserId +
                ", userName='" + userName + '\'' +
                ", loginUserRoleId=" + loginUserRoleId +
                ", officeId='" + officeId + '\'' +
                '}';
    }
}
