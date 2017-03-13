package com.rongdu.eloan.modules.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 用拉的方式来获取Spring容器中的bean
 * 
 * @author FHJ
 *
 */
public class ApplicationContextHelperBean implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextHelperBean.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clz) {
		return applicationContext.getBean(clz);
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(String beanName,Class<T> clazz) {
        return applicationContext.getBean(beanName,clazz);
    }
}
