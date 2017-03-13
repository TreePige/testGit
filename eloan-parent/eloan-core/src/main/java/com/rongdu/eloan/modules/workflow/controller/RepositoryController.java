package com.rongdu.eloan.modules.workflow.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.workflow.service.RDRepositoryService;
import com.rongdu.eloan.modules.workflow.service.RDTaskService;

@Controller
@RequestMapping("/modules/workflow/controller/RepositoryController")
public class RepositoryController extends WorkflowBaseController {

	@Autowired
	private RDRepositoryService repositoryService;

	@Autowired
	private RDTaskService taskService;

	@RequestMapping("/queryButtonNameList")
	public void queryButtonNameList(String processInstanceId, HttpServletResponse response) throws ServiceException {
		Map<String, Object> res = new HashMap<String, Object>();

		List<Map<String, String>> queryButtonNameList = (List<Map<String, String>>) repositoryService
				.queryButtonNameList(processInstanceId);

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		res.put(Constant.RESPONSE_DATA, queryButtonNameList);

		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/queryButtonNameListByTaskId")
	public void queryButtonNameListByTaskId(String taskId, HttpServletResponse response,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId)
			throws ServiceException {
		Map<String, Object> res = new HashMap<String, Object>();
		List<Map<String, String>> queryButtonNameList = (List<Map<String, String>>) repositoryService
				.queryButtonNameListByTaskId(taskId);
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		res.put(Constant.RESPONSE_DATA, queryButtonNameList);
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/queryPossibleComments")
	public void queryPossibleComments(String processInstanceId, String taskType, HttpServletResponse response,
			HttpServletRequest request) throws ServiceException {
		Map<String, Object> res = new HashMap<String, Object>();

		Long roleId = getRoleId(request);

		Collection<Map<String, String>> possibleComments = repositoryService.queryPossibleComments(processInstanceId,
				taskType, roleId);

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		res.put(Constant.RESPONSE_DATA, possibleComments);

		ServletUtils.writeToResponse(response, res);
	}
}
