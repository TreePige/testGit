package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;

import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.workflow.service.RDHistoryService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;

/**
 * 选定用户 任务分配器
 * @author FHJ
 *
 */
public class SelectedUserBasedTaskDistributer implements TaskDistributer{
	@Override
	public void assignTask(TaskWrapper taskWrapper) {
		DelegateTask delegateTask = taskWrapper.getDelegateTask();
		
		String assignee = delegateTask.getAssignee();
		// "same_as_user_task:taskuser-document-application" -> "taskuser-document-application"
		String taskDefinition = assignee.substring(assignee.indexOf(":") + 1);
		
		String processInstanceId = delegateTask.getProcessInstanceId();
		
		RDHistoryService historyService = ApplicationContextHelperBean.getBean(RDHistoryService.class);
		Map<String, Object> thelatestHistoricTaskInstance;
		try {
			thelatestHistoricTaskInstance = historyService.queryTheLatestHistoricTask(processInstanceId, taskDefinition);
		} catch (ServiceException e) {
			throw new RDRuntimeException("queryTheLatestHistoricTaskByProcessInstanceAndTaskDefinition出错", e);
		}
		
		if(thelatestHistoricTaskInstance == null) {
			throw new RDRuntimeException("没有找到指定任务的历史执行人");
		}
		delegateTask.setAssignee(thelatestHistoricTaskInstance.get("ASSIGNEE_").toString());
	}

}
