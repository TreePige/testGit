package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.workflow.service.RDHistoryService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;

public class ClaimTaskDistributer implements TaskDistributer {

	@Override
	public void assignTask(TaskWrapper taskWrapper) {
		// TODO Auto-generated method stub
        ParamChecker.checkEmpty(taskWrapper, "taskWrapper");
		DelegateTask delegateTask = taskWrapper.getDelegateTask();
		String processInstanceId = delegateTask.getProcessInstanceId();
		String taskDefinition =delegateTask.getTaskDefinitionKey();
		RDHistoryService historyService = ApplicationContextHelperBean.getBean(RDHistoryService.class);
		Map<String, Object> thelatestHistoricTaskInstance;
		try {
			thelatestHistoricTaskInstance = historyService.queryTheLatestHistoricTask(processInstanceId, taskDefinition);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new RDRuntimeException("queryTheLatestHistoricTaskByProcessInstanceAndTaskDefinition出错", e);
				}
		if(thelatestHistoricTaskInstance == null) {
			delegateTask.setAssignee(null);
		}else{
			delegateTask.setAssignee(thelatestHistoricTaskInstance.get("ASSIGNEE_").toString());
		}
	}

}
