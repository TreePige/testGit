package com.rongdu.eloan.modules.workflow.utils.factory;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.ObjectNotFoundException;
import com.rongdu.eloan.modules.common.utils.factory.ObjectFactory;
import com.rongdu.eloan.modules.workflow.utils.strategy.TaskExecutionStrategy;

public class TaskExecutionStrategyFactory implements ObjectFactory<TaskExecutionStrategy> {

	private static TaskExecutionStrategyFactory instance = new TaskExecutionStrategyFactory();

	public static TaskExecutionStrategyFactory getInstance() {
		return instance;
	}

	@Override
	public TaskExecutionStrategy getObject(Object qualifier) throws ObjectNotFoundException {
        ParamChecker.checkEmpty(qualifier, "qualifier");
		TaskExecutionStrategy strategy = null;
		try {
			Class<?> clazz = Class.forName((String)qualifier);
			strategy = (TaskExecutionStrategy) clazz.newInstance();
		} catch (Exception e) {
            throw new ObjectNotFoundException("任务执行策略 初始化失败！");
        }
        return strategy;
	}

}
