package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 把任务分配指定任务执行者的上级（上级只有一个人）
 * @author FHJ
 *
 */
public class SuperiorForTaskDistributer implements TaskDistributer{

	@Override
	public void assignTask(TaskWrapper taskWrapper) {
		DelegateTask delegateTask = taskWrapper.getDelegateTask();
		String processInstanceId = delegateTask.getProcessInstanceId();
		String assignee = delegateTask.getAssignee();
		
		// "SUPERIOR_FOR_TASK:taskuser-document-application" -> "taskuser-document-application"
		String taskDef = assignee.substring(assignee.indexOf(":") + 1);
		
		// TODO FHJ 
		SysUserService userService = ApplicationContextHelperBean.getBean(SysUserService.class);
		SysRoleService roleService = ApplicationContextHelperBean.getBean(SysRoleService.class);
		String bossUserID = null;
		try {
			Map sysUser = userService.queryTheUserWhoDidThisTask(processInstanceId, taskDef);
			// 找出该执行者的上司，两人一定属于同一个组中
			String officeId = (String) sysUser.get("officeId");
			Integer roleId = (Integer) sysUser.get("roleId");
			
			SysRole role = roleService.getRoleById(new Long(roleId));
			String roleName = role.getNid();
			
			String superiorRoleName = null;
			if("customerServiceStaff".equals(roleName)) {
				superiorRoleName = "customerManager";
			}

            SysUser user = userService.getUserByRoleAndOfficeId(superiorRoleName, new Long(officeId));
			if(user == null) {
				throw new RDRuntimeException("找不到上级人员");
			}

            bossUserID = user.getUserName();
        } catch (ServiceException e) {
			e.printStackTrace();
		}
		
        if (StringUtils.isEmpty(bossUserID)) {
            throw new RDRuntimeException("后续任务分配失败。");
        }

		delegateTask.setAssignee(bossUserID);
	}

}
