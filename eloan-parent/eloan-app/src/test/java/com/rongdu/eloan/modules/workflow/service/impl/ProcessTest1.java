package com.rongdu.eloan.modules.workflow.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *	@Description 测试流程
 *  @author fzc,fzc@erongdu.com
 *  @CreatTime 2016年7月11日 下午4:43:59
 *  @since version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration(name = "parent", locations = "classpath:/config/spring/*-beans.xml"),
	@ContextConfiguration(name = "child", locations = "classpath:/config/web/web-main.xml")

})
public class ProcessTest1 {

	  @Autowired
	  private RuntimeService runtimeService;

	  @Autowired
	  private TaskService taskService;
	  
//	  @Autowired
//	  private StartProcessUtil startProcessUtil;

	  /**
	   * @description 初审->投保->是否复核->人工复核->放款
	   * @author fzc
	   * @return void
	   * @since  1.0.0
	   */
	  @Test
	  public void simpleProcessTest() {
		Map<String,Object> variables = new HashMap<>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("WXProcess", variables);//测试流程图
		String processInstanceId = processInstance.getProcessInstanceId();
		
		//初审
		Execution execution = runtimeService.createExecutionQuery()
				  .processInstanceId(processInstanceId)
				  .activityId("receivetask-intial-audit")
				  .singleResult();
		
		variables.put("comment", "pass");
	    runtimeService.signal(execution.getId(),variables);
	    
	    /*//风控
	    Execution execution2 = runtimeService.createExecutionQuery()
			  .processInstanceId(processInstanceId)
			  .activityId("receivetask-riskControl")
			  .singleResult();
	
	    variables.put("comment", "pass");
        runtimeService.signal(execution2.getId(),variables);*/
        
        //投保
	    Execution execution3 = runtimeService.createExecutionQuery()
			  .processInstanceId(processInstanceId)
			  .activityId("receivetask-insurance")
			  .singleResult();
	
	    variables.put("comment", "pass");
        runtimeService.signal(execution3.getId(),variables);
	    
	    //是否复核
  		Execution execution4 = runtimeService.createExecutionQuery()
  				  .processInstanceId(processInstanceId)
  				  .activityId("receivetask-ifReAudit")
  				  .singleResult();
	    variables.put("amount", 100001);//大于10万进入复核
	    runtimeService.signal(execution4.getId(),variables);
	    
	    //人工复核
	    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	    /*assertEquals("人工复核", task.getName());
	    variables.put("comment", "pass");
	    taskService.complete(task.getId(),variables);
	    
	    //放款
  		Execution execution5 = runtimeService.createExecutionQuery()
  				  .processInstanceId(processInstanceId)
  				  .activityId("receivetask-loan")
  				  .singleResult();
	    variables.put("comment", "refuse");//自动放款拒绝
	    runtimeService.signal(execution5.getId(),variables);
	    
	    //人工放款
	    Task task2 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	    assertEquals("人工放款", task2.getName());
	    variables.put("comment", "loan");
	    taskService.complete(task2.getId(),variables);*/
	  }
	  
	  /**
	   * @description 黑名单->风控->投保->人工投保->是否复核->人工复核->放款->人工放款
	   * @author fzc
	   * @return void
	   * @since  1.0.0
	   */
	  @Test
	  public void simpleProcessTest2() {
		Map<String,Object> variables = new HashMap<>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("WXProcess", variables);//测试流程图
		String processInstanceId = processInstance.getProcessInstanceId();
		
		//初审
		Execution execution = runtimeService.createExecutionQuery()
				  .processInstanceId(processInstanceId)
				  .activityId("receivetask-intial-audit")
				  .singleResult();
		
		variables.put("comment", "pass");
	    runtimeService.signal(execution.getId(),variables);
	    
	    /*//风控
	    Execution execution2 = runtimeService.createExecutionQuery()
			  .processInstanceId(processInstanceId)
			  .activityId("receivetask-riskControl")
			  .singleResult();
	
	    variables.put("comment", "pass");
        runtimeService.signal(execution2.getId(),variables);*/
        
        //投保
	    Execution execution3 = runtimeService.createExecutionQuery()
			  .processInstanceId(processInstanceId)
			  .activityId("receivetask-insurance")
			  .singleResult();
	
	    variables.put("comment", "noResponse");
        runtimeService.signal(execution3.getId(),variables);
        
        //人工投保
	    Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	    assertEquals("system",task1.getAssignee());
	    assertEquals("人工投保", task1.getName());
	    variables.put("comment", "pass");
	    taskService.complete(task1.getId(),variables);
	    
	    //是否复核
  		Execution execution4 = runtimeService.createExecutionQuery()
  				  .processInstanceId(processInstanceId)
  				  .activityId("receivetask-ifReAudit")
  				  .singleResult();
	    variables.put("amount", 100001);//大于10万进入复核
	    runtimeService.signal(execution4.getId(), variables);
	    
	    //人工复核
	    Task task2 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	    assertEquals("system",task2.getAssignee());
	    assertEquals("人工复核", task2.getName());
	    variables.put("comment", "pass");
	    taskService.complete(task2.getId(),variables);
	    
	    //放款
  		Execution execution5 = runtimeService.createExecutionQuery()
  				  .processInstanceId(processInstanceId)
  				  .activityId("receivetask-loan")
  				  .singleResult();
	    variables.put("comment", "refuse");//自动放款拒绝
	    runtimeService.signal(execution5.getId(),variables);
	    
	    //人工放款
	    Task task3 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	    assertEquals("system",task3.getAssignee());
	    assertEquals("人工放款", task3.getName());
	    variables.put("comment", "loan");
	    taskService.complete(task3.getId(),variables);
	  }

	  @Test
	  public void simpleProcessTest3() {
	  Map<String,Object> variables = new HashMap<>();
	  //初审
	  Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId("1290001")
				.activityId("receivetask-intial-audit")
				.singleResult();
		
	  variables.put("comment", "pass");
	  runtimeService.signal(execution.getId(),variables);
  }
}
