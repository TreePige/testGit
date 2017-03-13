package com.rongdu.eloan.common.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rongdu.eloan.common.utils.ResponseUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.annotation.PermissionAnnotation;
import com.rongdu.eloan.modules.system.service.SysMenuService;


@Service
public class PermissionsInterceptor extends HandlerInterceptorAdapter {
  
	   private static final Logger log = LoggerFactory.getLogger(PermissionsInterceptor.class);
	@Autowired@Qualifier("sysMenuServiceImpl")
	private SysMenuService sysMenuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return true;
		}
		

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user == null || user.getUsername() == null) {
			ServletUtils.writeToResponse(response, ResponseUtil.getResponseFail("没有登录"));
			return false;
		}
		String url = request.getServletPath();
		if (url == null || url.length() <= 0) {
			return true;

		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		PermissionAnnotation annotation = handlerMethod.getMethodAnnotation((PermissionAnnotation.class));

		if (annotation != null) {
			HttpSession session = request.getSession(true);

			List<String> hrefs = (List<String>) session.getAttribute("hrefs");

			if (hrefs.contains(url)) {
				return true;

			} else {
				ServletUtils.writeToResponse(response, ResponseUtil.getResponseFail("沒有权限"));
				return false;
			}

		}

		// }

		return true;
	}
	
	
	public boolean  validatePermission(List<String> hrefs,String url){
		
		return  hrefs.contains(url);
		
		
		
	}
}
