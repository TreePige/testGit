package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import org.activiti.engine.delegate.DelegateTask;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysUserService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;

/**
 * 基于角色的任务分配器
 * 任务会分配给一个角色中的某个成员
 * @See RoledBasedTaskDistributionStrategy
 * @author FHJ
 *
 */

public class RoleBasedTaskDistributer implements TaskDistributer{
	
	private static final String TASKUSER_PLEDGE_ESTIMATION = "taskuser-pledge-estimation";
	
	
	
	
	@Override
	public void assignTask(TaskWrapper taskWrapper) {
        ParamChecker.checkEmpty(taskWrapper, "taskWrapper");
        
		DelegateTask delegateTask = taskWrapper.getDelegateTask();
		
		//String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
		String processInstanceId = delegateTask.getProcessInstanceId();
		
		String assignee = delegateTask.getAssignee();
		// "role:业务部" -> "业务部"
		String roleName = assignee.substring(assignee.indexOf(":") + 1);

		SysUserService sysUserService = ApplicationContextHelperBean.getBean(SysUserService.class);
		
		SysUser sysUser;
		
		try {
			String taskDefinition = null;
			// TODO FHJ查询出来的人必须和启动流程的用户是同一个机构
			// 首先查询出启动流程的用户
			String processLauncher = (String) delegateTask.getVariable(SystemConstant.PROCESS_LAUNCHER);
			SysUser launcher = sysUserService.getUserByUserName(processLauncher);
			
			sysUser = sysUserService.queryTheLeastBusyUserByRoleName(roleName, launcher.getOfficeId(),processInstanceId, taskDefinition);
		} catch (ServiceException e) {
			throw new RDRuntimeException(e.getMessage(), e);
		}

        if (sysUser == null) {
            throw new RDRuntimeException("后续任务分配失败。");
        }
		
		delegateTask.setAssignee(sysUser.getUserName());
		
	}

}
