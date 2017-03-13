package com.rongdu.eloan.common.utils.fel;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

/**
 * 
 * @Description 公式配置器,工具类
 * @author 孙凯伦,skl@erongdu.com
 * @CreatTime 2016年2月22日 下午4:03:13
 * @since version 1.0.0
 */
public class Formula {
	/**
	 * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
	 */
	private static Formula instance = null;

	/**
	 * 私有构造方法，防止被实例化 Formula.
	 */
	private Formula() {
	};

	/**
	 * @description 静态工程方法，创建实例
	 * @return
	 * @author 孙凯伦
	 * @return Formula
	 * @since 1.0.0
	 */
	public static Formula getInstance() {
		// 判断对象是否创建
		if (instance == null) {
			syncInit();
		}
		// 对象创建过,直接返回
		return instance;
	}

	/**
	 * 
	 * @description 同步方法,创建对象
	 * @author 孙凯伦
	 * @return void
	 * @since 1.0.0
	 */
	private static synchronized void syncInit() {
		// 判断对象是否创建
		if (instance == null) {
			instance = new Formula();
		}
	}

	/**
	 * @description 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 * @return
	 * @author 孙凯伦
	 * @return Object
	 * @since 1.0.0
	 */
	public Object readResolve() {
		return instance;
	}

	/**
	 * 
	 * @description 计算公式公共调用
	 * @param key
	 *            数据库公式级内的,参数级
	 * @param param
	 *            公式所需要的参数
	 * @param Formula
	 *            公式内容
	 * @return 计算结果
	 * @author 孙凯伦
	 * @return Object
	 * @since 1.0.0
	 */
	public Object calculate(Map<String, Object> key, Map<String, Object> param,
			String formula) {
		if (check(key, param)) {
			// fel框架主类
			FelEngine fel = new FelEngineImpl();
			// 设置计算参数
			FelContext ctx = fel.getContext();
			// 循环遍历
			for (String k : key.keySet()) {
				// 赋值参数
				ctx.set(k, new BigDecimal(String.valueOf(param.get(k))));
			}
			// 返回计算结果
			return fel.eval(formula);
		} else {
			try {
				throw new Exception("警告:键和值,其中有一个为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @description 检查传入的
	 * @param key
	 * @param param
	 * @return
	 * @author 孙凯伦
	 * @return boolean
	 * @since 1.0.0
	 */
	private boolean check(Map<String, Object> key, Map<String, Object> param) {
		boolean b = true;
		// 循环遍历
		for (String k : key.keySet()) {
			// 判断
			if (param.get(k) == null)
				b = false;
		}
		return b;
	}

}
