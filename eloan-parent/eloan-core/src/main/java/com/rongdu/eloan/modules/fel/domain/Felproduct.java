package com.rongdu.eloan.modules.fel.domain;

import java.io.Serializable;

/**
 * 公式配置,参数模块实体
 * @author 孙凯伦
 * @version 1.0
 * @since 2016-02-24 05:08:12
 */
public class Felproduct extends Common implements Serializable {
	
	private static final long serialVersionUID = 1L;
	



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
    * 注释
    */
    private String note;
    

	

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


