/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.system.action;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rongdu.eloan.modules.base.ControllerBaseSpringTester;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月7日 上午11:08:39
 *  @since version 1.0.0
 */
public class SysDicActionTest extends ControllerBaseSpringTester {
	@Test
	public void testQueryAllDic() throws Exception {
		mockMvc.perform(
					MockMvcRequestBuilders.get("/dicAction/queryAllDic.htm")
						.session(this.mockSession)
						)
				.andDo(MockMvcResultHandlers.print());
	}
}
