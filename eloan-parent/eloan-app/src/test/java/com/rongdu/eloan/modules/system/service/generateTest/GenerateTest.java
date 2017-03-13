package com.rongdu.eloan.modules.system.service.generateTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysDict;
import com.rongdu.eloan.modules.system.service.SysDictService;

public class GenerateTest extends ServiceBaseSpringAutoRollbackTransactionTester{
	@Autowired
	private SysDictService sysDictServiceImpl;
	@Test
	public void testGetDictList() throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		map.put("startRow", 0);
		map.put("pageSize", 25);
		//List<SysDict> list = sysDictServiceImpl.getDictList(map);
		//System.out.println(list.size());
		//long count = sysDictServiceImpl.getDictCount(map);
	}
}
