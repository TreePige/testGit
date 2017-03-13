package com.rongdu.eloan.modules.system.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.pagehelper.PageHelper;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.spring.security.authentication.encoding.PasswordEncoder;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.system.domain.SysOffice;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysOfficeService;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 用户action
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午1:53:06
 */
@Controller
public class SysUserAction extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(SysUserAction.class);
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysOfficeService sysOfficeService;
	@Autowired
	private PasswordEncoder passwordEncoder;// 密码加密
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/modifyPassword.htm")
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user) throws Exception{
		logger.info("---------------------------------开始修改密码----------------------------------------");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		String oldPassword = passwordEncoder.encodePassword(String.valueOf(userMap.get("password")));
		String newPassword1 = passwordEncoder.encodePassword(String.valueOf(userMap.get("password1")));
		String newPassword2 = passwordEncoder.encodePassword(String.valueOf(userMap.get("password2")));
		SysUser sysUser = this.getLoginUser(request);
		if (!sysUser.getPassword().equals(oldPassword)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "原密码输入不正确");
		} else if (!newPassword1.equals(newPassword2)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "两个新密码不一致");
		} else if (oldPassword.equals(newPassword1)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "新密码不能和旧密码相同");
		} else {
			sysUser.setPassword(newPassword1);// 密码加密
			sysUserService.editUserPassWord(sysUser);
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "密码修改成功");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 增加用户
	 * @param request
	 * @param response
	 * @param user
	 * @param status
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/adduser.htm")
	public void userAdd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "status", required = false) String status) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		// 获取当前登录用户信息
		SysUser userinfo = this.getLoginUser(request);
		String loginUserName = userinfo.getName();
		Date curDate = new Date();
		String officeId = String.valueOf(userMap.get("officeId"));
		if ("create".equalsIgnoreCase(status)) {// 新建
			SysUser sysUser = new SysUser();
			sysUser.setName(String.valueOf(userMap.get("name")));// 真实姓名
			sysUser.setNumber(String.valueOf(userMap.get("number")));// 工编号
			// 用户名验证
			String userName = String.valueOf(userMap.get("userName"));
			SysUser user2 = sysUserService.getUserByUserName(userName);
			if (null != user2) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "用户名已存在，不能重复注册!");
			} else {
				sysUser.setUserName(userName); // 登录名
				sysUser.setOfficeId(officeId);// 所属组织
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parentId", 0);
				List<SysOffice> ls = sysOfficeService.getListByMap(temp);
				sysUser.setCompanyId(ls.get(0).getId());
				String email = String.valueOf(userMap.get("email"));
				if (StringUtil.isMail(email)) {
					sysUser.setEmail(email);
				}
				if(userMap.get("phone") != null){
					sysUser.setPhone(String.valueOf(userMap.get("phone")));
				}
				if(userMap.get("remark") != null){
					sysUser.setRemark(String.valueOf(userMap.get("remark")));
				}
				String mobile = String.valueOf(userMap.get("mobile"));
				if (StringUtil.isPhone(mobile)) {
					sysUser.setMobile(mobile);
				}
				sysUser.setAddTime(curDate);
				sysUser.setAddUser(loginUserName);
				sysUser.setUpdateTime(curDate);
				sysUser.setUpdateUser(loginUserName);
				sysUser.setIsDelete((byte) 0);
				sysUser.setPassword(SystemConstant.SYSTEM_PASSWORD_DEFAULT); // 账号初始密码
				sysUser.setStatus(SystemConstant.USER_STATUS_NORMAL); // 用户状态：正常
				if (!StringUtil.isBlank((String)userMap.get("officeOver"))) {
					sysUser.setOfficeOver(String.valueOf(userMap.get("officeOver")));
				}
				if (!StringUtil.isBlank(String.valueOf(userMap.get("position")))) {
					sysUser.setPosition(Byte.valueOf(String.valueOf(userMap.get("position"))));
				}
				sysUserService.addUser(sysUser, String.valueOf(userMap.get("roleId")));// 增加用户
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			}
		} else if ("update".equalsIgnoreCase(status)) {// 更新
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("parentId", 0);
			List<SysOffice> ls = sysOfficeService.getListByMap(temp);		
			userMap.put("companyId", ls.get(0).getId());
			if (!StringUtil.isBlank((String)userMap.get("officeOver"))) {
				userMap.put("officeOver", String.valueOf(userMap.get("officeOver")));
			}
			if (StringUtil.isBlank(String.valueOf(userMap.get("position")))) {
				userMap.put("position", 0);
			}
			SysUser updateUser = getLoginUser(request);
			userMap.put("updateUser", updateUser.getUserName());
			boolean istrue = sysUserService.updateSysUserById(userMap);// 更新用户信息及对应的角色信息
			if (istrue) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			}
		} else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 用户修改 -- 锁定及解锁 密码重置
	 * @param request
	 * @param response
	 * @param status
	 */
	@RequestMapping("/modules/system/userEditPage.htm")
	public void userEditPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids[]", required = false) String ids[],
			@RequestParam(value = "status", required = false) String status) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		int successcount = 0;
		for (String id : ids) {
			long userid = Long.parseLong(id);
			SysUser sysUser = sysUserService.getUserById(userid);
			if(sysUser != null){
				if ("lock".equals(status)) {
					sysUser.setStatus((byte)1);
				} else if ("unlock".equals(status)) {
					sysUser.setStatus((byte)0);
				} else if ("editpassword".equals(status)) {
					sysUser.setPassword(passwordEncoder.encodePassword(SystemConstant.SYSTEM_PASSWORD_DEFAULT));
				}
				sysUser.setUpdateTime(new Date());
				int count = sysUserService.userUpdate(sysUser);
				if(count > 0){
					successcount ++;
				}
			}
		}
		if (successcount == ids.length) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		} else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/general/getSysUserList.htm")
	public void getSysUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> reposedata = new HashMap<String, Object>();
		Map<String, Object> paramap = JsonUtil.parse(user, Map.class);
		data.put("number", null == user ? null : paramap.get("number"));
		data.put("name", null == user ? null : paramap.get("name"));
		data.put("status", null == user ? null : paramap.get("status"));
		data.put("roleId", null == user ? null : paramap.get("roleId"));
		data.put("officeName", null == user ? null : paramap.get("officeName"));
		data.put("parentofficeId", null == user ? null : paramap.get("officeId"));
		if (null == data.get("parentofficeId")) {
			data.put("parentofficeId", this.getLoginUser(request).getOfficeId());
		}
		PageHelper.startPage(currentPage, pageSize);
		List<Map<String, Object>> sysUsers = (List<Map<String, Object>>) sysUserService.getUserPageList(data);
		int totalCount = sysUserService.getUserSum(data);
		reposedata.put("data", sysUsers);
		reposedata.put("totalCount", totalCount);
		reposedata.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		reposedata.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, reposedata);
	}
	
	/**
	 * 获取头部信息 可以js缓存优化 后期处理
	 * @param response
	 * @param request
	 */
	@RequestMapping("/modules/system/getUserMessage.htm")
	public void getUserMessage(HttpServletResponse response, HttpServletRequest request) throws Exception{
		Map<String, Object> responsemap = new HashMap<String, Object>();
		SysUser sysUser = this.getLoginUser(request);
		if(request.getSession().getAttribute("roleId") != null){
			SysRole sysRole = sysRoleService.getRoleById(Long.valueOf(String.valueOf(request.getSession().getAttribute("roleId"))));
			responsemap.put("rolename", sysRole.getName());
			responsemap.put("nid", sysRole.getNid());
		}
		List<SysRole> roleList = sysRoleService.getRoleListByUserId(sysUser.getId());
		Long officeId=Long.valueOf(sysUser.getOfficeId());
		responsemap.put("officeId", officeId);
		SysOffice office=sysOfficeService.getOfficeById(officeId);
		responsemap.put("officeName", office.getName());
		responsemap.put("name", sysUser.getName());
		responsemap.put("id", sysUser.getId());
		responsemap.put("roleList", roleList);
		responsemap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responsemap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responsemap);
	}
	
	/**
	 * 获取客服专员 customerServiceStaff 客户经理 saleCustomerManager
	 * @param request
	 * @param response
	 */
	@RequestMapping("/modules/system/getcustomerservicestafflist.htm")
	public void getCustomerServiceStaffList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String nid = this.paramString(request, "nid");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("officeId", this.getLoginUser(request).getOfficeId());
		paramMap.put("nid", nid);
		List<Map<String, Object>> ml = sysUserService.getCustomerServiceStaffList(paramMap);
		responseMap.put("data", ml);
		responseMap.put("totalCount", ml.size());
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * @description 根据角色Id查找用户
	 * @param response
	 * @param request
	 * @param session
	 * @param roleId
	 * @throws Exception
	 * @author fzc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping("/modules/system/getUserInfo.htm")
	public void getUserInfo(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session,
			@RequestParam(value = "roleName") String roleName)throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", roleName);
		
		String officeOver = getLoginUser(request).getOfficeOver();
		if(officeOver != null){
			params.put("officeOver", Arrays.asList(officeOver.split(",")));
		}
		
		List<Map<String,Object>> users = sysUserService.getUserInfo(params);
		
		responseMap.put("data", users);
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}
}
