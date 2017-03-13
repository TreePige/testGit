package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息实体
 * @author wulb
 * @version 1.0
 * @since 2016-08-15 05:50:16
 */
public class PlProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private long id;
    /**
    * 产品名
    */
    private String name;
    /**
    * 担保类型1信贷  2抵押-车贷
    */
    private Byte ptype;
    /**
    * 放款金额上限
    */
    private BigDecimal maxmlimit;
    /**
    * 期限上限
    */
    private long maxtlimit;
    /**
    * 0不可提前还款1可提前还款
    */
    private Byte prepayment;
    /**
    * 投资金额下限
    */
    private BigDecimal tminmlimit;
    /**
    * 投资金额上限
    */
    private BigDecimal tmaxmlimit;
    /**
    * 开放日期
    */
    private Date opendate;
    /**
    * 产品创建时间
    */
    private Date createtime;
    /**
    * 逾期罚息%日
    */
    private BigDecimal overduePenaltyRate;
    /**
    * 借款利率%月
    */
    private BigDecimal repaymentRate;
    /**
    * 提前还款违约金比率 月  暂作废
    */
    private BigDecimal repaymentDefault;
    /**
    * 产品是否已启用   0禁用   1启用
    */
    private Byte isopen;
    /**
    * 记录是否删除 0正常 -1已删除记录
    */
    private Byte isDelete;
    /**
    * 试用机构
    */
    private String officeIds;
    /**
    * 违约金周期
    */
    private Byte overduePeriod;
    /**
    * 逾期违约金利率%月
    */
    private BigDecimal overdueRate;
    /**
    * 备注
    */
    private String remark;
    /**
    * 借款期数
    */
    private Byte period;
    /**
    * 三个月内提前还款退还比例
    */
    private BigDecimal threePrepayment;
    /**
    * 六个月内提前还款退还比例
    */
    private BigDecimal sixPrepayment;
    /**
    * 产品类型 0A 1B 2C 3D
    */
    private Byte productType;
    /**
    * 借款保证金
    */
    private BigDecimal marginFee;
    /**
    * 一个月内提前还款退还比例
    */
    private BigDecimal onePrepayment;
    /**
    * 二个月内提前还款退还比例
    */
    private BigDecimal twoPrepayment;
    /**
    * 四个月内提前还款退还比例
    */
    private BigDecimal fourPrepayment;
    /**
    * 五个月内提前还款退还比例
    */
    private BigDecimal fivePrepayment;
    /**
    * 还款方式
    */
    private Byte repaymentType;
    /**
    * 是否展期(0为可以,1为否)
    */
    private Byte delay;
    /**
    * 车贷类型：0-分期 1-短期
    */
    private Byte carloanType;
    /**
    * 放款金额下限
    */
    private BigDecimal minmlimit;
    
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
    * 获取担保类型1信贷  2抵押-车贷
    *
    * @return 担保类型1信贷  2抵押-车贷
    */
    public Byte getPtype(){
        return ptype;
    }
    
    /**
     * 设置担保类型1信贷  2抵押-车贷
     * 
     * @param ptype 要设置的担保类型1信贷  2抵押-车贷
     */
    public void setPtype(Byte ptype){
        this.ptype = ptype;
    }

    /**
    * 获取放款金额上限
    *
    * @return 放款金额上限
    */
    public BigDecimal getMaxmlimit(){
        return maxmlimit;
    }
    
    /**
     * 设置放款金额上限
     * 
     * @param maxmlimit 要设置的放款金额上限
     */
    public void setMaxmlimit(BigDecimal maxmlimit){
        this.maxmlimit = maxmlimit;
    }

    /**
    * 获取期限上限
    *
    * @return 期限上限
    */
    public long getMaxtlimit(){
        return maxtlimit;
    }
    
    /**
     * 设置期限上限
     * 
     * @param maxtlimit 要设置的期限上限
     */
    public void setMaxtlimit(long maxtlimit){
        this.maxtlimit = maxtlimit;
    }

    /**
    * 获取0不可提前还款1可提前还款
    *
    * @return 0不可提前还款1可提前还款
    */
    public Byte getPrepayment(){
        return prepayment;
    }
    
    /**
     * 设置0不可提前还款1可提前还款
     * 
     * @param prepayment 要设置的0不可提前还款1可提前还款
     */
    public void setPrepayment(Byte prepayment){
        this.prepayment = prepayment;
    }

    /**
    * 获取投资金额下限
    *
    * @return 投资金额下限
    */
    public BigDecimal getTminmlimit(){
        return tminmlimit;
    }
    
    /**
     * 设置投资金额下限
     * 
     * @param tminmlimit 要设置的投资金额下限
     */
    public void setTminmlimit(BigDecimal tminmlimit){
        this.tminmlimit = tminmlimit;
    }

    /**
    * 获取投资金额上限
    *
    * @return 投资金额上限
    */
    public BigDecimal getTmaxmlimit(){
        return tmaxmlimit;
    }
    
    /**
     * 设置投资金额上限
     * 
     * @param tmaxmlimit 要设置的投资金额上限
     */
    public void setTmaxmlimit(BigDecimal tmaxmlimit){
        this.tmaxmlimit = tmaxmlimit;
    }

    /**
    * 获取开放日期
    *
    * @return 开放日期
    */
    public Date getOpendate(){
        return opendate;
    }
    
    /**
     * 设置开放日期
     * 
     * @param opendate 要设置的开放日期
     */
    public void setOpendate(Date opendate){
        this.opendate = opendate;
    }

    /**
    * 获取产品创建时间
    *
    * @return 产品创建时间
    */
    public Date getCreatetime(){
        return createtime;
    }
    
    /**
     * 设置产品创建时间
     * 
     * @param createtime 要设置的产品创建时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

    /**
    * 获取逾期罚息%日
    *
    * @return 逾期罚息%日
    */
    public BigDecimal getOverduePenaltyRate(){
        return overduePenaltyRate;
    }
    
    /**
     * 设置逾期罚息%日
     * 
     * @param overduePenaltyRate 要设置的逾期罚息%日
     */
    public void setOverduePenaltyRate(BigDecimal overduePenaltyRate){
        this.overduePenaltyRate = overduePenaltyRate;
    }

    /**
    * 获取借款利率%月
    *
    * @return 借款利率%月
    */
    public BigDecimal getRepaymentRate(){
        return repaymentRate;
    }
    
    /**
     * 设置借款利率%月
     * 
     * @param repaymentRate 要设置的借款利率%月
     */
    public void setRepaymentRate(BigDecimal repaymentRate){
        this.repaymentRate = repaymentRate;
    }

    /**
    * 获取提前还款违约金比率 月  暂作废
    *
    * @return 提前还款违约金比率 月  暂作废
    */
    public BigDecimal getRepaymentDefault(){
        return repaymentDefault;
    }
    
    /**
     * 设置提前还款违约金比率 月  暂作废
     * 
     * @param repaymentDefault 要设置的提前还款违约金比率 月  暂作废
     */
    public void setRepaymentDefault(BigDecimal repaymentDefault){
        this.repaymentDefault = repaymentDefault;
    }

    /**
    * 获取产品是否已启用   0禁用   1启用
    *
    * @return 产品是否已启用   0禁用   1启用
    */
    public Byte getIsopen(){
        return isopen;
    }
    
    /**
     * 设置产品是否已启用   0禁用   1启用
     * 
     * @param isopen 要设置的产品是否已启用   0禁用   1启用
     */
    public void setIsopen(Byte isopen){
        this.isopen = isopen;
    }

    /**
    * 获取记录是否删除 0正常 -1已删除记录
    *
    * @return 记录是否删除 0正常 -1已删除记录
    */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置记录是否删除 0正常 -1已删除记录
     * 
     * @param isDelete 要设置的记录是否删除 0正常 -1已删除记录
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }

    /**
    * 获取试用机构
    *
    * @return 试用机构
    */
    public String getOfficeIds(){
        return officeIds;
    }
    
    /**
     * 设置试用机构
     * 
     * @param officeIds 要设置的试用机构
     */
    public void setOfficeIds(String officeIds){
        this.officeIds = officeIds;
    }

    /**
    * 获取违约金周期
    *
    * @return 违约金周期
    */
    public Byte getOverduePeriod(){
        return overduePeriod;
    }
    
    /**
     * 设置违约金周期
     * 
     * @param overduePeriod 要设置的违约金周期
     */
    public void setOverduePeriod(Byte overduePeriod){
        this.overduePeriod = overduePeriod;
    }

    /**
    * 获取逾期违约金利率%月
    *
    * @return 逾期违约金利率%月
    */
    public BigDecimal getOverdueRate(){
        return overdueRate;
    }
    
    /**
     * 设置逾期违约金利率%月
     * 
     * @param overdueRate 要设置的逾期违约金利率%月
     */
    public void setOverdueRate(BigDecimal overdueRate){
        this.overdueRate = overdueRate;
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
    * 获取借款期数
    *
    * @return 借款期数
    */
    public Byte getPeriod(){
        return period;
    }
    
    /**
     * 设置借款期数
     * 
     * @param period 要设置的借款期数
     */
    public void setPeriod(Byte period){
        this.period = period;
    }

    /**
    * 获取三个月内提前还款退还比例
    *
    * @return 三个月内提前还款退还比例
    */
    public BigDecimal getThreePrepayment(){
        return threePrepayment;
    }
    
    /**
     * 设置三个月内提前还款退还比例
     * 
     * @param threePrepayment 要设置的三个月内提前还款退还比例
     */
    public void setThreePrepayment(BigDecimal threePrepayment){
        this.threePrepayment = threePrepayment;
    }

    /**
    * 获取六个月内提前还款退还比例
    *
    * @return 六个月内提前还款退还比例
    */
    public BigDecimal getSixPrepayment(){
        return sixPrepayment;
    }
    
    /**
     * 设置六个月内提前还款退还比例
     * 
     * @param sixPrepayment 要设置的六个月内提前还款退还比例
     */
    public void setSixPrepayment(BigDecimal sixPrepayment){
        this.sixPrepayment = sixPrepayment;
    }

    /**
    * 获取产品类型 0A 1B 2C 3D
    *
    * @return 产品类型 0A 1B 2C 3D
    */
    public Byte getProductType(){
        return productType;
    }
    
    /**
     * 设置产品类型 0A 1B 2C 3D
     * 
     * @param productType 要设置的产品类型 0A 1B 2C 3D
     */
    public void setProductType(Byte productType){
        this.productType = productType;
    }

    /**
    * 获取借款保证金
    *
    * @return 借款保证金
    */
    public BigDecimal getMarginFee(){
        return marginFee;
    }
    
    /**
     * 设置借款保证金
     * 
     * @param marginFee 要设置的借款保证金
     */
    public void setMarginFee(BigDecimal marginFee){
        this.marginFee = marginFee;
    }

    /**
    * 获取一个月内提前还款退还比例
    *
    * @return 一个月内提前还款退还比例
    */
    public BigDecimal getOnePrepayment(){
        return onePrepayment;
    }
    
    /**
     * 设置一个月内提前还款退还比例
     * 
     * @param onePrepayment 要设置的一个月内提前还款退还比例
     */
    public void setOnePrepayment(BigDecimal onePrepayment){
        this.onePrepayment = onePrepayment;
    }

    /**
    * 获取二个月内提前还款退还比例
    *
    * @return 二个月内提前还款退还比例
    */
    public BigDecimal getTwoPrepayment(){
        return twoPrepayment;
    }
    
    /**
     * 设置二个月内提前还款退还比例
     * 
     * @param twoPrepayment 要设置的二个月内提前还款退还比例
     */
    public void setTwoPrepayment(BigDecimal twoPrepayment){
        this.twoPrepayment = twoPrepayment;
    }

    /**
    * 获取四个月内提前还款退还比例
    *
    * @return 四个月内提前还款退还比例
    */
    public BigDecimal getFourPrepayment(){
        return fourPrepayment;
    }
    
    /**
     * 设置四个月内提前还款退还比例
     * 
     * @param fourPrepayment 要设置的四个月内提前还款退还比例
     */
    public void setFourPrepayment(BigDecimal fourPrepayment){
        this.fourPrepayment = fourPrepayment;
    }

    /**
    * 获取五个月内提前还款退还比例
    *
    * @return 五个月内提前还款退还比例
    */
    public BigDecimal getFivePrepayment(){
        return fivePrepayment;
    }
    
    /**
     * 设置五个月内提前还款退还比例
     * 
     * @param fivePrepayment 要设置的五个月内提前还款退还比例
     */
    public void setFivePrepayment(BigDecimal fivePrepayment){
        this.fivePrepayment = fivePrepayment;
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

    /**
    * 获取是否展期(0为可以,1为否)
    *
    * @return 是否展期(0为可以,1为否)
    */
    public Byte getDelay(){
        return delay;
    }
    
    /**
     * 设置是否展期(0为可以,1为否)
     * 
     * @param delay 要设置的是否展期(0为可以,1为否)
     */
    public void setDelay(Byte delay){
        this.delay = delay;
    }

    /**
    * 获取车贷类型：0-分期 1-短期
    *
    * @return 车贷类型：0-分期 1-短期
    */
    public Byte getCarloanType(){
        return carloanType;
    }
    
    /**
     * 设置车贷类型：0-分期 1-短期
     * 
     * @param carloanType 要设置的车贷类型：0-分期 1-短期
     */
    public void setCarloanType(Byte carloanType){
        this.carloanType = carloanType;
    }

    /**
    * 获取放款金额下限
    *
    * @return 放款金额下限
    */
    public BigDecimal getMinmlimit(){
        return minmlimit;
    }
    
    /**
     * 设置放款金额下限
     * 
     * @param minmlimit 要设置的放款金额下限
     */
    public void setMinmlimit(BigDecimal minmlimit){
        this.minmlimit = minmlimit;
    }

}