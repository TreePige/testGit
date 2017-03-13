package com.rongdu.eloan.modules.base;


import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:/config/spring/*-beans.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:/config/web/web-main.xml")

})
public class ControllerBaseSpringTester {  
  
    @Autowired  
    protected WebApplicationContext webApplicationContext;
    
    @Autowired
	protected SysUserService sysUserService;
    
    @Autowired
    protected SysRoleService sysRoleService;
    
    protected MockMvc mockMvc;
    
    protected MockHttpSession mockSession;
    

    
    @Before
	public void before() throws FileNotFoundException {
    	Log4jConfigurer.initLogging("classpath:config/log4j.properties");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		
		mockSession = new MockHttpSession(webApplicationContext.getServletContext(), UUID.randomUUID().toString());
	}
}  
  