package com.rongdu.eloan.modules.system.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.domain.SysUserRole;
import com.rongdu.eloan.modules.system.service.SysRoleMenuService;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 
 * 登陆处理Action, 实际登陆处理交由Spring Security框架, 该Action的作用仅仅为辅助
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午2:15:42
 */
@Controller
public class LoginAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(LoginAction.class);

	private final static String ROLEID = "roleId";
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 登陆处理
	 * 
	 * @param error
	 * @param model
	 */
	@RequestMapping("/login.htm")
	public void login(HttpServletResponse response, HttpServletRequest request) throws Exception{
		response.sendRedirect("/dev/index.html");
	}

	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/ajaxLoginProcess.htm")
	public void logajax(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "roleId", required = true) Long roleId,
			HttpServletResponse response, HttpServletRequest request,HttpSession session)
			throws Exception {
		logger.info("------------------------------------LoginAction-logajax() username:"
				+ username + ",password:" + password +",roleId:" + roleId);
		Map<String, Object> res = new HashMap<String, Object>();
		logger.info("***********************进入登录模块**********************************");
		
		try {
			SysUser sysUser = sysUserService.getUserByUserName(username);
			validateRole(sysUser, roleId);
			if (null != sysUser) {
				if (1 == sysUser.getStatus()) {
					res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
					res.put(Constant.RESPONSE_CODE_MSG, "该用户已锁定，请联系管理员！");
				} else {
					Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
							username, password);
					Authentication authentication = authenticationManager
							.authenticate(authenticationToken);
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
					session.setAttribute("SPRING_SECURITY_CONTEXT",
							SecurityContextHolder.getContext());
					logger.info("==================role="
							+ Long.valueOf(roleId) + " username="
							+ sysUser.getUserName());
					
					 List<String> menuHrefs =	sysRoleMenuService.getRoleMenuHrefList(roleId);
					session.setAttribute(Constant.ROLEID, Long.valueOf(roleId));

					session.setAttribute("SysUser", sysUser);
					   session.setAttribute("hrefs", menuHrefs);

					// response.addHeader("Location","/index.htm");

					/*
					 * if("true".equals(request.getParameter("debug"))){
					 * response.sendRedirect("/index.htm"); return; }
					 */

					res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				}
			} else {
				res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "用户不存在，登录失败");
			}

		} catch (AuthenticationException ex) {
			logger.error("登录失败", ex);

			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录失败");

		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}

		ServletUtils.writeToResponse(response, res);

	}

	/**
	 * 切换角色功能调用
	 * 
	 * @description
	 * @param role
	 * @param response
	 * @param request
	 * @author wgc
	 * @return void
	 * @throws IOException
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/ajaxChangeLoginProcess.htm")
	public void changeLoginajax(
			@RequestParam(value = "role", required = false) String role,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		logger.info("------------------------------------LoginAction-logajax() role:"
				+ role);
		Map<String, Object> res = new HashMap<String, Object>();
		
		logger.info("***********************进入切换角色**********************************");
	HttpSession session = request.getSession(true);
		
		SysUser user = (SysUser) session.getAttribute("SysUser");
            
		 validateRole(user, Long.valueOf(role));
		   List<String> menuHrefs =	sysRoleMenuService.getRoleMenuHrefList(Long.valueOf(role));
		   session.setAttribute("hrefs", menuHrefs);

		session.setAttribute(Constant.ROLEID, Long.valueOf(role.trim()));

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		ServletUtils.writeToResponse(response, res);
	}

	public void validateRole(SysUser user, Long roleid) throws ServiceException {
		List<SysUserRole> list = sysUserRoleService.getSysUserRoleList(user.getId());
		for (SysUserRole role : list) {
			if (role.getRoleId().equals(roleid))
				return;
		}
		SysRole role = sysRoleService.getRoleById(roleid);
		throw new ServiceException(user.getName() + "不包含[" + role.getName()
				+ "]这个角色");
	}

	@RequestMapping(value = "/login2.htm")
	public void sessionout(HttpServletResponse response) {

		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "登录失败");

		ServletUtils.writeToResponse(response, res);
	}

}
