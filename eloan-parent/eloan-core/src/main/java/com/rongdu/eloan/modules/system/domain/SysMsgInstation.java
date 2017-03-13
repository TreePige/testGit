package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信表实体
 * @author wgc
 * @version 1.0
 * @since 2014-12-09
 */
public class SysMsgInstation implements Serializable {
	
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
     * 内容
     */
    private String content;
    /**
     * 发送人
     */
    private Long sendId;
    /**
     * 接收人
     */
    private Long receiveId;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 查看时间
     */
    private Date lookTime;
    /**
     * 是否已读 0未读；1已读
     */
    private Byte isRead;
    /**
     * 是否删除 0正常;-1删除
     */
    private Byte isDelete;
    
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
     * 获取内容
     * 
     * @return 内容
     */
    public String getContent(){
        return content;
    }
    
    /**
     * 设置内容
     * 
     * @param content 要设置的内容
     */
    public void setContent(String content){
        this.content = content;
    }
    /**
     * 获取发送人
     * 
     * @return 发送人
     */
    public Long getSendId(){
        return sendId;
    }
    
    /**
     * 设置发送人
     * 
     * @param sendId 要设置的发送人
     */
    public void setSendId(Long sendId){
        this.sendId = sendId;
    }
    /**
     * 获取接收人
     * 
     * @return 接收人
     */
    public Long getReceiveId(){
        return receiveId;
    }
    
    /**
     * 设置接收人
     * 
     * @param receiveId 要设置的接收人
     */
    public void setReceiveId(Long receiveId){
        this.receiveId = receiveId;
    }
    /**
     * 获取发送时间
     * 
     * @return 发送时间
     */
    public Date getSendTime(){
        return sendTime;
    }
    
    /**
     * 设置发送时间
     * 
     * @param sendTime 要设置的发送时间
     */
    public void setSendTime(Date sendTime){
        this.sendTime = sendTime;
    }
    /**
     * 获取查看时间
     * 
     * @return 查看时间
     */
    public Date getLookTime(){
        return lookTime;
    }
    
    /**
     * 设置查看时间
     * 
     * @param lookTime 要设置的查看时间
     */
    public void setLookTime(Date lookTime){
        this.lookTime = lookTime;
    }
    /**
     * 获取是否已读 0未读；1已读
     * 
     * @return 是否已读 0未读；1已读
     */
    public Byte getIsRead(){
        return isRead;
    }
    
    /**
     * 设置是否已读 0未读；1已读
     * 
     * @param isRead 要设置的是否已读 0未读；1已读
     */
    public void setIsRead(Byte isRead){
        this.isRead = isRead;
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
}


