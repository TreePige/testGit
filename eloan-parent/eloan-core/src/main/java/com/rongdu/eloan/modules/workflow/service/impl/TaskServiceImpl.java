package com.rongdu.eloan.modules.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rongdu.eloan.modules.common.exception.NoExecutionPrivilegeException;
import com.rongdu.eloan.modules.common.exception.ObjectNotFoundException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.exception.TaskExecutionException;
import com.rongdu.eloan.modules.common.exception.TaskNotFoundException;
import com.rongdu.eloan.modules.system.dao.SysOfficeDao;
import com.rongdu.eloan.modules.system.dao.SysRoleDao;
import com.rongdu.eloan.modules.system.service.SysUserService;
import com.rongdu.eloan.modules.workflow.service.RDTaskService;
import com.rongdu.eloan.modules.workflow.utils.factory.TaskExecutionStrategyFactory;
import com.rongdu.eloan.modules.workflow.utils.strategy.TaskExecutionStrategy;
import com.rongdu.eloan.modules.workflow.utils.strategy.TaskServiceWrapper;

/**
 * 流程任务Service
 * @author FHJ
 *
 */
@Service
public class TaskServiceImpl implements RDTaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
	private TaskService taskService;
	
	@Autowired
	private SysUserService userService;

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysOfficeDao sysOfficeDao;
	
	/**
	 * 直接调用Activiti的API来完成任务
	 * API内部用观察者模式（listener）维护了task的生命周期，设置在task的各个监听器会被调用，
	 * 所以需要在这里将Transactional加上以实现监听器与流程API的一起事务回滚
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void completeTask(String taskId, String executor, Map<String, Object> bpmValues) throws ServiceException {
		preCheckParams(taskId, executor);
		
		// TODO FHJ这个从系统配置中读取出来
		Object qualifier = "com.rongdu.eloan.modules.workflow.utils.strategy.impl.StrictOneUserExecutionStrategy";
		TaskExecutionStrategy taskExecutionStrategy;
		
		try {
			taskExecutionStrategy = TaskExecutionStrategyFactory.getInstance().getObject(qualifier);
			taskExecutionStrategy.executeTask(new TaskServiceWrapper(taskService), taskId, executor, bpmValues);
		} catch (ObjectNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (NoExecutionPrivilegeException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (TaskNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (TaskExecutionException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	private void preCheckParams(String taskId, String executor) throws ServiceException {
		if(StringUtils.isEmpty(taskId)){
			throw new ServiceException("任务执行失败：任务ID不能为空");
		}
		if(StringUtils.isEmpty(executor)){
			throw new ServiceException("任务执行失败：执行者名称不能为空");
		}
	}
	@Override
	public void claimTask(String taskId, String userName) throws ServiceException {
		preCheckParams(taskId, userName);
		try {
			taskService.claim(taskId, userName);
		} catch (ActivitiTaskAlreadyClaimedException e) {
			throw new ServiceException("任务已被认领", e);
		}
		
	}
}
