package com.rongdu.eloan.modules.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件类型管理实体
 * @author lyc
 * @version 1.0
 * @since 2016-07-15 10:18:51
 */
public class PubProfile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
    * id
    */
    private Long id;
    /**
    * 父级id
    */
    private int parentId;
    /**
    * 所属产品id
    */
    private int productId;
    /**
    * 所属业务类型
    */
    private String ptype;
    /**
    * 必填项
    */
    private int isRequired;
    /**
    * 资料要求备注
    */
    private String remark;
    /**
    * 资料要求备注
    */
    private String remark2;
    /**
    * 排序
    */
    private int sort;
    /**
    * 是否启动
    */
    private int disable;
    
    /**
    * 获取id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }
    
    /**
     * 设置id
     * 
     * @param long 要设置的id
     */
    public void setId(Long id){
        this.id = id;
    }
    
    /**
    * 获取父级id
    *
    * @return 父级id
    */
    public int getParentId(){
        return parentId;
    }
    
    /**
     * 设置父级id
     * 
     * @param parentId 要设置的父级id
     */
    public void setParentId(int parentId){
        this.parentId = parentId;
    }

    /**
    * 获取所属产品id
    *
    * @return 所属产品id
    */
    public int getProductId(){
        return productId;
    }
    
    /**
     * 设置所属产品id
     * 
     * @param productId 要设置的所属产品id
     */
    public void setProductId(int productId){
        this.productId = productId;
    }

    /**
    * 获取所属业务类型
    *
    * @return 所属业务类型
    */
    public String getPtype(){
        return ptype;
    }
    
    /**
     * 设置所属业务类型
     * 
     * @param ptype 要设置的所属业务类型
     */
    public void setPtype(String ptype){
        this.ptype = ptype;
    }

    /**
    * 获取必填项
    *
    * @return 必填项
    */
    public int getIsRequired(){
        return isRequired;
    }
    
    /**
     * 设置必填项
     * 
     * @param isRequired 要设置的必填项
     */
    public void setIsRequired(int isRequired){
        this.isRequired = isRequired;
    }

    /**
    * 获取资料要求备注
    *
    * @return 资料要求备注
    */
    public String getRemark(){
        return remark;
    }
    
    /**
     * 设置资料要求备注
     * 
     * @param remark 要设置的资料要求备注
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取资料要求备注
    *
    * @return 资料要求备注
    */
    public String getRemark2(){
        return remark2;
    }
    
    /**
     * 设置资料要求备注
     * 
     * @param remark2 要设置的资料要求备注
     */
    public void setRemark2(String remark2){
        this.remark2 = remark2;
    }

    /**
    * 获取排序
    *
    * @return 排序
    */
    public int getSort(){
        return sort;
    }
    
    /**
     * 设置排序
     * 
     * @param sort 要设置的排序
     */
    public void setSort(int sort){
        this.sort = sort;
    }

    /**
    * 获取是否启动
    *
    * @return 是否启动
    */
    public int getDisable(){
        return disable;
    }
    
    /**
     * 设置是否启动
     * 
     * @param disable 要设置的是否启动
     */
    public void setDisable(int disable){
        this.disable = disable;
    }

}