package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/**
 * 
 * 组织机构表
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午4:41:50
 */
public class SysOffice implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	public SysOffice() {
		super();
	}

	/**
	 * 主键标示
	 */
	private String id;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 父级ID
	 */
	private String parentId;
	/**
	 * 省
	 */

	private String province;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String area;
	/**
	 * 机构类型:0部门，1公司
	 */
	private byte type;
	/**
	 * 机构等级
	 */
	private byte grade;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 负责人
	 */
	private String master;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 添加者
	 */
	private String addUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改者
	 */
	private String updateUser;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否删除：0不删除，1删除
	 */
	private byte isDelete;
	//排序
	private Integer sort;
	
	private String officeNumber;//机构号
	private String officeBank;//银行
	private String officeBankCard;//银行卡号
	private String officeCard;//机构代码证号

	/**
	 * 部门下的用户信息
	 */
	private List<SysUser> operatorList;

	public SysOffice(String id) {
		this.id = id;
	}

	/**
	 * 获取主键标示
	 * 
	 * @return 主键标示
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键标示
	 * 
	 * @param id 要设置的主键标示
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取机构名称
	 * 
	 * @return 机构名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置机构名称
	 * 
	 * @param name 要设置的机构名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父级ID
	 * 
	 * @return 父级ID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父级ID
	 * 
	 * @param parentId 要设置的父级ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取机构类型:0部门，1公司
	 * 
	 * @return 机构类型:0部门，1公司
	 */
	public byte getType() {
		return type;
	}

	/**
	 * 设置机构类型:0部门，1公司
	 * 
	 * @param type 要设置的机构类型:0部门，1公司
	 */
	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * 获取机构等级
	 * 
	 * @return 机构等级
	 */
	public byte getGrade() {
		return grade;
	}

	/**
	 * 设置机构等级
	 * 
	 * @param grade 要设置的机构等级
	 */
	public void setGrade(byte grade) {
		this.grade = grade;
	}

	/**
	 * 获取联系地址
	 * 
	 * @return 联系地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置联系地址
	 * 
	 * @param address 要设置的联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮政编码
	 * 
	 * @param zipCode 要设置的邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取负责人
	 * 
	 * @return 负责人
	 */
	public String getMaster() {
		return master;
	}

	/**
	 * 设置负责人
	 * 
	 * @param master 要设置的负责人
	 */
	public void setMaster(String master) {
		this.master = master;
	}

	/**
	 * 获取电话
	 * 
	 * @return 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 * 
	 * @param phone 要设置的电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取传真
	 * 
	 * @return 传真
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * 设置传真
	 * 
	 * @param fax 要设置的传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 获取邮箱
	 * 
	 * @return 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 * 
	 * @param email 要设置的邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取添加时间
	 * 
	 * @return 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param addTime 要设置的添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取添加者
	 * 
	 * @return 添加者
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * 设置添加者
	 * 
	 * @param addUser 要设置的添加者
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param updateTime 要设置的修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取修改者
	 * 
	 * @return 修改者
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置修改者
	 * 
	 * @param updateUser 要设置的修改者
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark 要设置的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取是否删除：0不删除，1删除
	 * 
	 * @return 是否删除：0不删除，1删除
	 */
	public byte getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置是否删除：0不删除，1删除
	 * 
	 * @param isDelete 要设置的是否删除：0不删除，1删除
	 */
	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the operatorList
	 */
	public List<SysUser> getOperatorList() {
		return operatorList;
	}

	/**
	 * @param userList the operatorList to set
	 */

	public void setOperatorList(List<SysUser> operatorList) {
		this.operatorList = operatorList;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getOfficeBank() {
		return officeBank;
	}

	public void setOfficeBank(String officeBank) {
		this.officeBank = officeBank;
	}

	public String getOfficeBankCard() {
		return officeBankCard;
	}

	public void setOfficeBankCard(String officeBankCard) {
		this.officeBankCard = officeBankCard;
	}

	public String getOfficeCard() {
		return officeCard;
	}

	public void setOfficeCard(String officeCard) {
		this.officeCard = officeCard;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
