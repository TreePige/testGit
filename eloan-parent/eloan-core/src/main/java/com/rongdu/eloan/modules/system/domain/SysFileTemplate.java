package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统文件模板表实体
 * @author wgc
 * @version 1.0
 * @since 2015-02-03
 */
public class SysFileTemplate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板类型
     */
    private Byte type;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 文件相对路经
     */
    private String url;
    /**
     * 0未删除   -1已删除
     */
    private Byte isDelete;
    /**
     * 说明，可为空
     */
    private String remark;
    
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
     * 获取模板名称
     * 
     * @return 模板名称
     */
    public String getName(){
        return name;
    }
    
    /**
     * 设置模板名称
     * 
     * @param name 要设置的模板名称
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 获取模板类型
     * 
     * @return 模板类型
     */
    public Byte getType(){
        return type;
    }
    
    /**
     * 设置模板类型
     * 
     * @param type 要设置的模板类型
     */
    public void setType(Byte type){
        this.type = type;
    }
    /**
     * 获取上传时间
     * 
     * @return 上传时间
     */
    public Date getUploadTime(){
        return uploadTime;
    }
    
    /**
     * 设置上传时间
     * 
     * @param uploadTime 要设置的上传时间
     */
    public void setUploadTime(Date uploadTime){
        this.uploadTime = uploadTime;
    }
    /**
     * 获取文件相对路经
     * 
     * @return 文件相对路经
     */
    public String getUrl(){
        return url;
    }
    
    /**
     * 设置文件相对路经
     * 
     * @param url 要设置的文件相对路经
     */
    public void setUrl(String url){
        this.url = url;
    }
    /**
     * 获取0未删除   -1已删除
     * 
     * @return 0未删除   -1已删除
     */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置0未删除   -1已删除
     * 
     * @param isDelete 要设置的0未删除   -1已删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }
    /**
     * 获取说明，可为空
     * 
     * @return 说明，可为空
     */
    public String getRemark(){
        return remark;
    }
    
    /**
     * 设置说明，可为空
     * 
     * @param remark 要设置的说明，可为空
     */
    public void setRemark(String remark){
        this.remark = remark;
    }
}


