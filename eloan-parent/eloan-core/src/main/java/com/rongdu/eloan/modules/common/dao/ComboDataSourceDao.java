package com.rongdu.eloan.modules.common.dao;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@RDBatisDao
public interface ComboDataSourceDao extends BaseDao{
	List<Map<String, Object>> getAllProductSimpleInfos(Map<String, Object> data);

	Map<String, Object> queryProductAbout(Map<String, Object> data);

	List<Map<String, Object>> queryDic(Map<String, Object> data);
}
