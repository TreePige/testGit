package com.rongdu.eloan.modules.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.system.domain.SysAlarmSetting;
import com.rongdu.eloan.modules.system.domain.SysRiskControl;



@RDBatisDao
public interface SysRiskControlDao extends BaseDao<SysRiskControl,Long> {
	/**
	 * 根据ID更新 通用更新
	 * 
	 * @param map
	 * @return Boolean
	 * @throws PersistentDataException
	 */
	Boolean updateSysRiskControlById(Map<String, Object> data)
			throws PersistentDataException;

	/**
	 * 分页查询出的列表
	 * 
	 * @param mapdata
	 * @return 分页列表
	 * @throws PersistentDataException
	 */
	List<? extends SysRiskControl> getSysRiskControlPageList(
			Map<String, Object> data) throws PersistentDataException;

	/**
	 * 获取总记录数
	 * 
	 * @param param
	 * @return 记录数
	 * @throws PersistentDataException
	 */
	int getSysRiskControlCount(Map<String, Object> data)
			throws PersistentDataException;

	List<Map> query(Integer pageNum, Integer pageSize,
			Map<String, Object> params) throws PersistentDataException;

	void deleteByIds(String ids) throws PersistentDataException;

//	List<Map> queryRisk(Integer pageNum, Integer pageSize,
//			Map<String, Object> params) throws PersistentDataException;

}
