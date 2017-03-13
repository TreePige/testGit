package com.rongdu.eloan.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *  
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 杭州融都科技有限公司 http://www.erongdu.com</p>
 * <p>System: 小贷管理系统</p>
 * <p>Description: ip获取工具类</p>
 *  
 * @author：wujing
 * @version 1.0
 * @since 2014-4-2
 */
public class IpUtil {
	
	   public static String getRemortIP(HttpServletRequest request) {     
	    	String ip = request.getHeader("x-forwarded-for");  
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;
	    }

}
