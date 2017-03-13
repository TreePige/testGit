package com.rongdu.eloan.modules.workflow.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.modules.workflow.service.RDRepositoryService;

@Controller
@RequestMapping("/modules/workflow/controller/ProcessDefinitionController")
public class ProcessDefinitionController extends WorkflowBaseController {

	@Autowired
	private RDRepositoryService repositoryService;
	
	/**
	 * 部署流程
	 * @param request
	 * @param response
	 */
	@RequestMapping("deploy")
	public void deploy(
			@RequestParam(value = "resource")String resource,
			HttpServletRequest request,
			HttpServletResponse response) {
		ProcessDefinition processDefinition = repositoryService.deploy(resource);
		try {
			PrintWriter writer = response.getWriter();
			writer.write("processDenifitionId:" + processDefinition.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
