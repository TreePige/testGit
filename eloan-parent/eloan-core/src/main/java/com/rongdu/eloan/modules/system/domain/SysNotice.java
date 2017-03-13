package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告表实体
 * @author wgc
 * @version 1.0
 * @since 2014-12-09
 */
public class SysNotice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date addTime;
    /**
     * 创建者
     */
    private Long addId;
    /**
     * 部门ID
     */
    private Long officeId;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 是否删除 0正常;-1删除
     */
    private Byte isDelete;
    /**发布时间*/
    private Date sendTime;
    /**是否发布*/
    private Byte isSend;
    
    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Long getId(){
        return id;
    }
    
    /**
     * 设置主键
     * 
     * @param id 要设置的主键
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取标题
     * 
     * @return 标题
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * 设置标题
     * 
     * @param title 要设置的标题
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 获取公告内容
     * 
     * @return 公告内容
     */
    public String getContent(){
        return content;
    }
    
    /**
     * 设置公告内容
     * 
     * @param content 要设置的公告内容
     */
    public void setContent(String content){
        this.content = content;
    }
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getAddTime(){
        return addTime;
    }
    
    /**
     * 设置创建时间
     * 
     * @param addTime 要设置的创建时间
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获取创建者
     * 
     * @return 创建者
     */
    public Long getAddId(){
        return addId;
    }
    
    /**
     * 设置创建者
     * 
     * @param addId 要设置的创建者
     */
    public void setAddId(Long addId){
        this.addId = addId;
    }
    /**
     * 获取部门ID
     * 
     * @return 部门ID
     */
    public Long getOfficeId(){
        return officeId;
    }
    
    /**
     * 设置部门ID
     * 
     * @param officeId 要设置的部门ID
     */
    public void setOfficeId(Long officeId){
        this.officeId = officeId;
    }
    /**
     * 获取公司ID
     * 
     * @return 公司ID
     */
    public Long getCompanyId(){
        return companyId;
    }
    
    /**
     * 设置公司ID
     * 
     * @param companyId 要设置的公司ID
     */
    public void setCompanyId(Long companyId){
        this.companyId = companyId;
    }
    /**
     * 获取是否删除 0正常;-1删除
     * 
     * @return 是否删除 0正常;-1删除
     */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置是否删除 0正常;-1删除
     * 
     * @param isDelete 要设置的是否删除 0正常;-1删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Byte getIsSend() {
		return isSend;
	}

	public void setIsSend(Byte isSend) {
		this.isSend = isSend;
	}
    
    
}


