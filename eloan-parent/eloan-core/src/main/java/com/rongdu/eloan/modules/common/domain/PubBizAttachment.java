package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务附件表实体
 * @author lyz
 * @version 1.0
 * @since 2016-07-18 02:58:27
 */
public class PubBizAttachment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private Long id;
    /**
    * 附件状态:0删除,1正常
    */
    private Integer state;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 原文件名
    */
    private String fileName;
    /**
    * 新文件名
    */
    private String newfileName;
    /**
    * 文件大小
    */
    private Long fileSize;
    /**
    * 上传后相对路径
    */
    private String filePath;
    /**
    * 原文件签名
    */
    private String signcod;
    /**
    * 操作者id
    */
    private Long operatorId;
    /**
    * 所属业务类型
    */
    private String bizType;
    /**
    * 关联id
    */
    private Long relationId;
    
    /**
    * 获取id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }
    
    /**
     * 设置id
     * 
     * @param long 要设置的id
     */
    public void setId(Long id){
        this.id = id;
    }
    
    /**
    * 获取附件状态:0删除,1正常
    *
    * @return 附件状态:0删除,1正常
    */
    public Integer getState(){
        return state;
    }
    
    /**
     * 设置附件状态:0删除,1正常
     * 
     * @param state 要设置的附件状态:0删除,1正常
     */
    public void setState(Integer state){
        this.state = state;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }
    
    /**
     * 设置创建时间
     * 
     * @param createTime 要设置的创建时间
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取原文件名
    *
    * @return 原文件名
    */
    public String getFileName(){
        return fileName;
    }
    
    /**
     * 设置原文件名
     * 
     * @param fileName 要设置的原文件名
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
    * 获取新文件名
    *
    * @return 新文件名
    */
    public String getNewfileName(){
        return newfileName;
    }
    
    /**
     * 设置新文件名
     * 
     * @param newfileName 要设置的新文件名
     */
    public void setNewfileName(String newfileName){
        this.newfileName = newfileName;
    }

    /**
    * 获取文件大小
    *
    * @return 文件大小
    */
    public Long getFileSize(){
        return fileSize;
    }
    
    /**
     * 设置文件大小
     * 
     * @param fileSize 要设置的文件大小
     */
    public void setFileSize(Long fileSize){
        this.fileSize = fileSize;
    }

    /**
    * 获取上传后相对路径
    *
    * @return 上传后相对路径
    */
    public String getFilePath(){
        return filePath;
    }
    
    /**
     * 设置上传后相对路径
     * 
     * @param filePath 要设置的上传后相对路径
     */
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    /**
    * 获取原文件签名
    *
    * @return 原文件签名
    */
    public String getSigncod(){
        return signcod;
    }
    
    /**
     * 设置原文件签名
     * 
     * @param signcod 要设置的原文件签名
     */
    public void setSigncod(String signcod){
        this.signcod = signcod;
    }

    /**
    * 获取操作者id
    *
    * @return 操作者id
    */
    public Long getOperatorId(){
        return operatorId;
    }
    
    /**
     * 设置操作者id
     * 
     * @param operatorId 要设置的操作者id
     */
    public void setOperatorId(Long operatorId){
        this.operatorId = operatorId;
    }

    /**
    * 获取所属业务类型
    *
    * @return 所属业务类型
    */
    public String getBizType(){
        return bizType;
    }
    
    /**
     * 设置所属业务类型
     * 
     * @param bizType 要设置的所属业务类型
     */
    public void setBizType(String bizType){
        this.bizType = bizType;
    }

    /**
    * 获取关联id
    *
    * @return 关联id
    */
    public Long getRelationId(){
        return relationId;
    }
    
    /**
     * 设置关联id
     * 
     * @param relationId 要设置的关联id
     */
    public void setRelationId(Long relationId){
        this.relationId = relationId;
    }

}