package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同附件信息实体
 * @author wulb
 * @version 1.0
 * @since 2016-08-29 10:50:06
 */
public class PubContractAttachment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private long id;
    /**
    * 创建人
    */
    private long creator;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改者
    */
    private long modifier;
    /**
    * 修改时间
    */
    private Date modifyTime;
    /**
    * 附件状态
    */
    private long state;
    /**
    * 0不删除1已删除
    */
    private Byte isDelete;
    /**
    * 合同名称
    */
    private String name;
    /**
    * 原文件名
    */
    private String fileName;
    /**
    * 新文件名
    */
    private String newfileName;
    /**
    * 生效节点
    */
    private long effectiveNode;
    /**
    * 文件大小
    */
    private long fileSize;
    /**
    * 上传后相对路径
    */
    private String filePath;
    
    /**
    * 获取id
    *
    * @return id
    */
    public long getId(){
        return id;
    }
    
    /**
     * 设置id
     * 
     * @param long 要设置的id
     */
    public void setId(long id){
        this.id = id;
    }
    
    /**
    * 获取创建人
    *
    * @return 创建人
    */
    public long getCreator(){
        return creator;
    }
    
    /**
     * 设置创建人
     * 
     * @param creator 要设置的创建人
     */
    public void setCreator(long creator){
        this.creator = creator;
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
    * 获取修改者
    *
    * @return 修改者
    */
    public long getModifier(){
        return modifier;
    }
    
    /**
     * 设置修改者
     * 
     * @param modifier 要设置的修改者
     */
    public void setModifier(long modifier){
        this.modifier = modifier;
    }

    /**
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getModifyTime(){
        return modifyTime;
    }
    
    /**
     * 设置修改时间
     * 
     * @param modifyTime 要设置的修改时间
     */
    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }

    /**
    * 获取附件状态
    *
    * @return 附件状态
    */
    public long getState(){
        return state;
    }
    
    /**
     * 设置附件状态
     * 
     * @param state 要设置的附件状态
     */
    public void setState(long state){
        this.state = state;
    }

    /**
    * 获取0不删除1已删除
    *
    * @return 0不删除1已删除
    */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置0不删除1已删除
     * 
     * @param isDelete 要设置的0不删除1已删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }

    /**
    * 获取合同名称
    *
    * @return 合同名称
    */
    public String getName(){
        return name;
    }
    
    /**
     * 设置合同名称
     * 
     * @param name 要设置的合同名称
     */
    public void setName(String name){
        this.name = name;
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
    * 获取生效节点
    *
    * @return 生效节点
    */
    public long getEffectiveNode(){
        return effectiveNode;
    }
    
    /**
     * 设置生效节点
     * 
     * @param effectiveNode 要设置的生效节点
     */
    public void setEffectiveNode(long effectiveNode){
        this.effectiveNode = effectiveNode;
    }

    /**
    * 获取文件大小
    *
    * @return 文件大小
    */
    public long getFileSize(){
        return fileSize;
    }
    
    /**
     * 设置文件大小
     * 
     * @param fileSize 要设置的文件大小
     */
    public void setFileSize(long fileSize){
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

}