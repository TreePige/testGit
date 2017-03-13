package com.rongdu.eloan.modules.system.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 角色Action
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午1:45:50
 */
@Controller
public class SysRoleAction extends BaseAction {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取所有角色列表
	 * @param request
	 * @param response
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年10月15日
	 */
	@RequestMapping(value = "/modules/system/getRoleList.htm")
	public void getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<>();
		param.put("isDelete", "0");
		List<SysRole> sysRoleList = sysRoleService.getListByMap(param);
		res.put("data", sysRoleList);
		res.put("totalCount", sysRoleList.size());
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}
	
	/**
	 * 角色删除（逻辑删除）
	 * @throws ServiceException
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping(value = "/modules/system/general/roleDelete.htm")
	public void roleDelete(@RequestParam("key") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断用户是否还在使用这个角色
		Map<String, Object> res = new HashMap<String, Object>();
		if (id == null) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色不能为空");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("roleId", id);
		param.put("delete", "0");
		int roleNum = sysUserService.queryRoleUserIsUse(param);
		if (roleNum >= 1) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色有用户在使用，删除失败");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		int result = sysRoleService.deleteRole(id);
		if(result > 0){
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else{
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/getSysRoleList.htm")
	public void getSysUserList(HttpServletResponse response,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> reposedata = new HashMap<String, Object>();
		PageHelper.startPage(currentPage, pageSize);
		List<SysRole> sysRoles = (List<SysRole>) sysRoleService.getRolePageList(data);
		int totalCount = sysRoleService.getRolecount(data);
		reposedata.put("data", sysRoles);
		reposedata.put("totalCount", totalCount);
		ServletUtils.writeToResponse(response, reposedata);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/system/addRole.htm")
	public void addRoleAction(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "form") String data,
			@RequestParam(value = "status") String status) throws Exception{
		HashMap<String, Object> dataMap = JsonUtil.parse(data, HashMap.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if ("create".equalsIgnoreCase(status)) {
			SysUser loginUser = this.getLoginUser(request);
			SysRole role = new SysRole();
			role.setAddTime(new Date());
			role.setAddUser(loginUser.getUserName());
			role.setUpdateTime(new Date());
			role.setUpdateUser(loginUser.getUserName());
			role.setIsDelete((byte)0);
			role.setName(dataMap.get("name") != null ? String.valueOf(dataMap.get("name")) :"");
			role.setNid(dataMap.get("nid") != null ? String.valueOf(dataMap.get("nid")) :"");
			role.setRemark(dataMap.get("remark") != null ? String.valueOf(dataMap.get("remark")) :"");
			long n = sysRoleService.addRole(role);
			if (n > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		} else if ("update".equals(status)) {
			int total = sysRoleService.updateRole(dataMap);
			if (total > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新失败");
			}
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/system/insertByMap.htm")
	public void insertByMap(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "data") String data) throws Exception{
		Map<String, Object> returnMap = new HashMap<>();
		HashMap<String, Object> dataMap = JsonUtil.parse(data, HashMap.class);
		String[] keys = new String[dataMap.size()]; 
        Set<String> sset = dataMap.keySet(); 
        int i = 0; 
        for (String os : sset) { 
            keys[i++] = os; 
        } 
        Map<String, Object> role = new HashMap<String, Object>(); 
        role.put("keys", keys); 
        role.put("params", dataMap); 
		long n = sysRoleService.insertByMap(role);
		if (n > 0) {
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, returnMap);
	}
	
	/**
	 * 
	 * @description
	 * @param response
	 * @param username
	 * @param password
	 * @param request
	 * @param session
	 * @throws Exception
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getByUserPassRolesList.htm")
	public void getByUserPassRolesList(HttpServletResponse response,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpSession session
			)
			throws Exception {
		if (username != null && password != null) {
			
			List<Map<String, Object>> roles = sysRoleService
					.getByUserPassRolesList(username, password);

			Map<String, Object> rec = new HashMap<String, Object>();

			if (roles.size() > 0) {
				rec.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, roles);
				ServletUtils.writeToResponse(response, rec);
			} else {
				rec.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, "用户名或密码错误");
				ServletUtils.writeToResponse(response, rec);
			}
		}
	}

}
