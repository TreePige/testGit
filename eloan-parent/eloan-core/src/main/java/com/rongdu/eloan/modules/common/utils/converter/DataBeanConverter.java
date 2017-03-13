package com.rongdu.eloan.modules.common.utils.converter;

import com.rongdu.eloan.modules.common.utils.databean.BasicDataBean;

/**
 * 类型转换器
 * 将BasicDataBean转换成Domain包的类型
 * @author FHJ
 *
 */
public abstract class DataBeanConverter<T> {
	/**
	 * 将BasicDataBean转换成Domain包的类型
	 * @param basicDataBean
	 * @return
	 */
	public abstract T convert(BasicDataBean basicDataBean);
}
