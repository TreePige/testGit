package com.rongdu.eloan.modules.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.parser.impl.DefaultClassTypeParser;
import com.rongdu.eloan.modules.workflow.service.RDProcessInstanceService;

@Controller
@RequestMapping("/modules/workflow/controller/ProcessInstanceController")
public class ProcessInstanceController extends WorkflowBaseController{
	@Autowired
	private RDProcessInstanceService processInstanceService;

	private DefaultClassTypeParser defaultClassTypeParser = new DefaultClassTypeParser();
	
	/**
	 * 启动流程实例
	 * @param processDefinitionId 流程定义ID
	 * @param businessName 流程业务名称
	 * @param serviceVariables 业务相关参数
	 * @param processVariables 流程相关参数
	 * @throws ServiceException 
	 */
	@RequestMapping("/startProcessInstance")
	public void startProcessInstance(
			@RequestParam(value = "processDefinitionId")String processDefinitionId,
			@RequestParam(value = "businessName")String businessName,
			@RequestParam(value = "serviceVariables", required = false)String serviceVariables,
			@RequestParam(value = "processVariables", required = false)String processVariables,
			HttpServletRequest request, HttpServletResponse response) throws ServiceException
	{
		Map<String, Object> processVarMap = defaultClassTypeParser.parse(processVariables, Map.class);

		String launcherUserId = "100";//getSysUser().getId().toString();
		Map<String, Object> bpmVariables = new HashMap<String, Object>();
		
		// 准备bpm变量集合
		prepareServiceParams(serviceVariables, bpmVariables);
		prepareProcessParams(processVarMap, bpmVariables);
		
		ProcessInstance processInstance = processInstanceService.startProcessInstance(processDefinitionId,
				launcherUserId, businessName, bpmVariables);
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		res.put(Constant.RESPONSE_DATA, "processInstanceId:" + processInstance.getId());
		
		ServletUtils.writeToResponse(response, res);
	}
	
}
