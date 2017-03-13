package com.rongdu.eloan.modules.system.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.system.domain.SysOffice;

public class SysOfficeDaoTest extends ServiceBaseSpringAutoRollbackTransactionTester{

	@Autowired
	private SysOfficeDao sysOfficeDao;
	
	@Test
	public void testGetOfficeTreeList() throws PersistentDataException{
		List<Map<String,Object>> list = sysOfficeDao.getOfficeTreeList();
		System.out.println(list);
	}
	
	@Test
	public void testInsert() throws PersistentDataException{
		SysOffice office = new SysOffice();
		office.setName("t1");
		office.setId("1104");
		office.setParentId("11");
		
		long num = sysOfficeDao.insert(office);
		System.out.println(num);
	}

}
