package com.rongdu.eloan.modules.workflow.utils.strategy.impl;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.NoExecutionPrivilegeException;
import com.rongdu.eloan.modules.common.exception.TaskExecutionException;
import com.rongdu.eloan.modules.common.exception.TaskNotFoundException;
import com.rongdu.eloan.modules.workflow.utils.strategy.TaskServiceWrapper;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 严格的单一用户任务执行策略，
 * 只有任务的所有者有执行任务的权力
 * @author FHJ
 *
 */
public class StrictOneUserExecutionStrategy extends AbstractTaskExecutionStrategy {
    private static final Logger logger = LoggerFactory.getLogger(StrictOneUserExecutionStrategy.class);

	@Override
	public void executeTask(TaskServiceWrapper taskServiceWrapper, String taskId, String userId, Map<String, Object> bpmValues)
			throws NoExecutionPrivilegeException, TaskNotFoundException,
			TaskExecutionException {
        ParamChecker.checkEmpty(taskServiceWrapper, "taskServiceWrapper");
        ParamChecker.checkEmpty(taskId, "taskId");
        ParamChecker.checkEmpty(userId, "userId");
        ParamChecker.checkEmpty(bpmValues, "bpmValues");

		TaskService taskService = taskServiceWrapper.getTaskService();
		
		//　预先检查任务的所有权，如果userId不是taskId所对应任务的所有者，直接抛出异常
		preCheckTaskOwnship(taskId, userId, taskService);
		doExecuteTask(taskId, bpmValues, taskService);
	}
	
	private void preCheckTaskOwnship(String taskId, String executor, TaskService taskService) throws NoExecutionPrivilegeException, TaskNotFoundException {
		logger.info("开始检查任务的所有权");
        TaskQuery createTaskQuery = taskService.createTaskQuery();
		Task task = createTaskQuery.taskId(taskId).singleResult();
        if (task == null) {
            logger.error("查询不到当前任务[taskId:{}]！", taskId);
            throw new TaskNotFoundException("任务可能已经提交，请关闭窗口重试！");
        }
        String assignee = task.getAssignee();
		ParamChecker.checkEmpty(assignee, "assignee");
		
		if(!assignee.equals(executor)) {
			throw new NoExecutionPrivilegeException("任务执行失败，该任务不属于您！");
		}
	}
}
