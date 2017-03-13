package com.rongdu.eloan.modules.workflow.controller;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 工作流程Controller的基类
 * @author Administrator
 *
 */
public class WorkflowBaseController extends BaseAction {
	
	/**
	 * 准备流程相关的参数集合
	 * 所谓与流程相关的参数，就是能够改变流程走向的参数，例如：comment:'不同意', ammount:200000(金额大于200000会走分支2)等
	 * @param processVarMap
	 * @param bpmVariables
	 */
	protected void prepareProcessParams(Map<String, Object> processVarMap, final Map<String, Object> bpmVariables) {
		if(processVarMap == null) {
			return;
		}
		Set<Entry<String, Object>> entrySet = processVarMap.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			bpmVariables.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 准备业务相关的参数集合
	 * @param serviceVariables
	 * @param bpmVariables
	 */
	protected void prepareServiceParams(String serviceVariables, Map<String, Object> bpmVariables) {
		bpmVariables.put(SystemConstant.SERVICE_VARIABLES, serviceVariables);
	}

    /**
     * 准备登录用户所用的角色
     * @param role
     * @param bpmVariables
     */
    protected void prepareRoleInfoParams(SysRole role, Map<String, Object> bpmVariables) {
        bpmVariables.put(SystemConstant.LOGIN_ROLE_INFO_VARIABLES, role);
    }
	
	/**
	 * 准备登录用户相关的参数集合
	 * @param sysUser
	 * @param bpmVariables
	 */
	protected void prepareLoginInfoParams(SysUser sysUser, Map<String, Object> bpmVariables) {
		bpmVariables.put(SystemConstant.LOGIN_INFO_VARIABLES, sysUser);
	}

    /**
     * 从Session中获取roleId
     * @param request
     * @return
     */
    protected Long getRoleId(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(Constant.ROLEID);
    }
}
