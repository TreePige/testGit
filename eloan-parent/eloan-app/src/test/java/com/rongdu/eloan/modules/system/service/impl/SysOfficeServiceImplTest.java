package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysOffice;
import com.rongdu.eloan.modules.system.service.SysOfficeService;

public class SysOfficeServiceImplTest extends ServiceBaseSpringAutoRollbackTransactionTester{

	@Autowired
	private SysOfficeService sysOfficeServiceImpl;
	
	@Test
	public void testGetOfficeTreeList() throws ServiceException{
		List<Map<String,Object>> list = sysOfficeServiceImpl.getOfficeTreeList();
		System.out.println(list);
	}
	
	@Test
	public void testGetOfficeById() throws ServiceException{
		long id = 1104L;
		SysOffice office =  sysOfficeServiceImpl.getOfficeById(id);
		System.out.println(office);
	}
	
	@Test
	public void testGetOfficeInfoById() throws ServiceException{
		long id = 1101L;
		Map<String,Object> map = sysOfficeServiceImpl.getOfficeInfoById(id);
		System.out.println(map);
	}
	
	@Test
	public void testGetIdByParentId() throws ServiceException{
		String parentId = "11";
		String id = sysOfficeServiceImpl.getIdByParentId(parentId);
		System.out.println(id);
	}
	
	@Test
	public void testOfficeAdd() throws ServiceException{
		SysOffice office = new SysOffice();
		office.setName("t1");
		office.setId("1104");
		office.setParentId("11");
		long num = sysOfficeServiceImpl.officeAdd(office);
	}
	
	@Test
	public void testUpdateSysOfficeById() throws ServiceException{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "分控部");
		map.put("id", 1102);
		Boolean num = sysOfficeServiceImpl.updateSysOfficeById(map);
	}
	
	@Test
	public void testDeleteOfficeAll() throws ServiceException{
		long id = 1102L;
		sysOfficeServiceImpl.deleteOfficeAll(id);
	}
	
	@Test
	public void testGetListByOfficeId() throws ServiceException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("isDelete", 0);
		param.put("id", "11");
		List<SysOffice> list  = sysOfficeServiceImpl.getListByOfficeId(param);
		System.out.println(list);
	}
	
}
