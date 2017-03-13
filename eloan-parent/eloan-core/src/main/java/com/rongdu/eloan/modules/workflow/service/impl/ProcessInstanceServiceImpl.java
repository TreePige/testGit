package com.rongdu.eloan.modules.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.workflow.service.RDProcessInstanceService;

@Service
public class ProcessInstanceServiceImpl implements RDProcessInstanceService {
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private IdentityService identityService;

	/**
	 * 直接调用Activiti的API来启动流程
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcessInstance startProcessInstance(
			String processDefinitionId,
			String launcherUserId, 
			String businessName,
			Map<String, Object> bpmVariables) throws ServiceException
	{
		identityService.setAuthenticatedUserId(launcherUserId);
		ProcessInstance processInstance;
		try {
			processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessName, bpmVariables);
		} catch (RDRuntimeException e) {
			// TODO FHJ 重构这个异常，查看下activiti中是不是只可能招聘ActivitiException
			throw new ServiceException(e.getMessage(), e);
		}
		return processInstance;
	}

}
