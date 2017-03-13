package com.rongdu.eloan.modules.paramconfig.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 产品类型表实体
 * @author wangmc
 * @version 1.0
 * @since 2016-07-12 03:36:54
 */
public class ProductType  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
    * id,自增字段
    */
    private Long id;
    /**
    * 产品类型
    */
    private String productType;
    /**
    * 公式表,id例:(1,2,3,4,5,6)
    */
    private String formulaId;
    /**
    * 贷款类型(1信贷  2车贷  3房贷)
    */
    private Long ptype;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 创建人
    */
    private String createName;
    /**
    * 修改时间
    */
    private Date modifyDate;
    /**
    * 修改人
    */
    private String modifyName;
    /**
    * 是否启用(0为启用,1为不启用)
    */
    private Long state;
    /**
    * 删除状态(0未删除,-1为删除)
    */
    private Long deleteState;
    /**
    * 注释
    */
    private String note;
    
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
    * 获取产品类型
    *
    * @return 产品类型
    */
    public String getProductType(){
        return productType;
    }
    
    /**
     * 设置产品类型
     * 
     * @param productType 要设置的产品类型
     */
    public void setProductType(String productType){
        this.productType = productType;
    }
    /**
    * 获取公式表,id例:(1,2,3,4,5,6)
    *
    * @return 公式表,id例:(1,2,3,4,5,6)
    */
    public String getFormulaId(){
        return formulaId;
    }
    
    /**
     * 设置公式表,id例:(1,2,3,4,5,6)
     * 
     * @param formulaId 要设置的公式表,id例:(1,2,3,4,5,6)
     */
    public void setFormulaId(String formulaId){
        this.formulaId = formulaId;
    }
    /**
    * 获取贷款类型(1信贷  2车贷  3房贷)
    *
    * @return 贷款类型(1信贷  2车贷  3房贷)
    */
    public Long getPtype(){
        return ptype;
    }
    
    /**
     * 设置贷款类型(1信贷  2车贷  3房贷)
     * 
     * @param ptype 要设置的贷款类型(1信贷  2车贷  3房贷)
     */
    public void setPtype(Long ptype){
        this.ptype = ptype;
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
    * 获取创建人
    *
    * @return 创建人
    */
    public String getCreateName(){
        return createName;
    }
    
    /**
     * 设置创建人
     * 
     * @param createName 要设置的创建人
     */
    public void setCreateName(String createName){
        this.createName = createName;
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
    * 获取修改人
    *
    * @return 修改人
    */
    public String getModifyName(){
        return modifyName;
    }
    
    /**
     * 设置修改人
     * 
     * @param modifyName 要设置的修改人
     */
    public void setModifyName(String modifyName){
        this.modifyName = modifyName;
    }
    /**
    * 获取是否启用(0为启用,1为不启用)
    *
    * @return 是否启用(0为启用,1为不启用)
    */
    public Long getState(){
        return state;
    }
    
    /**
     * 设置是否启用(0为启用,1为不启用)
     * 
     * @param state 要设置的是否启用(0为启用,1为不启用)
     */
    public void setState(Long state){
        this.state = state;
    }
    /**
    * 获取删除状态(0未删除,-1为删除)
    *
    * @return 删除状态(0未删除,-1为删除)
    */
    public Long getDeleteState(){
        return deleteState;
    }
    
    /**
     * 设置删除状态(0未删除,-1为删除)
     * 
     * @param deleteState 要设置的删除状态(0未删除,-1为删除)
     */
    public void setDeleteState(Long deleteState){
        this.deleteState = deleteState;
    }
    /**
    * 获取注释
    *
    * @return 注释
    */
    public String getNote(){
        return note;
    }
    
    /**
     * 设置注释
     * 
     * @param note 要设置的注释
     */
    public void setNote(String note){
        this.note = note;
    }
}


