package com.rongdu.eloan.modules.workflow.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.rongdu.eloan.modules.common.exception.ServiceException;

public interface RDProcessInstanceService {
	/**
	 * 启动流程实例
	 * 
	 * @param processDefinitionId 流程定义ID
	 * @param launcherUserId 启动人
	 * @param businessName 流程实例业务名称
	 * @param bpmVariables 流程变量
	 * @return 流程实例对象
	 */
	ProcessInstance startProcessInstance(String processDefinitionId,
			String launcherUserId, String businessName,
			Map<String, Object> bpmVariables) throws ServiceException;
}
