package com.rongdu.eloan.common.utils;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;

/**
 * 参数检查者
 */
public abstract class ParamChecker {
	
	private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	public static void checkString(Object value,String name){
	    checkEmpty(value, name);
	    String str=value.toString();
	    if(!StringUtils.hasLength(str)){
	        throw new IllegalArgumentException(name + " 不能为空！");
	    }
    }
	/**
	 * 检查参数是否为空
	 * @param value 需要被检查的参数
	 * @param name
	 */
	public static void checkEmpty(Object value, String name) {
		if(value == null) {
			throw new IllegalArgumentException(name + " 不能为空！");
		}
	}

	/**
	 * 检查字符串参数是否为空
	 * @param value 需要被检查的参数
	 * @param name
	 */
	public static void checkEmpty(String value, String name) {
		if(StringUtils.isEmpty(value)) {
			throw new IllegalArgumentException(name + " 不能为空！");
		}
	}

	/**
	 * 检查Long型数字是否为0
	 * @param value 需要被检查的参数
	 * @param name
	 */
	public static void checkZero(Long value, String name) {
		checkEmpty(value, name);
		
		if(value == 0) {
			throw new IllegalArgumentException(name + " 不能为0！");
		}
	}

	/**
	 * 检查Byte型数字是否为0
	 * @param value 需要被检查的参数
	 * @param name
	 */
	public static void checkZero(Byte value, String name) {
		checkEmpty(value, name);
		
		if(value == 0) {
			throw new IllegalArgumentException(name + " 不能为0！");
		}
	}

	/**
	 * 检查给定数字value是否在from和to之间
	 * @param value 需要被检查的参数
	 * @param from 范围起点
	 * @param to 范围终点
	 * @param name
	 */
	public static void checkBetween(Byte value, Byte from, Byte to, String name) {
		checkEmpty(from, "from");
		checkEmpty(to, "to");
		if(from > to) {
			throw new IllegalArgumentException("from:" + from +  "必须小于或者等于" + "to:" + to + "！");
		}
		checkEmpty(value, name);
		if(value < from || value > to) {
			throw new IllegalArgumentException(name + "只能在" + from + "与" + to + "之间！");
		}
	}

	/**
	 * 检查给定数字value是否在from和to之间
	 * @param value 需要被检查的参数
	 * @param from  范围起点
	 * @param to    范围终点
	 * @param name
	 */
	public static void checkBetween(BigDecimal value, BigDecimal from, BigDecimal to, String name) {
		checkEmpty(from, "from");
		checkEmpty(to, "to");
		if(from.compareTo(to) > 0) { // from > to
			throw new IllegalArgumentException("from:" + from +  "必须小于或者等于" + "to:" + to + "！");
		}
		checkEmpty(value, name);
		if(value.compareTo(from) < 0 || value.compareTo(to) > 0) {
			throw new IllegalArgumentException(name + "只能在" + from + "与" + to + "之间！");
		}
	}

	/**
	 * 检查给定数字value是否在from和to之间
	 * @param value 需要被检查的参数
	 * @param from  范围起点
	 * @param to    范围终点
	 * @param name
	 */
	public static void checkBetween(Double value, Double from, Double to, String name) {
		checkEmpty(from, "from");
		checkEmpty(to, "to");
		if(from.compareTo(to) > 0) { // from > to
			throw new IllegalArgumentException("from:" + from +  "必须小于或者等于" + "to:" + to + "！");
		}
		checkEmpty(value, name);
		if(value.compareTo(from) < 0 || value.compareTo(to) > 0) {
			throw new IllegalArgumentException(name + "只能在" + decimalFormat.format(from) + "与" + decimalFormat.format(to) + "之间！");
		}
	}

	/**
	 * 检查给定整数是否大于from
	 * @param value 需要被检查的参数
	 * @param from 范围起点
	 * @param name
	 */
	public static void checkGreaterThan(Integer value, Integer from, String name) {
		checkEmpty(from, "from");
		checkEmpty(value, name);
		if(value <= from) {
			throw new IllegalArgumentException(name + "的数值必须大于" + from);
		}
	}
	
	public static void checkSmallerThan(BigDecimal value, BigDecimal from, String name){
		checkEmpty(from, "from");
		checkEmpty(value, name);
		if(value.compareTo(from) > 0) {
			throw new IllegalArgumentException(name + "的数值必须不大于" + from);
		}
	}

	/**
	 * 检查给定小数是否大于from
	 *
	 * @param value 需要被检查的参数
	 * @param from  范围起点
	 * @param name
	 */
	public static void checkGreaterThan(BigDecimal value,
			BigDecimal from, String name) {
		checkEmpty(from, "from");
		checkEmpty(value, name);
		if(value.compareTo(from) <= 0) {
			throw new IllegalArgumentException(name + "的数值必须大于" + from);
		}
	}
	
	public static void checkZero(Integer value, String name) {
		checkEmpty(value, name);
		
		if(value == 0) {
			throw new IllegalArgumentException(name + " 不能为0！");
		}
	}
	
	public static void checkInCollection(Integer value, Collection<Integer> expectedCollection, String name) {
		checkEmpty(value, name);
		checkEmpty(expectedCollection, "expectedCollection");
		
		if(!expectedCollection.contains(value)) {
			throw new IllegalArgumentException(name + " 不在期望范围中：" + expectedCollection);
		}
	}


    public static boolean hashEmpty(Map<String,Object> params,String[] keys){
        for(String key:keys){
            if(StringUtils.isEmpty(params.get(key))){
                return true;
            }
        }
        return false;
    }

	public static void checkGreaterThan(Double value, Double from, String name) {
		checkEmpty(from, "from");
		checkEmpty(value, name);
		if (value <= from) {
			throw new IllegalArgumentException(name + "的数值必须大于" + from);
		}
	}
}
