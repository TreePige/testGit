package com.rongdu.eloan.modules.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.system.domain.SysOfficeUserCount;

/**
 * 
 * 机构人数DAO接口
 * @version 1.0
 * @author yaoyy
 * @created 2015-12-24
 */
@RDBatisDao
public interface SysOfficeUserCountDao extends BaseDao<SysOfficeUserCount,Long> {
	
	/**
	 * 获取机构人数
	 * @param officeId
	 */
	public List<SysOfficeUserCount> getCountList() throws PersistentDataException;
	
}
