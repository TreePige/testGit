package com.rongdu.eloan.modules.fel.extension;

import java.math.BigDecimal;

import com.greenpineyu.fel.function.CommonFunction;

/**
 * @Description 累加求和
 * @author limf 
 * @CreatTime 2016-11-18
 * @version 1.0.0
 *
 */
public class Sum extends CommonFunction {

	@Override
	public String getName() {
		return "Sum";
	}

	@Override
	public Object call(Object[] arguments) {
		int argsLen = arguments.length;  
		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < argsLen; ++i) {
			sum = sum.add(new BigDecimal(arguments[i].toString()));
		}
		return sum;
	}

}
