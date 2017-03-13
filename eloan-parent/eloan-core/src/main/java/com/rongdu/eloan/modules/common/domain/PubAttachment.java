package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *	@Description 附件
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月12日 下午1:52:31
 *  @since version 1.0.0
 */
public class PubAttachment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
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
    private String btype;

    /**
    * 附件类型
    */
    private Integer atype;

    /**
    * 咨询id
    */
    private Long consultId;

    /**
    * 流程ID
    */
    private Long processInstanceId;

    /**
    * 项目编号
    */
    private Long projectId;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
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
    public String getBtype(){
        return btype;
    }
    
    /**
     * 设置所属业务类型
     * 
     * @param btype 要设置的所属业务类型
     */
    public void setBtype(String btype){
        this.btype = btype;
    }

    /**
    * 获取附件类型
    *
    * @return 附件类型
    */
    public Integer getAtype(){
        return atype;
    }
    
    /**
     * 设置附件类型
     * 
     * @param atype 要设置的附件类型
     */
    public void setAtype(Integer atype){
        this.atype = atype;
    }

    /**
    * 获取咨询id
    *
    * @return 咨询id
    */
    public Long getConsultId(){
        return consultId;
    }
    
    /**
     * 设置咨询id
     * 
     * @param consultId 要设置的咨询id
     */
    public void setConsultId(Long consultId){
        this.consultId = consultId;
    }

    /**
    * 获取流程ID
    *
    * @return 流程ID
    */
    public Long getProcessInstanceId(){
        return processInstanceId;
    }
    
    /**
     * 设置流程ID
     * 
     * @param processInstanceId 要设置的流程ID
     */
    public void setProcessInstanceId(Long processInstanceId){
        this.processInstanceId = processInstanceId;
    }

    /**
    * 获取项目编号
    *
    * @return 项目编号
    */
    public Long getProjectId(){
        return projectId;
    }
    
    /**
     * 设置项目编号
     * 
     * @param projectId 要设置的项目编号
     */
    public void setProjectId(Long projectId){
        this.projectId = projectId;
    }

	@Override
	public String toString() {
		return "PubAttachment [id=" + id + ", state=" + state + ", createTime="
				+ createTime + ", fileName=" + fileName + ", newfileName="
				+ newfileName + ", fileSize=" + fileSize + ", filePath="
				+ filePath + ", signcod=" + signcod + ", operatorId="
				+ operatorId + ", btype=" + btype + ", atype=" + atype
				+ ", consultId=" + consultId + ", processInstanceId="
				+ processInstanceId + ", projectId=" + projectId + "]";
	}
    
}


