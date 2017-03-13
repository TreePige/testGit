/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.system.action;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;

/**
 * @Description
 * @author liyc,lyc1@erongdu.com
 * @CreatTime 2016年7月5日 下午1:59:02
 * @since version 1.0.0
 */
public class LoginActionTest extends ControllerBaseSpringTester {

	@Test
	public void testAjaxLoginProcess() throws Exception {
		mockMvc.perform(
					MockMvcRequestBuilders.get("/ajaxLoginProcess.htm")
						.session(this.mockSession)
						.param("username", "system")
						.param("password", "123456")
						.param("roleId", "1"))
				.andDo(MockMvcResultHandlers.print());
	}
}
