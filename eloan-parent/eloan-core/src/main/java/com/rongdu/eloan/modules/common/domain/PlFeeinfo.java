package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用信息实体
 * @author wulb
 * @version 1.0
 * @since 2016-08-18 17:01:44
 */
public class PlFeeinfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private long id;
    /**
    * 项目ID
    */
    private long projectId;
    /**
    * 流程ID
    */
    private String processInstanceId;
    /**
    * 咨询id
    */
    private long consultId;
    /**
    * 创建人
    */
    private long creator;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改人
    */
    private long modifier;
    /**
    * 修改时间
    */
    private Date modifyTime;
    /**
    * 产品id
    */
    private long productId;
    /**
    * 月息
    */
    private BigDecimal repaymentRate;
    /**
    * 借款期限
    */
    private Byte timeLimit;
    /**
    * 还款方式
    */
    private Byte repaymentType;
    
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
    * 获取项目ID
    *
    * @return 项目ID
    */
    public long getProjectId(){
        return projectId;
    }
    
    /**
     * 设置项目ID
     * 
     * @param projectId 要设置的项目ID
     */
    public void setProjectId(long projectId){
        this.projectId = projectId;
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
    * 获取咨询id
    *
    * @return 咨询id
    */
    public long getConsultId(){
        return consultId;
    }
    
    /**
     * 设置咨询id
     * 
     * @param consultId 要设置的咨询id
     */
    public void setConsultId(long consultId){
        this.consultId = consultId;
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
    * 获取修改人
    *
    * @return 修改人
    */
    public long getModifier(){
        return modifier;
    }
    
    /**
     * 设置修改人
     * 
     * @param modifier 要设置的修改人
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
    * 获取产品id
    *
    * @return 产品id
    */
    public long getProductId(){
        return productId;
    }
    
    /**
     * 设置产品id
     * 
     * @param productId 要设置的产品id
     */
    public void setProductId(long productId){
        this.productId = productId;
    }

    /**
    * 获取月息
    *
    * @return 月息
    */
    public BigDecimal getRepaymentRate(){
        return repaymentRate;
    }
    
    /**
     * 设置月息
     * 
     * @param repaymentRate 要设置的月息
     */
    public void setRepaymentRate(BigDecimal repaymentRate){
        this.repaymentRate = repaymentRate;
    }

    /**
    * 获取借款期限
    *
    * @return 借款期限
    */
    public Byte getTimeLimit(){
        return timeLimit;
    }
    
    /**
     * 设置借款期限
     * 
     * @param timeLimit 要设置的借款期限
     */
    public void setTimeLimit(Byte timeLimit){
        this.timeLimit = timeLimit;
    }

    /**
    * 获取还款方式
    *
    * @return 还款方式
    */
    public Byte getRepaymentType(){
        return repaymentType;
    }
    
    /**
     * 设置还款方式
     * 
     * @param repaymentType 要设置的还款方式
     */
    public void setRepaymentType(Byte repaymentType){
        this.repaymentType = repaymentType;
    }

}