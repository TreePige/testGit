package com.rongdu.eloan.modules.workflow.utils.strategy.impl;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.TaskExecutionException;
import com.rongdu.eloan.modules.common.exception.TaskNotFoundException;
import com.rongdu.eloan.modules.workflow.utils.strategy.TaskExecutionStrategy;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiOptimisticLockingException;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class AbstractTaskExecutionStrategy implements TaskExecutionStrategy{
    private static final Logger logger = LoggerFactory.getLogger(AbstractTaskExecutionStrategy.class);

	/**
	 * 调用activiti API进行任务执行，子类尽量直接继承下来进行调用，不要override该方法。
	 * @param taskId 任务ID
	 * @param bpmValues 流程参数
	 * @param taskService 真正的activiti任务service
	 * @throws TaskNotFoundException
	 * @throws TaskExecutionException
	 */
	protected void doExecuteTask(String taskId, Map<String, Object> bpmValues, TaskService taskService) throws TaskNotFoundException, TaskExecutionException {
        ParamChecker.checkEmpty(taskId, "taskId");
        ParamChecker.checkEmpty(bpmValues, "bpmValues");
        ParamChecker.checkEmpty(taskService, "taskService");

        logger.info("开始执行任务[taskId:{}]", taskId);
        try {
			taskService.complete(taskId, bpmValues);
		} catch (ActivitiException e) {
			// 任何的RuntimeException都被包装成了ActivitiExeption
			// 这边应该有这么几种情况，
			// 1. taskId没有填写，但这种情况会在preCheckParams中处理：{"code":400,"msg":"任务执行失败：任务ID不能为空"}
			// 2. taskId填写了，但没有填写对：{"code":400,"msg":"Cannot find task with id 50001011"}
			// 3. taskId填写了，但任务已经执行完了：{"code":400,"msg":"Cannot find task with id 500010"}
			// 4. 业务方法中抛出错误信息：{"code":400,"msg":"Exception while invoking TaskListener: Exception while invoking TaskListener: 管理费率只能在0与1之间！"}
			String errorMsg = e.getMessage().replaceAll("Exception while invoking TaskListener: ", "");
			if(errorMsg != null && errorMsg.contains("Cannot find task with id")) {
				throw new TaskNotFoundException("任务执行失败：该任务可能已被执行，或者任务ID有误！");
			}

            // Activiti在并发任务中会抛出“乐观锁”（乐观锁会直接引起操作失败，以保证逻辑的正确性），这里把英文的描述转换成中文
			if(e instanceof ActivitiOptimisticLockingException) {
				errorMsg = "部分数据正在同时被其他人使用，本次操作失败，请稍候再试。";
			}

            logger.warn(errorMsg);
            throw new TaskExecutionException(errorMsg, e);
		}
	}
	
}
