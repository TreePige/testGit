package com.rongdu.eloan.modules.fel.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公式配置-------参数模块实体
 * @author 孙凯伦
 * @version 1.0
 * @since 2016-02-22 02:14:50
 */
public class FelParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private long id;
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
     * 状态
     */
    private Integer state;
    /**
     * 删除状态
     */
    private Integer deleteState;
	
    /**
    * 中文名
    */
    private String chineseName;

    /**
    * 英文名
    */
    private String englishName;

    /**
    * 数据来源
    */
    private String dataSource;

    /**
     * 数据类型 double,bigdecimal,integer
     */
    private String dataType; 

    /**
     * 获取主键
     *
     * @return 主键
     */
     public long getId() {
         return id;
     }
     /**
      * 设置主键
      * 
      * @param id 要设置的主键
      */
     public void setId(long id) {
         this.id = id;
     }

     /**
     * 获取主键
     *
     * @return 主键
     */
     public Integer getState() {
         return state;
     }
     /**
      * 设置状态
      * 
      * @param state 要设置的状态
      */
     public void setState(Integer state) {
         this.state = state;
     }
     /**
     * 获取删除状态
     *
     * @return 删除状态
     */
     public Integer getDeleteState() {
         return deleteState;
     }
     /**
      * 设置删除状态
      * 
      * @param deleteState 要设置的删除状态
      */
     public void setDeleteState(Integer deleteState) {
         this.deleteState = deleteState;
     }
     /**
      * 获取创建时间
      *
      * @return 创建时间
      */
  	public Date getCreateDate() {
  		return createDate;
  	}
      /**
       * 设置创建时间
       * 
       * @param createDate 要设置的创建时间
       */
  	public void setCreateDate(Date createDate) {
  		this.createDate = createDate;
  	}
      /**
      * 获取创建人
      *
      * @return 创建人
      */
  	public String getCreateName() {
  		return createName;
  	}
      /**
       * 设置创建人
       * 
       * @param createName 要设置的创建人
       */
  	public void setCreateName(String createName) {
  		this.createName = createName;
  	}
      /**
      * 获取修改时间
      *
      * @return 修改时间
      */
  	public Date getModifyDate() {
  		return modifyDate;
  	}
      /**
       * 设置修改时间
       * 
       * @param modifyDate 要设置的修改时间
       */
  	public void setModifyDate(Date modifyDate) {
  		this.modifyDate = modifyDate;
  	}
      /**
      * 获取修改人
      *
      * @return 修改人
      */
  	public String getModifyName() {
  		return modifyName;
  	}
      /**
       * 设置修改人
       * 
       * @param modifyName 要设置的修改人
       */
  	public void setModifyName(String modifyName) {
  		this.modifyName = modifyName;
  	}
    
    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
    * 获取中文名
    *
    * @return 中文名
    */
    public String getChineseName(){
        return chineseName;
    }
    
    /**
     * 设置中文名
     * 
     * @param chineseName 要设置的中文名
     */
    public void setChineseName(String chineseName){
        this.chineseName = chineseName;
    }

    /**
    * 获取英文名
    *
    * @return 英文名
    */
    public String getEnglishName(){
        return englishName;
    }
    
    /**
     * 设置英文名
     * 
     * @param englishName 要设置的英文名
     */
    public void setEnglishName(String englishName){
        this.englishName = englishName;
    }

    /**
    * 获取数据来源
    *
    * @return 数据来源
    */
    public String getDataSource(){
        return dataSource;
    }
    
    /**
     * 设置数据来源
     * 
     * @param dataSource 要设置的数据来源
     */
    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
    }
}