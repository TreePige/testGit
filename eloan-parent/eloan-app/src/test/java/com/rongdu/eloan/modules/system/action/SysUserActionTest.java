package com.rongdu.eloan.modules.system.action;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysUser;

public class SysUserActionTest extends ControllerBaseSpringTester {
	
	@Before
    public void initSession() throws ServiceException {
        SysUser user = sysUserService.getUserById(1);
        mockSession.setAttribute("SysUser", user);
        mockSession.setAttribute("roleId", 1);
    }
	
	@Test
	public void testModifyPassword() throws Exception {
		String user = "{\"password\":\"123456\",\"password1\":\"666666\",\"password2\":\"666666\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/modifyPassword.htm").session(this.mockSession).param("user",user)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testUserAddUpdate() throws Exception {
		String user = "{\"id\":\"18\",\"rid\":\"\",\"roleId\":[1],\"phone\":\"15068963247\",\"email\":\"1@163.com\",\"mobile\":\"15068963247\",\"name\":\"测试更新账号\",\"number\":\"0578\",\"officeId\":\"11\",\"officeOver\":\"\",\"position\":\"0\",\"remark\":\"\",\"userName\":\"测试更新账号\",\"officeName\":\"普惠快融信息服务（北京）有限公司总公司\"}";
		String status = "update";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/adduser.htm").session(this.mockSession).param("user",user).param("status", status)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testUserAddCreate() throws Exception {
		String user = "{\"rid\":\"\",\"roleId\":[1],\"phone\":\"15068963247\",\"email\":\"1@163.com\",\"mobile\":\"15068963247\",\"name\":\"测试创建账号\",\"number\":\"0578\",\"officeId\":\"11\",\"officeOver\":\"\",\"position\":\"0\",\"remark\":\"\",\"userName\":\"测试创建账号\",\"officeName\":\"普惠快融信息服务（北京）有限公司总公司\"}";
		String status = "create";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/adduser.htm").session(this.mockSession).param("user",user).param("status", status)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testUserEditPage() throws Exception {
		String id = "18";
		String status = "editpassword";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/userEditPage.htm").session(this.mockSession).param("id",id).param("status", status)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testGetSysUserList() throws Exception {
		String start = "0";
		String limit = "2";
		String user = "{\"number\":\"\",\"name\":\"\",\"status\":\"\",\"roleId\":\"\",\"officeName\":\"\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/general/getSysUserList.htm").session(this.mockSession).param("start",start).param("limit", limit).param("user", user)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testGetUserMessage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/getUserMessage.htm").session(this.mockSession)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testGetCustomerServiceStaffList() throws Exception {
		String nid = "system";
		mockMvc.perform(MockMvcRequestBuilders.get("/modules/system/getcustomerservicestafflist.htm").session(this.mockSession).param("nid", nid)).andDo(MockMvcResultHandlers.print());
	}
}
