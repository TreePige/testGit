package com.rongdu.eloan.modules.workflow.dao;

import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;

@RDBatisDao
public interface ButtonDao extends BaseDao<T,Long>{
	/**
	 * 根据任务ID 查询当前活动的activityID
	 * @description
	 * @param taskId
	 * @return
	 * @author wangdk
	 * @return Map<String,Object>
	 * @since  1.0.0
	*/
	Map<String,Object> queryActivityId(String taskId) ;
}
