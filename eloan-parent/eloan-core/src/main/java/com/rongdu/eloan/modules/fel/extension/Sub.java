package com.rongdu.eloan.modules.fel.extension;

import java.math.BigDecimal;

import com.greenpineyu.fel.function.CommonFunction;

public class Sub extends CommonFunction {

	@Override
	public String getName() {
		return "Sub";
	}

	@Override
	public Object call(Object[] arguments) {
		int argsLen = arguments.length;  
		BigDecimal minuend = new BigDecimal(arguments[0].toString());
		for (int i = 1; i < argsLen; ++i) {
			minuend = minuend.subtract(new BigDecimal(arguments[i].toString()));
		}
		return minuend;
	}
	
}
