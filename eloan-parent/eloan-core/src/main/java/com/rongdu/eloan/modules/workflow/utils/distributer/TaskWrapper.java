package com.rongdu.eloan.modules.workflow.utils.distributer;

import org.activiti.engine.delegate.DelegateTask;

/**
 * 对activiti原生API的一层包装，避免高层设计直接直接与activiti耦合
 * @author FHJ
 *
 */
public class TaskWrapper {
	private DelegateTask delegateTask;

	public TaskWrapper() {
		super();
	}

	public TaskWrapper(DelegateTask delegateTask) {
		super();
		this.delegateTask = delegateTask;
	}

	public DelegateTask getDelegateTask() {
		return delegateTask;
	}

	public void setDelegateTask(DelegateTask delegateTask) {
		this.delegateTask = delegateTask;
	}
	
}
