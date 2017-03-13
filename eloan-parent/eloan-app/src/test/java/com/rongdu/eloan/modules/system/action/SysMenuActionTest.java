/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.system.action;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysUser;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月6日 上午10:34:54
 *  @since version 1.0.0
 */
public class SysMenuActionTest extends ControllerBaseSpringTester {
	
	@Before
    public void initSession() throws ServiceException {
        SysUser user = sysUserService.getUserById(1);
        mockSession.setAttribute("SysUser", user);
        mockSession.setAttribute("roleId", 1l);
    }
	
	@Test
	public void testFetchRoleMenu() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/sconfig/fetchRoleMenu.htm")
					.session(this.mockSession)
					.param("sysType", "1")
					)
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testFetchRoleMenuHas() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/sconfig/fetchRoleMenuHas.htm")
					.session(this.mockSession)
					.param("roleId", "1")
					)
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testFetchAllMenu() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/sconfig/fetchAllMenu.htm")
					.session(this.mockSession)
					)
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testCreate() throws Exception {
		String menu = "{\"scriptid\":\"test\",\"text\":\"测试\",\"parentId\":9999,\"iconCls\":\"icon\",\"remark\":\"ds\",\"sort\":\"1\",\"isDelete\":0,\"isMenu\":1,\"leaf\":true,\"level\":2}";
		String status = "create";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/menu/update.htm")
					.session(this.mockSession)
					.param("menu", menu)
					.param("status", status)
					)
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testUpdate() throws Exception {
		String menu = "{\"id\":\"32\",\"scriptid\":\"test\",\"text\":\"测试1\",\"parentId\":9999,\"iconCls\":\"icon\",\"remark\":\"ds\",\"sort\":\"1\",\"isDelete\":0,\"isMenu\":1,\"leaf\":true,\"level\":2}";
		String status = "update";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/menu/update.htm")
					.session(this.mockSession)
					.param("menu", menu)
					.param("status", status)
					)
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testMenucombo() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/sconfig/menucombo.htm")
					.session(this.mockSession)
					)
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testGetMenuInfoById() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/getMenuInfoById.htm")
					.session(this.mockSession)
					.param("id", "6")
					)
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/menu/delete.htm")
					.session(this.mockSession)
					.param("id", "43")
					)
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testSaveOrUpdateRoleMenus() throws Exception {
		String menu = "[2,4]";
		String roleId = "6";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/modules/system/sconfig/saveOrUpdateRoleMenus.htm")
					.session(this.mockSession)
					.param("checkedkey", menu)
					.param("roleId", roleId)
					)
			.andDo(MockMvcResultHandlers.print());
	}
}
