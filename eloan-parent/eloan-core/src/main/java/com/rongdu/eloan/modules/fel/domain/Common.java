package com.rongdu.eloan.modules.fel.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User:    孙凯伦
 * email:   skl@erongdu.com
 * Date:    2016/1/15
 * Time:    10:52
 * details: 继承的公共类
 */
public class Common implements Serializable {
    /**
     * 主键
     */
    private String id;
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
     * 跳转到第几页
     */
    private Integer start;
    /**
     * 每页显示几条
     */
    private Integer limit;
    /**
     * 排序的列
     */
    private String fields;
    /**
     * 升序还是降序
     */
    private String rule;
    /**
     * 直接执行的sql,动态查询最少一个条件
     */
    private String whereSql;
    /**
    * 获取主键
    *
    * @return 主键
    */
    public String getId() {
        return id;
    }
    /**
     * 设置主键
     * 
     * @param id 要设置的主键
     */
    public void setId(String id) {
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
    * 获取跳转页
    *
    * @return 跳转页
    */
    public Integer getStart() {
        return start;
    }
    /**
     * 设置跳转页
     * 
     * @param start 要设置的跳转页
     */
    public void setStart(Integer start) {
        this.start = start;
    }
    /**
    * 获取每页几条
    *
    * @return 每页几条
    */
    public Integer getLimit() {
        return limit;
    }
    /**
     * 设置每页几条
     * 
     * @param limit 要设置的每页几条
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    /**
    * 获取排序列
    *
    * @return 排序列
    */
    public String getFields() {
        return fields;
    }
    /**
     * 设置排序列
     * 
     * @param fields 要设置的排序列
     */
    public void setFields(String fields) {
        this.fields = fields;
    }
    /**
    * 获取升序还是降序
    *
    * @return 升序还是降序
    */
    public String getRule() {
        return rule;
    }
    /**
     * 设置升序还是降序
     * 
     * @param rule 要设置的升序还是降序
     */
    public void setRule(String rule) {
        this.rule = rule;
    }
    /**
    * 获取执行的sql
    *
    * @return 执行的sql
    */
    public String getWhereSql() {
        return whereSql;
    }
    /**
     * 设置执行的sql
     * 
     * @param whereSql 要设置的执行的sql
     */
    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Common [id=");
		builder.append(id);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", createName=");
		builder.append(createName);
		builder.append(", modifyDate=");
		builder.append(modifyDate);
		builder.append(", modifyName=");
		builder.append(modifyName);
		builder.append(", state=");
		builder.append(state);
		builder.append(", deleteState=");
		builder.append(deleteState);
		builder.append(", start=");
		builder.append(start);
		builder.append(", limit=");
		builder.append(limit);
		builder.append(", fields=");
		builder.append(fields);
		builder.append(", rule=");
		builder.append(rule);
		builder.append(", whereSql=");
		builder.append(whereSql);
		builder.append("]");
		return builder.toString();
	}
    
}
