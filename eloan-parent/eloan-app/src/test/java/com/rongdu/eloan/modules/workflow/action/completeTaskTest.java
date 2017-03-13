package com.rongdu.eloan.modules.workflow.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;

public class completeTaskTest extends ControllerBaseSpringTester{

	@Before
	public void initSession() throws ServiceException {
		SysUser user = new SysUser();
		user.setUserName("用户a");
		mockSession.setAttribute("SysUser", user);

	}
	
	/**
	 * 完成任务
	 * @throws Exception 
	 */
	@Test
	public void testCompleteTask() throws Exception {
		String taskId = "1045016";
		String serviceVariables = "";
		String processVariables = "{\"comment\":\"pass\"}";
		mockMvc.perform(
				get("/modules/workflow/controller/ProcessTaskController/completeTask.htm")
						.session(mockSession).param("taskId", taskId)
						.param("serviceVariables", serviceVariables)
						.param("processVariables", processVariables))
				.andExpect(status().isOk()).andDo(print());
	}
}
