/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.extension;

import java.math.BigDecimal;

import com.greenpineyu.fel.function.CommonFunction;

/**
 *	@Description 次方 2^6
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年8月17日 下午2:50:27
 *  @since version 1.0.0
 */
public class Power extends CommonFunction{

	@Override
	public String getName() {
		return "Power";
	}

	@Override
	public Object call(Object[] arguments) {
		BigDecimal radix = new BigDecimal(arguments[0].toString());
		Integer power = Integer.valueOf(arguments[1].toString());
		return radix.pow(power);
	}

}
