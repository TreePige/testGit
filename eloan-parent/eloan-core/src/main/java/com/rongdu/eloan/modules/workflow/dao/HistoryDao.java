package com.rongdu.eloan.modules.workflow.dao;

import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

@RDBatisDao
public interface HistoryDao extends BaseDao<T,Long>{

	Map<String, Object> queryTheLatestHistoricTask(Map<String, Object> map);
}
