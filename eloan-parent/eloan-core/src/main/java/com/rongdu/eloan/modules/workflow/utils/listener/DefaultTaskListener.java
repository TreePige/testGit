package com.rongdu.eloan.modules.workflow.utils.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTaskListener implements TaskListener {
	private static Logger logger = LoggerFactory.getLogger(DefaultTaskListener.class);

	/**
	 * 该方法是整个Listener的入口方法
	 * @param delegateTask
	 */
	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getEventName();
		logger.debug("{}", this);
		logger.debug("{} : {}", eventName, delegateTask);

		if ("create".equals(eventName)) {
			this.onCreate(delegateTask);
		}

		if ("assignment".equals(eventName)) {
			this.onAssignment(delegateTask);
		}

		if ("complete".equals(eventName)) {
			this.onComplete(delegateTask);
		}

		if ("delete".equals(eventName)) {
			this.onDelete(delegateTask);
		}

		((TaskEntity) delegateTask).setEventName(eventName);
	}

	public void onCreate(DelegateTask delegateTask) {
	}

	public void onAssignment(DelegateTask delegateTask) {
	}

	public void onComplete(DelegateTask delegateTask) {
	}

	public void onDelete(DelegateTask delegateTask) {
	}
}
