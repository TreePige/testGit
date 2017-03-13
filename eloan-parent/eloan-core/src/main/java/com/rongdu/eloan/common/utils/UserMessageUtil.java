package com.rongdu.eloan.common.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.rongdu.eloan.modules.system.domain.SysUser;

public class UserMessageUtil {
	
    /**
     * 测试用
     */
	public  static void getUserMessage(HttpServletRequest request){
		
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
		//登录名  
		System.out.println("Username:" + securityContextImpl.getAuthentication().getName());  
		//登录密码，未加密的  
		System.out.println("Credentials:" + securityContextImpl.getAuthentication().getCredentials());  
		WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl.getAuthentication().getDetails();  
		//获得访问地址  
		System.out.println("RemoteAddress" + details.getRemoteAddress());  
		//获得sessionid  
		System.out.println("SessionId:" + details.getSessionId());
		//获得当前用户所拥有的权限  
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();  
		for (GrantedAuthority grantedAuthority : authorities) {  
		System.out.println("Authority" + grantedAuthority.getAuthority());  
		}
	}
	
	
	/**
	 * 获取当前用户信息
	 * @param request
	 * @return 
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年11月20日
	 */
	public static SysUser getCurrentUserInfo(HttpServletRequest request){
		SysUser sysUser = new SysUser();
		try {
			Object obj = request.getSession().getAttribute("SysUser");		
			if(obj != null && obj instanceof SysUser){
				sysUser = (SysUser) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysUser;
	}	

}
