package com.rongdu.eloan.modules.common.utils.databean;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/**
 * 业务数据bean 保存每个流程都保存的通用业务数据
 * 
 * @author FHJ
 *
 */
public class BasicServiceDataBean extends ProjectWorkflowDataBean {
	/** 借款金额 */
	protected BigDecimal amountComment;
	/** 拒贷类别 */
	protected String rejectionCategoryComment;
	/** 资料类别 */
	protected String materialCategoryComment;
    /** 抵押类型 */
    protected Byte mortgageTypeComment;
	/** 客户ID */
	protected Long customerIdComment;
	/** 借款期数 */
	protected Integer periodComment;
	/** 产品ID */
	protected Long productIdComment;
	/** 处理意见备注 */
	protected String remarkComment;
	/** 内部决策描述 */
	protected String internalDecisionComment;
	/** 反馈客服描述 */
	protected String feedbackCustomerComment;
	/** 面审分数 */
	protected int grade;
	/** 下户费收取形式 */
	protected Byte receiveType;
	 /** 房屋快处值  */
	protected BigDecimal housingValueFaster;
	
	protected int curuserid;//销售员
	
	private String manualAssignee;//手动任务分配人

	public String getManualAssignee() {
		return manualAssignee;
	}

	public void setManualAssignee(String manualAssignee) {
		this.manualAssignee = manualAssignee;
	}

	public int getCuruserid() {
		return curuserid;
	}

	public void setCuruserid(int curuserid) {
		this.curuserid = curuserid;
	}
	
	public BigDecimal getAmountComment() {
		return amountComment;
	}
	public void setAmountComment(BigDecimal amountComment) {
		this.amountComment = amountComment;
	}
	public Long getCustomerIdComment() {
		return customerIdComment;
	}
	public void setCustomerIdComment(Long customerIdComment) {
		this.customerIdComment = customerIdComment;
	}
	
	public Integer getPeriodComment() {
		return periodComment;
	}
	public void setPeriodComment(Integer periodComment) {
		this.periodComment = periodComment;
	}
	public Long getProductIdComment() {
		return productIdComment;
	}
	public void setProductIdComment(Long productIdComment) {
		this.productIdComment = productIdComment;
	}
	public String getRemarkComment() {
		return remarkComment;
	}
	public void setRemarkComment(String remarkComment) {
		this.remarkComment = remarkComment;
	}
	
	public String getRejectionCategoryComment() {
		return rejectionCategoryComment;
	}
	public void setRejectionCategoryComment(String rejectionCategoryComment) {
		this.rejectionCategoryComment = rejectionCategoryComment;
	}
	public String getMaterialCategoryComment() {
		return materialCategoryComment;
	}
	public void setMaterialCategoryComment(String materialCategoryComment) {
		this.materialCategoryComment = materialCategoryComment;
	}

    public Byte getMortgageTypeComment() {
        return mortgageTypeComment;
    }

    public void setMortgageTypeComment(Byte mortgageTypeComment) {
        this.mortgageTypeComment = mortgageTypeComment;
    }

    public String getInternalDecisionComment() {
		return internalDecisionComment;
	}
	public void setInternalDecisionComment(String internalDecisionComment) {
		this.internalDecisionComment = internalDecisionComment;
	}
	public String getFeedbackCustomerComment() {
		return feedbackCustomerComment;
	}
	public void setFeedbackCustomerComment(String feedbackCustomerComment) {
		this.feedbackCustomerComment = feedbackCustomerComment;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Byte getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(Byte receiveType) {
		this.receiveType = receiveType;
	}
	
	public BigDecimal getHousingValueFaster() {
		return housingValueFaster;
	}

	public void setHousingValueFaster(BigDecimal housingValueFaster) {
		this.housingValueFaster = housingValueFaster;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
