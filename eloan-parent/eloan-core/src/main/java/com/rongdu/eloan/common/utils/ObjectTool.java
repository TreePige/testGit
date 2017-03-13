package com.rongdu.eloan.common.utils;

import java.math.BigDecimal;

/**
 * 
 *	@Description Object的工具类
 *  @author 孙凯伦,skl@erongdu.com
 *  @CreatTime 2016年2月24日 上午11:45:36
 *  @since version 1.0.0
 */
public class ObjectTool {
	/**
	 * 
	 * @description 转换为String
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return String
	 * @since  1.0.0
	 */
	public static String String(Object o){
		if(judge(o))
			return String.valueOf(o);
		else
			return "";
	}
	/**
	 * 
	 * @description 转换为Integer
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return Integer
	 * @since  1.0.0
	 */
	public static Integer Integer(Object o){
		if(judge(o))
			return Integer.valueOf(String(o));
		else
			return 0;
	}
	/**
	 * 
	 * @description 转换为Long
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return Long
	 * @since  1.0.0
	 */
	public static Long Long(Object o){
		if(judge(o))
			return Long.valueOf((String(o)));
		else
			return 0L;
	}
	/**
	 * 
	 * @description 转换为BigDecimal
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	public static BigDecimal BigDecimal(Object o){
		if(judge(o))
			return BigDecimal.valueOf(Double(o));
		else
			return new BigDecimal(0);
	}
	/**
	 * 
	 * @description 转换为Double
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return Double
	 * @since  1.0.0
	 */
	public static Double Double(Object o){
		if(judge(o))
			return Double.valueOf(String(o));
		else
			return null;
	}
	/**
	 * 
	 * @description 转换为Byte
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return Byte
	 * @since  1.0.0
	 */
	public static Byte Byte(Object o){
		if(judge(o))
			return Byte.valueOf(String(o));
		else
			return 0;
	} 
	
	/**
	 * 
	 * @description 判断是否为空
	 * @param o
	 * @return
	 * @author 孙凯伦
	 * @return boolean
	 * @since  1.0.0
	 */
	public static boolean judge(Object o){
		if(o != null && o != "")
			return true;
		else
			return false;
	}
	/**
	 * object数组转string数组
	 * @param obj
	 * @return
	 */	
	public static String[] toStringArray(Object[] obj){
		String[] stringArray=new String[obj.length];
		int i=0;
		for(Object oo:obj){
            if(oo instanceof String){ //对每个元素进行类型判断
            	stringArray[i]=(String)oo;
            }
            i++;
        }
		return stringArray;
	}
}
