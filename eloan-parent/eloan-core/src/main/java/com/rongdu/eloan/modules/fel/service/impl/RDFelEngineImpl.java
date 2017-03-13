/**Copyright (c) 杭州融都科技股份有限公司*/
package com.rongdu.eloan.modules.fel.service.impl;

import java.math.BigDecimal;

import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;
import com.rongdu.eloan.modules.fel.extension.Max;
import com.rongdu.eloan.modules.fel.extension.Min;
import com.rongdu.eloan.modules.fel.extension.Power;
import com.rongdu.eloan.modules.fel.extension.Sqrt;

/**
 *	@Description
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年8月11日 上午10:49:07
 *  @since version 1.0.0
 */
public class RDFelEngineImpl extends FelEngineImpl{
	
	public RDFelEngineImpl() {
		super();
		this.initFunction();
	}
	
	public void initFunction(){
		this.addFun(new Power());
		this.addFun(new Sqrt());
		this.addFun(new Min());
		this.addFun(new Max());
	}
	
}
