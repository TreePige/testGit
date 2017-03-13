package com.rongdu.eloan.common.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 工具类-数字处理
 * 
 * @author zhangyz
 * @version 1.0
 */
public final class NumberUtil {

	/**
	 * 构造函数
	 */
	private NumberUtil() {

	}

	/**
	 * 将string转换为Long
	 * 
	 * @param str 字符串
	 * @return Long型数字
	 */
	public static Long getLong(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		Long ret = 0l;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = null;
		}
		return ret;
	}

	/**
	 * 将string转换成Integer，当是非数字类型时，将返回null
	 * 
	 * @param str 字符串
	 * @return Integer数字
	 */
	public static Integer getInt(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		Integer ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = null;
		}
		return ret;
	}
	
	/**
	 * 将string转换成Byte，当是非数字类型时，将返回null
	 * 
	 * @param str 字符串
	 * @return Byte字节
	 */
	public static Byte getByte(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		Byte ret = 0;
		try {
			ret = Byte.parseByte(str);
		} catch (NumberFormatException e) {
			ret = null;
		}
		return ret;
	}
	
	/**
	 * 将string转换成double，当是非数字类型时，将返回null
	 * @param str
	 * @return double数字
	 */
	public static Double getDouble(String str){
		if (str == null || "".equals(str)) {
			return null;
		}
		Double ret = 0.0;
		try {
			ret = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			ret = null;
		}
		return ret;
	}
	public static void roundBigDecimal(Map<String, Object> map) {
        for (Entry<String, Object> entry : map.entrySet()) {
            Object v = entry.getValue();
            if (v instanceof BigDecimal) {
                BigDecimal value = (BigDecimal) v;
                entry.setValue(value.setScale(2, BigDecimal.ROUND_CEILING));
            }
        }
    }
}
