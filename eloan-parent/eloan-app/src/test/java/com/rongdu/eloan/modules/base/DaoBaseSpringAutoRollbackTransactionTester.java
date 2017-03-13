package com.rongdu.eloan.modules.base;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:/config/spring/*-beans.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:/config/web/web-main.xml")

})
public class DaoBaseSpringAutoRollbackTransactionTester extends AbstractTransactionalJUnit4SpringContextTests {
	@BeforeClass
	public static void beforeClass() throws Exception {
//		Log4jConfigurer.initLogging("classpath:config/log4j.properties");
	}
}
