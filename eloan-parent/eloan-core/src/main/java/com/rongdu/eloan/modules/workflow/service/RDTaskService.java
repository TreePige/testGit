package com.rongdu.eloan.modules.workflow.service;

import java.util.Map;

import com.rongdu.eloan.modules.common.exception.ServiceException;


/**
 * 融都Task业务接口
 * @author FHJ
 *
 */
public interface RDTaskService {
	/**
	 * 完成任务
	 * @param taskId 任务ID
	 * @param executor 执行者名称
	 * @param bpmValues 流程变量
	 * @throws ServiceException
	 */
	void completeTask(String taskId, String executor, Map<String, Object> bpmValues) throws ServiceException;
	


	public void claimTask(String taskId, String userName) throws ServiceException; 
}
