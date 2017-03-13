/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.extension;

import com.greenpineyu.fel.function.CommonFunction;

/**
 *	@Description 取集合最大值
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年8月17日 下午3:02:37
 *  @since version 1.0.0
 */
public class Max extends CommonFunction{

	@Override
	public String getName() {
		return "Max";
	}

	@Override
	public Object call(Object[] arguments) {
		Long max = null;
		for (Object item : arguments) {
			Long current = Long.valueOf(item.toString());
			if (max == null || current > max) {
				max = current;
			}
		}
		return max;
	}

}
