package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统编码规则表实体
 * @author wgc
 * @version 1.0
 * @since 2015-02-03
 */
public class SysNoRules implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;
    /**
     * 编号名称
     */
    private String sName;
    /**
     * 编号代码
     */
    private String sCode;
    /**
     * 字符长度
     */
    private Byte sLength;
    /**
     * 字符含义
     */
    private String sContent;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 是否删除0正常-1删除
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
     * 获取编号名称
     * 
     * @return 编号名称
     */
    public String getSName(){
        return sName;
    }
    
    /**
     * 设置编号名称
     * 
     * @param sName 要设置的编号名称
     */
    public void setSName(String sName){
        this.sName = sName;
    }
    /**
     * 获取编号代码
     * 
     * @return 编号代码
     */
    public String getSCode(){
        return sCode;
    }
    
    /**
     * 设置编号代码
     * 
     * @param sCode 要设置的编号代码
     */
    public void setSCode(String sCode){
        this.sCode = sCode;
    }
    /**
     * 获取字符长度
     * 
     * @return 字符长度
     */
    public Byte getSLength(){
        return sLength;
    }
    
    /**
     * 设置字符长度
     * 
     * @param sLength 要设置的字符长度
     */
    public void setSLength(Byte sLength){
        this.sLength = sLength;
    }
    /**
     * 获取字符含义
     * 
     * @return 字符含义
     */
    public String getSContent(){
        return sContent;
    }
    
    /**
     * 设置字符含义
     * 
     * @param sContent 要设置的字符含义
     */
    public void setSContent(String sContent){
        this.sContent = sContent;
    }
    /**
     * 获取创建者
     * 
     * @return 创建者
     */
    public String getCreator(){
        return creator;
    }
    
    /**
     * 设置创建者
     * 
     * @param creator 要设置的创建者
     */
    public void setCreator(String creator){
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
    public String getModifier(){
        return modifier;
    }
    
    /**
     * 设置修改者
     * 
     * @param modifier 要设置的修改者
     */
    public void setModifier(String modifier){
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
     * 获取是否删除0正常-1删除
     * 
     * @return 是否删除0正常-1删除
     */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置是否删除0正常-1删除
     * 
     * @param isDelete 要设置的是否删除0正常-1删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }
}


