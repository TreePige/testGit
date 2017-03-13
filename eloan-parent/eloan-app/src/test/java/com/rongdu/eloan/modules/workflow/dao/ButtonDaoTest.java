package com.rongdu.eloan.modules.workflow.dao;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;


public class ButtonDaoTest  extends ServiceBaseSpringAutoRollbackTransactionTester{

	@Autowired
	private ButtonDao buttonDao;
	
	@Test
	public void testQueryActivityId()  {
		String taskId ="1111";
		Map<String, Object> map = buttonDao.queryActivityId(taskId);
	}

}
