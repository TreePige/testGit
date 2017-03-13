package com.rongdu.eloan.modules.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 营业厅费率配置-车贷的实体
 * @author wgc
 * @version 1.0
 * @since 2015-05-25
 */
public class SysCarConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 是否删除0不删除 1删除
     */
    private Byte isDelete;
    /**
     * 营业厅ID
     */
    private String officeId;
    /**
     * 营业厅名称
     */
    private String officeName;
    /**
     * 停车费 X/月
     */
    private BigDecimal parkingFee;
    /**
     * GPS安装费 X/次
     */
    private BigDecimal gpsInstallFee;
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Long getId(){
        return id;
    }
    
    /**
     * 设置主键
     * 
     * @param id 要设置的主键
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取创建人
     * 
     * @return 创建人
     */
    public String getCreator(){
        return creator;
    }
    
    /**
     * 设置创建人
     * 
     * @param creator 要设置的创建人
     */
    public void setCreator(String creator){
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
     * 获取是否删除0不删除 1删除
     * 
     * @return 是否删除0不删除 1删除
     */
    public Byte getIsDelete(){
        return isDelete;
    }
    
    /**
     * 设置是否删除0不删除 1删除
     * 
     * @param isDelete 要设置的是否删除0不删除 1删除
     */
    public void setIsDelete(Byte isDelete){
        this.isDelete = isDelete;
    }
    /**
     * 获取营业厅ID
     * 
     * @return 营业厅ID
     */
    public String getOfficeId(){
        return officeId;
    }
    
    /**
     * 设置营业厅ID
     * 
     * @param officeId 要设置的营业厅ID
     */
    public void setOfficeId(String officeId){
        this.officeId = officeId;
    }
    /**
     * 获取营业厅名称
     * 
     * @return 营业厅名称
     */
    public String getOfficeName(){
        return officeName;
    }
    
    /**
     * 设置营业厅名称
     * 
     * @param officeName 要设置的营业厅名称
     */
    public void setOfficeName(String officeName){
        this.officeName = officeName;
    }
    /**
     * 获取停车费 X/月
     * 
     * @return 停车费 X/月
     */
    public BigDecimal getParkingFee(){
        return parkingFee;
    }
    
    /**
     * 设置停车费 X/月
     * 
     * @param parkingFee 要设置的停车费 X/月
     */
    public void setParkingFee(BigDecimal parkingFee){
        this.parkingFee = parkingFee;
    }
    /**
     * 获取GPS安装费 X/次
     * 
     * @return GPS安装费 X/次
     */
    public BigDecimal getGpsInstallFee(){
        return gpsInstallFee;
    }
    
    /**
     * 设置GPS安装费 X/次
     * 
     * @param gpsInstallFee 要设置的GPS安装费 X/次
     */
    public void setGpsInstallFee(BigDecimal gpsInstallFee){
        this.gpsInstallFee = gpsInstallFee;
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
}


