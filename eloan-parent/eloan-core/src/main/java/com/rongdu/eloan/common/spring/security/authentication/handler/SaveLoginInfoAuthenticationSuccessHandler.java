package com.rongdu.eloan.common.spring.security.authentication.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 验证通过Handler 此Handler对用户验证通过后对用户登录信息进行记录
 * 
 * @author zhangyz
 */

@Service
public class SaveLoginInfoAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private SysRoleService sysRoleService;

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Autowired
	private SysUserService sysUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		try {
			// 设置用户登录信息
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SysUser op = new SysUser();
			op.setUserName(user.getUsername());
			op.setLoginIp(getIpAddr(request));
			// 更新用户登录信息
			sysUserService.editUserLoginInfo(op);
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			SysUser sysUser = null;
			sysUser = sysUserService.getUserByUserName(op.getUserName());
			HttpSession session = request.getSession();
			session.setAttribute("SysUser", sysUser);
			if (roles.contains(SystemConstant.ROLE_DEFAULT) && sysUser.getStatus().intValue()==0) {//状态为0的可以登录
				Map<Object, Object> context = new HashMap<Object, Object>();
				context.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				context.put(Constant.RESPONSE_CODE_MSG, "登录成功");
				ServletUtils.writeToResponse(response, context);
				List<SysRole> roleList = sysRoleService.getRoleListByUserId(op.getId());
				List<Long> roleIdList = new ArrayList<Long>();
				// 转换用户的角色为用户授权, 并记录用户角色Id列表
				for (SysRole role : roleList) {
					roleIdList.add(role.getId());
				}
				session.setAttribute("roleList", roleIdList);
				super.onAuthenticationSuccess(request, response, authentication);
			}else{//登录失败 added by wgc on Dec 09 2014
				Map<Object, Object> context = new HashMap<Object, Object>();
				context.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				context.put(Constant.RESPONSE_CODE_MSG, sysUser.getStatus().intValue()==1?"该账号已被锁定，请解锁后使用！":"登录失败！");
				ServletUtils.writeToResponse(response, context);
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 此方法在转发请求的HTTP头信息中，增加了 X-FORWARDED-FOR 信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址
	 * 
	 * @param request
	 *            请求
	 * @return String
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

}
