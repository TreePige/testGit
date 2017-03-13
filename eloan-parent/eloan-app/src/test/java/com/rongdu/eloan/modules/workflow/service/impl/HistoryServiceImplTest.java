package com.rongdu.eloan.modules.workflow.service.impl;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.workflow.service.RDHistoryService;

public class HistoryServiceImplTest extends ServiceBaseSpringAutoRollbackTransactionTester{

	@Autowired
	private RDHistoryService historyServiceImpl;
	@Test
	public void testQueryTheLatestHistoricTask() throws ServiceException {
		Map<String,Object> map = historyServiceImpl.queryTheLatestHistoricTask("111", "12");
		if(map != null && ! map.isEmpty()){
			System.out.println(map.toString());
		}
	}

}
