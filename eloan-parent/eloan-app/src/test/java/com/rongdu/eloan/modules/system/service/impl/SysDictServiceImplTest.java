package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.common.domain.PageBean;
import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysDict;
import com.rongdu.eloan.modules.system.domain.SysDictDetail;
import com.rongdu.eloan.modules.system.service.SysDictDetailService;
import com.rongdu.eloan.modules.system.service.SysDictService;

public class SysDictServiceImplTest extends ServiceBaseSpringAutoRollbackTransactionTester{

	@Autowired
	private SysDictService sysDictServiceImpl;
	@Autowired
	private SysDictDetailService sysDictDetailService;
	@Test
	public void testGetDictList() throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		map.put("startRow", 0);
		map.put("pageSize", 25);
		//List<SysDict> list = sysDictServiceImpl.getDictList(map);
		long count = sysDictServiceImpl.getDictCount(map);
	}

	@Test
	public void testDictDetailList() throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		PageBean pb = sysDictServiceImpl.getDictDetailList(map, 1, 10);
	}
	
	@Test
	public void testAddOrModify() throws ServiceException {
//		String status = "create";
		String status = "update";
		SysDict sysDict =new SysDict();
		sysDict.setId(5L);
		sysDict.setCode("2");
		sysDict.setName("t2");
		sysDict.setRemark("rrrrr");
		sysDict.setSort(22);
		sysDictServiceImpl.addOrModify(sysDict, status);
	}
	
	@Test
	public void testGetItemCountMap() throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		map.put("parentId", 5);
		long num = sysDictDetailService.getItemCountMap(map);
		boolean flag = sysDictServiceImpl.deleteDict(5L);
	}
	
	@Test
	public void testDetailAddOrModify() throws ServiceException {
//		String status = "create";
		String status = "update";
		
		SysDictDetail dictDetail =new SysDictDetail();
		dictDetail.setId(1L);
		dictDetail.setItemCode("2");
		dictDetail.setItemValue("t2");
		dictDetail.setParentId(3434L);
		sysDictDetailService.addOrModify(dictDetail, status);
	}
	
	@Test
	public void testDeleteSysDictDetail() throws ServiceException {
		long id = 1L;
		boolean num = sysDictDetailService.deleteSysDictDetail(id);
	}
	
	@Test
	public void testDictListAndCount() throws ServiceException {
		Map<String,Object> map = new HashMap<>();
		PageBean pb = sysDictServiceImpl.getDictListAndCount(map, 1, 10);
	}
	
	@Test
	public void testDetDictsCache() throws ServiceException {
		List<Map<String, Object>> pb = sysDictServiceImpl.getDictsCache("109");
		System.out.println(pb);
	}

}
