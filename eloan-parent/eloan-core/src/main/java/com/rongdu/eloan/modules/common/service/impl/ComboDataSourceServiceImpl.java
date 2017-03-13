package com.rongdu.eloan.modules.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.modules.common.dao.ComboDataSourceDao;
import com.rongdu.eloan.modules.common.service.ComboDataSourceService;

/**
 * @author Administrator
 *
 */
@Service
public class ComboDataSourceServiceImpl implements ComboDataSourceService {
	@Autowired
	private ComboDataSourceDao comboDataSourceDao;
	
	@Override
	public List<Map<String, Object>> queryDic(Map<String, Object> data) throws Exception {
		return comboDataSourceDao.queryDic(data);
	}

	@Override
	public List<Map<String, Object>> getAllProductSimpleInfos(Map<String, Object> data) throws Exception {
		return comboDataSourceDao.getAllProductSimpleInfos(data);
	}

	@Override
	public Map<String, Object> queryProductAbout(Map<String, Object> data) throws Exception {
		return comboDataSourceDao.queryProductAbout(data);
	}
}
