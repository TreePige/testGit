package com.rongdu.eloan.modules.paramconfig.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品参数表实体
 * @author mcwang
 * @version 1.0
 * @since 2016-08-03 05:36:09
 */
public class ProductParam  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
    * id,自增字段
    */
    private Long id;
    /**
    * 产品名
    */
    private String name;
    /**
    * 产品类型id
    */
    private Long productType;
    /**
    * 担保类型1信贷  2抵押-车贷
    */
    private Long ptype;
    /**
    * 还款方式
    */
    private Long repaymentType;
    /**
    * 是否展期(0为可以,1为否)
    */
    private Long delay;
    /**
    * 提前还款罚息费率
    */
    private BigDecimal aheadRepayRate;
    /**
    * 逾期罚息%日
    */
    private BigDecimal overdueDayRate;
    /**
    * 底点利率
    */
    private BigDecimal bottomMonthRate;
    /**
    * 适用机构
    */
    private String officeIds;
    /**
    * 创建者
    */
    private String creator;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 修改者
    */
    private String modifier;
    /**
    * 修改时间
    */
    private Date modifyDate;
    /**
    * 记录是否删除 0正常 -1已删除记录
    */
    private Long isDelete;
    /**
    * 备注
    */
    private String remark;
    /**
    * 是否已有业务运行（0无，1有）
    */
    private Long isRun;
    
    /**
    * 获取id,自增字段
    *
    * @return id,自增字段
    */
    public Long getId(){
        return id;
    }
    
    /**
     * 设置id,自增字段
     * 
     * @param id 要设置的id,自增字段
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
    * 获取产品名
    *
    * @return 产品名
    */
    public String getName(){
        return name;
    }
    
    /**
     * 设置产品名
     * 
     * @param name 要设置的产品名
     */
    public void setName(String name){
        this.name = name;
    }
    /**
    * 获取产品类型id
    *
    * @return 产品类型id
    */
    public Long getProductType(){
        return productType;
    }
    
    /**
     * 设置产品类型id
     * 
     * @param productType 要设置的产品类型id
     */
    public void setProductType(Long productType){
        this.productType = productType;
    }
    /**
    * 获取担保类型1信贷  2抵押-车贷
    *
    * @return 担保类型1信贷  2抵押-车贷
    */
    public Long getPtype(){
        return ptype;
    }
    
    /**
     * 设置担保类型1信贷  2抵押-车贷
     * 
     * @param ptype 要设置的担保类型1信贷  2抵押-车贷
     */
    public void setPtype(Long ptype){
        this.ptype = ptype;
    }
    /**
    * 获取还款方式
    *
    * @return 还款方式
    */
    public Long getRepaymentType(){
        return repaymentType;
    }
    
    /**
     * 设置还款方式
     * 
     * @param repaymentType 要设置的还款方式
     */
    public void setRepaymentType(Long repaymentType){
        this.repaymentType = repaymentType;
    }
    /**
    * 获取是否展期(0为可以,1为否)
    *
    * @return 是否展期(0为可以,1为否)
    */
    public Long getDelay(){
        return delay;
    }
    
    /**
     * 设置是否展期(0为可以,1为否)
     * 
     * @param delay 要设置的是否展期(0为可以,1为否)
     */
    public void setDelay(Long delay){
        this.delay = delay;
    }
    /**
    * 获取提前还款罚息费率
    *
    * @return 提前还款罚息费率
    */
    public BigDecimal getAheadRepayRate(){
        return aheadRepayRate;
    }
    
    /**
     * 设置提前还款罚息费率
     * 
     * @param aheadRepayRate 要设置的提前还款罚息费率
     */
    public void setAheadRepayRate(BigDecimal aheadRepayRate){
        this.aheadRepayRate = aheadRepayRate;
    }
    /**
    * 获取逾期罚息%日
    *
    * @return 逾期罚息%日
    */
    public BigDecimal getOverdueDayRate(){
        return overdueDayRate;
    }
    
    /**
     * 设置逾期罚息%日
     * 
     * @param overdueDayRate 要设置的逾期罚息%日
     */
    public void setOverdueDayRate(BigDecimal overdueDayRate){
        this.overdueDayRate = overdueDayRate;
    }
    /**
    * 获取底点利率
    *
    * @return 底点利率
    */
    public BigDecimal getBottomMonthRate(){
        return bottomMonthRate;
    }
    
    /**
     * 设置底点利率
     * 
     * @param bottomMonthRate 要设置的底点利率
     */
    public void setBottomMonthRate(BigDecimal bottomMonthRate){
        this.bottomMonthRate = bottomMonthRate;
    }
    /**
    * 获取适用机构
    *
    * @return 适用机构
    */
    public String getOfficeIds(){
        return officeIds;
    }
    
    /**
     * 设置适用机构
     * 
     * @param officeIds 要设置的适用机构
     */
    public void setOfficeIds(String officeIds){
        this.officeIds = officeIds;
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
    public Date getCreateDate(){
        return createDate;
    }
    
    /**
     * 设置创建时间
     * 
     * @param createDate 要设置的创建时间
     */
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
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
    public Date getModifyDate(){
        return modifyDate;
    }
    
    /**
     * 设置修改时间
     * 
     * @param modifyDate 要设置的修改时间
     */
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }
    /**
    * 获取记录是否删除 0正常 -1已删除记录
    *
    * @return 记录是否删除 0正常 -1已删除记录
    */
    public Long getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置记录是否删除 0正常 -1已删除记录
     * 
     * @param isDelete 要设置的记录是否删除 0正常 -1已删除记录
     */
    public void setIsDelete(Long isDelete){
        this.isDelete = isDelete;
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
    * 获取是否已有业务运行（0无，1有）
    *
    * @return 是否已有业务运行（0无，1有）
    */
    public Long getIsRun(){
        return isRun;
    }
    
    /**
     * 设置是否已有业务运行（0无，1有）
     * 
     * @param isRun 要设置的是否已有业务运行（0无，1有）
     */
    public void setIsRun(Long isRun){
        this.isRun = isRun;
    }
}


