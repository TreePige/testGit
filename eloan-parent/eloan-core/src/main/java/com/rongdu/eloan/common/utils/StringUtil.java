package com.rongdu.eloan.common.utils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 工具类-字符串处理
 * 
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public final class StringUtil extends StringUtils {

	/**
	 * 构造函数
	 */
	private StringUtil() {

	}

	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 * 
	 * @param str 字符串
	 * @return 如果str为null，返回"",否则返回str
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static boolean isEmpty(Object str) {
		return str==null || "".equals(str);
	}

	/**
	 * 判断输入的手机号码是否有效
	 * 
	 * @param str 手机号码
	 * @return 检验结果（true：有效 false：无效）
	 */
	public static boolean isPhone(String str) {
		String phone = isNull(str);
		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher matcher = regex.matcher(phone);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
	
	/**
	 * 判断邮箱是否有效
	 * @param str 邮箱
	 * @return 检验结果（true：有效 false：无效）
	 */
	public static boolean isMail(String str) {
		String mail = isNull(str);
		Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = regex.matcher(mail);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 判断输入的身份证号码是否有效
	 * 
	 * @param str 身份证号码
	 * @return 检验结果（true：有效 false：无效）
	 */
	public static boolean isCard(String str) {
		String cardId = isNull(str);
		// 身份证正则表达式(15位)
		Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		// 身份证正则表达式(18位)
		Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		Matcher matcher1 = isIDCard1.matcher(cardId);
		Matcher matcher2 = isIDCard2.matcher(cardId);
		boolean isMatched = matcher1.matches() || matcher2.matches();
		return isMatched;
	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str 字符串
	 * @return 首字母大写的字符串
	 */
	public static String upperFirst(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 功能：将输入字符串的首字母改成小写
	 * 
	 * @param str 字符串
	 * @return 首字母小写的字符串
	 */
	public static String lowerFirst(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	/**
	 * String to int
	 * @param str
	 * @return
	 */
	public static int getInt(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		int ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String to Long
	 * @param str
	 * @return
	 */
	public static long getLong(String str) {
		if (StringUtil.isBlank(str))
			return 0L;
		long ret = 0;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	
	public static String isNull(Object o) {
		if (o == null) {
			return "";
		}
		String str="";
		if(o instanceof String){
			str=(String)o;
		}else{
			str=o.toString();
		}
		return str;
	}
	
	public static boolean isBlank(String str){
		return StringUtil.isNull(str).equals("");
	}

    public static String toString(String separate,int...objs){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<objs.length;i++){
            if(i>0)sb.append(separate);
            sb.append(objs[i]);
        }
        return sb.toString();
    }


	public static String toStringArray(Object... list){
		StringBuilder sb=new StringBuilder();
		int index=0;

		for (Object o : list) {
			if(index>0)sb.append(",");
			sb.append(o.toString());
			index++;
		}
		return sb.toString();
	}

	public static String toString(Collection list){
		return toString(list,",");
	}
    public static String toString(Collection list,String delim){
        StringBuilder sb=new StringBuilder();
		int index=0;

		for (Object o : list) {
			if(index>0)sb.append(delim);
			sb.append(o.toString());
			index++;
		}
        return sb.toString();
    }

    public static String getRelativePath(File file,File parentFile){
        return file.getAbsolutePath().replaceFirst("^\\Q"+parentFile.getAbsolutePath()+"\\E","").replace("\\","/");
    }
    public static String getFileUri(HttpServletRequest request,File file){
        String pre=request.getRealPath("/");
        String fullpath=file.getAbsolutePath();
        return fullpath.replace(pre.replaceFirst("[\\\\/]$",""),"").replace("\\","/");
    };
    
    public static String getRepairedFileUri(String fullpath){
        return fullpath.replaceFirst("[\\\\/]$","").replace("\\","/").replace("//","/");
    };

    public static boolean isNullString(String s) {
		if (s == null || "".equals(s)) {
			return true;
		}
		return false;
	}
    
    /***对象获取成数字类型的Value**/
    public static String getNumberValueOfObject(Object obj){
    	if(obj == null || "".equals(obj)){
    		return "0";
    	}
    	String objValue = String.valueOf(obj);
    	if(isEmpty(objValue) || objValue.length()== 0){
    		return "0";
    	}
    	return objValue;
    } 
}
