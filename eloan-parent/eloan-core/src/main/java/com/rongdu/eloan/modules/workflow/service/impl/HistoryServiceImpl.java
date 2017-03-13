package com.rongdu.eloan.modules.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.workflow.dao.HistoryDao;
import com.rongdu.eloan.modules.workflow.service.RDHistoryService;

@Service
public class HistoryServiceImpl implements RDHistoryService {
	
	@Autowired
	private HistoryDao historyDao;
	
	@Override
	public Map<String, Object> queryTheLatestHistoricTask(
			String processInstanceId, String taskDefinition)throws ServiceException {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("processInstanceId", processInstanceId);
		map.put("taskDefinition", taskDefinition);
		Map<String, Object> data = null;
		try {
			data = historyDao.queryTheLatestHistoricTask(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return data;
	}

}
