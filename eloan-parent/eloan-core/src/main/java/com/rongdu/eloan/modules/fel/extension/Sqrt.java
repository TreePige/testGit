/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.extension;

import com.greenpineyu.fel.function.CommonFunction;

/**
 *	@Description 开方
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年8月17日 下午2:56:36
 *  @since version 1.0.0
 */
public class Sqrt extends CommonFunction{

	@Override
	public String getName() {
		return "Sqrt";
	}

	@Override
	public Object call(Object[] arguments) {
		Double radix = Double.parseDouble(arguments[0].toString());
		return Math.sqrt(radix);
	}

}
