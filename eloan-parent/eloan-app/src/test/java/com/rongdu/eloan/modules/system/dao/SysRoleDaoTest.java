/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.system.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月5日 上午10:36:31
 *  @since version 1.0.0
 */
public class SysRoleDaoTest extends ServiceBaseSpringAutoRollbackTransactionTester {
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Test
	public void testGetByUserPassRolesList () {
		
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("username", "system");
		data.put("password", "fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha");

		System.out.println(sysRoleDao.getByUserPassRolesList(data).toString());
	}
}
