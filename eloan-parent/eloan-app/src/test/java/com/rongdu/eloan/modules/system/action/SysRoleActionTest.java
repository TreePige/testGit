package com.rongdu.eloan.modules.system.action;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysUser;

public class SysRoleActionTest extends ControllerBaseSpringTester {
	
	@Before
    public void initSession() throws ServiceException {
        SysUser user = sysUserService.getUserById(1);
        mockSession.setAttribute("SysUser", user);
        mockSession.setAttribute("roleId", 1);
    }
	
	@Test
	public void testGetRoleList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/getRoleList.htm").session(this.mockSession)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testRoleDelete() throws Exception {
		String key = "3";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/general/roleDelete.htm").session(this.mockSession).param("key",key)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testGetSysUserList() throws Exception {
		String start = "1";
		String limit = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/getSysRoleList.htm").session(this.mockSession).param("start",start).param("limit", limit)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testAddRoleActionCreate() throws Exception {
		String status = "create";
		String data = "{\"name\":\"测试角色\",\"nid\":\"testid\",\"addUser\":\"system\",\"remark\":\"测试角色添加\",\"isDelete\":\"0\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/addRole.htm").session(this.mockSession).param("form",data).param("status", status)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testInsertByMap() throws Exception {
		String data = "{\"name\":\"测试角色\",\"nid\":\"testid\",\"add_user\":\"system\",\"remark\":\"测试角色添加\",\"is_delete\":\"0\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/insertByMap.htm").session(this.mockSession).param("data",data)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testAddRoleActionUpdate() throws Exception {
		String status = "update";
		String data = "{\"id\":\"4\",\"name\":\"test\",\"nid\":\"test\",\"addUser\":\"system\",\"remark\":\"测试角色更新\",\"isDelete\":\"0\",\"updateUser\":\"admin\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/addRole.htm").session(this.mockSession).param("form",data).param("status", status)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testGetByUserPassRolesList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getByUserPassRolesList.htm").session(this.mockSession).param("username","system").param("password", "123456")).andDo(MockMvcResultHandlers.print());
	}
}
