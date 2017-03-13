package com.rongdu.eloan.modules.workflow.utils.strategy;

import java.util.Map;

import com.rongdu.eloan.modules.common.exception.NoExecutionPrivilegeException;
import com.rongdu.eloan.modules.common.exception.TaskExecutionException;
import com.rongdu.eloan.modules.common.exception.TaskNotFoundException;

/**
 * 任务执行策略
 * 
 * @author FHJ
 *
 */
public interface TaskExecutionStrategy {
	
	/**
	 * 执行任务
	 * @param taskServiceWrapper 工作流TaskService的包装
	 * @param taskId 任务ID
	 * @param userId 用户ID
	 * @param bpmValues 流程变量
	 * @throws NoExecutionPrivilegeException 如果该用户没有指定任务的权力，NoExecutionPrivilegeException将会被抛出。
	 * @throws TaskNotFoundException 如果任务没有找到，或者任务在前一时刻已经被执行，TaskNotFoundException将会被抛出。
	 * @throws TaskExecutionException 如果任务执行时，监听器中报错，TaskExecutionException将会被抛出。
	 */
	void executeTask(TaskServiceWrapper taskServiceWrapper, String taskId, String userId, Map<String, Object> bpmValues)
			throws NoExecutionPrivilegeException, TaskNotFoundException, TaskExecutionException;
}
