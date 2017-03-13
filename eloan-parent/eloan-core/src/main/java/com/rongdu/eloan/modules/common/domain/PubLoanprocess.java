package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 审批历史实体
 * @author wulb
 * @version 1.0
 * @since 2016-08-05 04:12:22
 */
public class PubLoanprocess implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private long id;
    /**
    * 审批者
    */
    private long creator;
    /**
    * 审批时间
    */
    private Date createTime;
    /**
    * 是否删除0正常 -1已删除
    */
    private Byte isDelete;
    /**
    * 所属机构
    */
    private String officeId;
    /**
    * 流程ID
    */
    private String processInstanceId;
    /**
    * 咨询ID
    */
    private long consultId;
    /**
    * 客户ID
    */
    private long customerId;
    /**
     * 下户费收取形式
     */
    private Byte receiveType;
    /**
    * 处理意见
    */
    private String type;
    /**
    * 流程状态
    */
    private String processState;
    /**
    * 资料类别/拒贷类别
    */
    private String category;
    /**
    * 备注
    */
    private String remark;
    /**
    * 产品ID
    */
    private long productId;
    /**
    * 批贷金额
    */
    private BigDecimal amount;
    /**
    * 原任务ID
    */
    private String taskId;
    /**
    * 新任务ID(next task id)
    */
    private String newTaskId;
    /**
    * 项目编号
    */
    private long projectId;
    /**
     * 房屋快处值
     */
     private BigDecimal housingValueFaster;
     /**
      * 手动任务分配人
      */
      private String manualAssignee;
    
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
    * 获取审批者
    *
    * @return 审批者
    */
    public long getCreator(){
        return creator;
    }
    
    /**
     * 设置审批者
     * 
     * @param creator 要设置的审批者
     */
    public void setCreator(long creator){
        this.creator = creator;
    }

    /**
    * 获取审批时间
    *
    * @return 审批时间
    */
    public Date getCreateTime(){
        return createTime;
    }
    
    /**
     * 设置审批时间
     * 
     * @param createTime 要设置的审批时间
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取是否删除0正常 -1已删除
    *
    * @return 是否删除0正常 -1已删除
    */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置是否删除0正常 -1已删除
     * 
     * @param isDelete 要设置的是否删除0正常 -1已删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }

    /**
    * 获取所属机构
    *
    * @return 所属机构
    */
    public String getOfficeId(){
        return officeId;
    }
    
    /**
     * 设置所属机构
     * 
     * @param officeId 要设置的所属机构
     */
    public void setOfficeId(String officeId){
        this.officeId = officeId;
    }

    /**
    * 获取流程ID
    *
    * @return 流程ID
    */
    public String getProcessInstanceId(){
        return processInstanceId;
    }
    
    /**
     * 设置流程ID
     * 
     * @param processInstanceId 要设置的流程ID
     */
    public void setProcessInstanceId(String processInstanceId){
        this.processInstanceId = processInstanceId;
    }

    /**
    * 获取咨询ID
    *
    * @return 咨询ID
    */
    public long getConsultId(){
        return consultId;
    }
    
    /**
     * 设置咨询ID
     * 
     * @param consultId 要设置的咨询ID
     */
    public void setConsultId(long consultId){
        this.consultId = consultId;
    }

    /**
    * 获取客户ID
    *
    * @return 客户ID
    */
    public long getCustomerId(){
        return customerId;
    }
    
    /**
     * 设置客户ID
     * 
     * @param customerId 要设置的客户ID
     */
    public void setCustomerId(long customerId){
        this.customerId = customerId;
    }

	/**
     * 获取下户费收取形式
     *
     * @return 下户费收取形式
     */
    public Byte getReceiveType() {
		return receiveType;
	}

    /**
     * 设置下户费收取形式
     * 
     * @param receiveType 要设置的下户费收取形式
     */
	public void setReceiveType(Byte receiveType) {
		this.receiveType = receiveType;
	}

	/**
    * 获取处理意见
    *
    * @return 处理意见
    */
    public String getType(){
        return type;
    }
    
    /**
     * 设置处理意见
     * 
     * @param type 要设置的处理意见
     */
    public void setType(String type){
        this.type = type;
    }

    /**
    * 获取流程状态
    *
    * @return 流程状态
    */
    public String getProcessState(){
        return processState;
    }
    
    /**
     * 设置流程状态
     * 
     * @param processState 要设置的流程状态
     */
    public void setProcessState(String processState){
        this.processState = processState;
    }

    /**
    * 获取资料类别/拒贷类别
    *
    * @return 资料类别/拒贷类别
    */
    public String getCategory(){
        return category;
    }
    
    /**
     * 设置资料类别/拒贷类别
     * 
     * @param category 要设置的资料类别/拒贷类别
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
        return remark;
    }
    
    /**
     * 设置备注
     * 
     * @param remark 要设置的备注
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取产品ID
    *
    * @return 产品ID
    */
    public long getProductId(){
        return productId;
    }
    
    /**
     * 设置产品ID
     * 
     * @param productId 要设置的产品ID
     */
    public void setProductId(long productId){
        this.productId = productId;
    }

    /**
    * 获取批贷金额
    *
    * @return 批贷金额
    */
    public BigDecimal getAmount(){
        return amount;
    }
    
    /**
     * 设置批贷金额
     * 
     * @param amount 要设置的批贷金额
     */
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    /**
    * 获取原任务ID
    *
    * @return 原任务ID
    */
    public String getTaskId(){
        return taskId;
    }
    
    /**
     * 设置原任务ID
     * 
     * @param taskId 要设置的原任务ID
     */
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    /**
    * 获取新任务ID(next task id)
    *
    * @return 新任务ID(next task id)
    */
    public String getNewTaskId(){
        return newTaskId;
    }
    
    /**
     * 设置新任务ID(next task id)
     * 
     * @param newTaskId 要设置的新任务ID(next task id)
     */
    public void setNewTaskId(String newTaskId){
        this.newTaskId = newTaskId;
    }

    /**
    * 获取项目编号
    *
    * @return 项目编号
    */
    public long getProjectId(){
        return projectId;
    }
    
    /**
     * 设置项目编号
     * 
     * @param projectId 要设置的项目编号
     */
    public void setProjectId(long projectId){
        this.projectId = projectId;
    }

	public BigDecimal getHousingValueFaster() {
		return housingValueFaster;
	}

	public void setHousingValueFaster(BigDecimal housingValueFaster) {
		this.housingValueFaster = housingValueFaster;
	}

	public String getManualAssignee() {
		return manualAssignee;
	}

	public void setManualAssignee(String manualAssignee) {
		this.manualAssignee = manualAssignee;
	}
	
}