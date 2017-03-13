package com.rongdu.eloan.modules.system.service.impl;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.base.ServiceBaseSpringAutoRollbackTransactionTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysUserService;

public class SysUserServiceImplTest extends ServiceBaseSpringAutoRollbackTransactionTester{
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void TestGetPreviouslyAssignedUserSimpleInfoByProcessInstanceIdAndState() throws ServiceException{
		SysRole sysUser = sysUserService.getRoleById(1);
		System.out.println(sysUser.getId()+sysUser.getName());
	}
	
	@Test
	public void TestQueryTheLeastBusyUserByRoleName() throws ServiceException{
		SysUser sysUser = sysUserService.queryTheLeastBusyUserByRoleName("1", "1101", "3", "4");
	}

	@Test
	public void TestQueryTheUserWhoDidThisTask() throws ServiceException{
		Map sysUser = sysUserService.queryTheUserWhoDidThisTask("11", "qqq");
	}
	
	@Test
	public void TestGetUserByRoleAndOfficeId() throws ServiceException{
		SysUser sysUser = sysUserService.getUserByRoleAndOfficeId("123", 111L);
	}

}
