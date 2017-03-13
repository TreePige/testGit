package com.rongdu.eloan.modules.base;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * 所有Service层的测试用例都可以继承这个Base类，这样就可以进行Service组件的自动注入
 * 并且，在Junit中测试的代码在框架中会自动事务回滚,即使是Service方法完成执行成功的时候。
 * 
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:/config/spring/*-beans.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:/config/web/web-main.xml")

})
public class ServiceBaseSpringTester extends AbstractJUnit4SpringContextTests {
	@BeforeClass
	public static void beforeClass() throws Exception {
		//Log4jConfigurer.initLogging("classpath:config/log4j.properties");
	}
}

