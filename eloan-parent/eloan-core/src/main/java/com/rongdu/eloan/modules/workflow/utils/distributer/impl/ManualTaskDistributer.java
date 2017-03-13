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
 *	@Description 手工分配任务
 *  @author fzc,fzc@erongdu.com
 *  @CreatTime 2016年8月8日 下午5:49:33
 *  @since version 1.0.0
 */
public class ManualTaskDistributer implements TaskDistributer{

	@Override
	public void assignTask(TaskWrapper task) {
		 ParamChecker.checkEmpty(task, "taskWrapper");
	        
			DelegateTask delegateTask = task.getDelegateTask();
			
			String userName = (String) delegateTask.getVariable("ManualAssignee");
			
			delegateTask.setAssignee(userName);
		
	}

}
