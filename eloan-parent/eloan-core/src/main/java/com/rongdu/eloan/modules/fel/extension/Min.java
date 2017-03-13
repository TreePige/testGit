/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.extension;

import com.greenpineyu.fel.function.CommonFunction;

/**
 *	@Description 求集合中最小值
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年8月17日 下午2:42:17
 *  @since version 1.0.0
 */
public class Min extends CommonFunction{

	@Override
	public String getName() {
		return "Min";
	}

	@Override
	public Object call(Object[] arguments) {
		Long min = null;
		for (Object item : arguments) {
			Long current = Long.valueOf(item.toString());
			if (min == null || current < min) {
				min = current;
			}
		}
		return min;
	}

}
