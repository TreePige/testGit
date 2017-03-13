package com.rongdu.eloan.modules.fel.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公式配置,参数模块实体
 * @author 孙凯伦
 * @version 1.0
 * @since 2016-02-24 09:44:15
 */
public class FelFormula implements Serializable {
	
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
    * 单位
    */
    private String unit;

    /**
    * 数据来源(1为系统计算,2为人工录入)
    */
    private Integer dataSource;

    /**
    * 公式内容,若内嵌公式,此为假公式    用于计算
    */
    private String formula;
    
    /**
     * 中文公式 用于显示
     */
    private String formulaChinese;

    /**
    * 注释
    */
    private String note;

    /**
    * 参数表,id例:(1,2,3,4,5,6,)
    */
    private String paramId;

    /**
    * 是否内嵌公式(0:否,1为是)
    */
    private Byte nestedState;

    /**
    * 内嵌公式,专属,此为真公式
    */
    private String nestedFormula;

    /**
    * 类型表的id
    */
    private Integer typeId;

    /**
    * 页面的,公式json
    */
    private String formulaJson;

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
    * 获取单位
    *
    * @return 单位
    */
    public String getUnit(){
        return unit;
    }
    
    /**
     * 设置单位
     * 
     * @param unit 要设置的单位
     */
    public void setUnit(String unit){
        this.unit = unit;
    }

    /**
    * 获取数据来源(1为系统计算,2为人工录入)
    *
    * @return 数据来源(1为系统计算,2为人工录入)
    */
    public Integer getDataSource(){
        return dataSource;
    }
    
    /**
     * 设置数据来源(1为系统计算,2为人工录入)
     * 
     * @param dataSource 要设置的数据来源(1为系统计算,2为人工录入)
     */
    public void setDataSource(Integer dataSource){
        this.dataSource = dataSource;
    }

    /**
    * 获取公式内容,若内嵌公式,此为假公式
    *
    * @return 公式内容,若内嵌公式,此为假公式
    */
    public String getFormula(){
        return formula;
    }
    
    /**
     * 设置公式内容,若内嵌公式,此为假公式
     * 
     * @param formula 要设置的公式内容,若内嵌公式,此为假公式
     */
    public void setFormula(String formula){
        this.formula = formula;
    }

    public String getFormulaChinese() {
		return formulaChinese;
	}

	public void setFormulaChinese(String formulaChinese) {
		this.formulaChinese = formulaChinese;
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

    /**
    * 获取参数表,id例:(1,2,3,4,5,6,)
    *
    * @return 参数表,id例:(1,2,3,4,5,6,)
    */
    public String getParamId(){
        return paramId;
    }
    
    /**
     * 设置参数表,id例:(1,2,3,4,5,6,)
     * 
     * @param paramId 要设置的参数表,id例:(1,2,3,4,5,6,)
     */
    public void setParamId(String paramId){
        this.paramId = paramId;
    }

    /**
    * 获取是否内嵌公式(0:否,1为是)
    *
    * @return 是否内嵌公式(0:否,1为是)
    */
    public Byte getNestedState(){
        return nestedState;
    }
    
    /**
     * 设置是否内嵌公式(0:否,1为是)
     * 
     * @param nestedState 要设置的是否内嵌公式(0:否,1为是)
     */
    public void setNestedState(Byte nestedState){
        this.nestedState = nestedState;
    }

    /**
    * 获取内嵌公式,专属,此为真公式
    *
    * @return 内嵌公式,专属,此为真公式
    */
    public String getNestedFormula(){
        return nestedFormula;
    }
    
    /**
     * 设置内嵌公式,专属,此为真公式
     * 
     * @param nestedFormula 要设置的内嵌公式,专属,此为真公式
     */
    public void setNestedFormula(String nestedFormula){
        this.nestedFormula = nestedFormula;
    }

    /**
    * 获取类型表的id
    *
    * @return 类型表的id
    */
    public Integer getTypeId(){
        return typeId;
    }
    
    /**
     * 设置类型表的id
     * 
     * @param typeId 要设置的类型表的id
     */
    public void setTypeId(Integer typeId){
        this.typeId = typeId;
    }

    /**
    * 获取页面的,公式json
    *
    * @return 页面的,公式json
    */
    public String getFormulaJson(){
        return formulaJson;
    }
    
    /**
     * 设置页面的,公式json
     * 
     * @param formulaJson 要设置的页面的,公式json
     */
    public void setFormulaJson(String formulaJson){
        this.formulaJson = formulaJson;
    }
}