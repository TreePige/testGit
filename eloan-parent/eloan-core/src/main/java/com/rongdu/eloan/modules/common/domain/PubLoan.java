package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 放款实体
 * @author fzc
 * @version 1.0
 * @since 2016-08-26 05:51:23
 */
public class PubLoan implements Serializable {
	
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
    * 是否删除 0正常 1删除
    */
    private Byte isDelete;
    /**
    * 放款金额
    */
    private BigDecimal account;
    /**
    * 放款银行
    */
    private Integer bankFlag;
    /**
    * 放款银行账号
    */
    private String bankAccount;
    /**
    * 放款
    */
    private long operator;
    /**
    * 备注 放款
    */
    private String remark;
    /**
    * 
    */
    private String processinstanceid;
    /**
    * 放款
    */
    private Date loanTime;
    /**
    * 
    */
    private long projectId;
    /**
    * 客户名
    */
    private String customerName;
    /**
    * 客户银行账号
    */
    private String customerBankCard;
    /**
    * 客户id
    */
    private long customerId;
    /**
    * 客户开户行
    */
    private String customerBank;
    /**
    * 客户开户网点
    */
    private String customerObank;
    /**
    * 1-放款 
    */
    private Byte type;
    
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
    * 获取是否删除 0正常 1删除
    *
    * @return 是否删除 0正常 1删除
    */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置是否删除 0正常 1删除
     * 
     * @param isDelete 要设置的是否删除 0正常 1删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }

    /**
    * 获取放款金额
    *
    * @return 放款金额
    */
    public BigDecimal getAccount(){
        return account;
    }
    
    /**
     * 设置放款金额
     * 
     * @param account 要设置的放款金额
     */
    public void setAccount(BigDecimal account){
        this.account = account;
    }

    public Integer getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(Integer bankFlag) {
		this.bankFlag = bankFlag;
	}

	/**
    * 获取放款银行账号
    *
    * @return 放款银行账号
    */
    public String getBankAccount(){
        return bankAccount;
    }
    
    /**
     * 设置放款银行账号
     * 
     * @param bankAccount 要设置的放款银行账号
     */
    public void setBankAccount(String bankAccount){
        this.bankAccount = bankAccount;
    }

    /**
    * 获取放款
    *
    * @return 放款
    */
    public long getOperator(){
        return operator;
    }
    
    /**
     * 设置放款
     * 
     * @param operator 要设置的放款
     */
    public void setOperator(long operator){
        this.operator = operator;
    }

    /**
    * 获取备注 放款
    *
    * @return 备注 放款
    */
    public String getRemark(){
        return remark;
    }
    
    /**
     * 设置备注 放款
     * 
     * @param remark 要设置的备注 放款
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getProcessinstanceid(){
        return processinstanceid;
    }
    
    /**
     * 设置
     * 
     * @param processinstanceid 要设置的
     */
    public void setProcessinstanceid(String processinstanceid){
        this.processinstanceid = processinstanceid;
    }

    /**
    * 获取放款
    *
    * @return 放款
    */
    public Date getLoanTime(){
        return loanTime;
    }
    
    /**
     * 设置放款
     * 
     * @param loanTime 要设置的放款
     */
    public void setLoanTime(Date loanTime){
        this.loanTime = loanTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public long getProjectId(){
        return projectId;
    }
    
    /**
     * 设置
     * 
     * @param projectId 要设置的
     */
    public void setProjectId(long projectId){
        this.projectId = projectId;
    }

    /**
    * 获取客户名
    *
    * @return 客户名
    */
    public String getCustomerName(){
        return customerName;
    }
    
    /**
     * 设置客户名
     * 
     * @param customerName 要设置的客户名
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
    * 获取客户银行账号
    *
    * @return 客户银行账号
    */
    public String getCustomerBankCard(){
        return customerBankCard;
    }
    
    /**
     * 设置客户银行账号
     * 
     * @param customerBankCard 要设置的客户银行账号
     */
    public void setCustomerBankCard(String customerBankCard){
        this.customerBankCard = customerBankCard;
    }

    /**
    * 获取客户id
    *
    * @return 客户id
    */
    public long getCustomerId(){
        return customerId;
    }
    
    /**
     * 设置客户id
     * 
     * @param customerId 要设置的客户id
     */
    public void setCustomerId(long customerId){
        this.customerId = customerId;
    }

    /**
    * 获取客户开户行
    *
    * @return 客户开户行
    */
    public String getCustomerBank(){
        return customerBank;
    }
    
    /**
     * 设置客户开户行
     * 
     * @param customerBank 要设置的客户开户行
     */
    public void setCustomerBank(String customerBank){
        this.customerBank = customerBank;
    }

    /**
    * 获取客户开户网点
    *
    * @return 客户开户网点
    */
    public String getCustomerObank(){
        return customerObank;
    }
    
    /**
     * 设置客户开户网点
     * 
     * @param customerObank 要设置的客户开户网点
     */
    public void setCustomerObank(String customerObank){
        this.customerObank = customerObank;
    }

    /**
    * 获取1-放款 
    *
    * @return 1-放款 
    */
    public Byte getType(){
        return type;
    }
    
    /**
     * 设置1-放款 
     * 
     * @param type 要设置的1-放款 
     */
    public void setType(Byte type){
        this.type = type;
    }

}