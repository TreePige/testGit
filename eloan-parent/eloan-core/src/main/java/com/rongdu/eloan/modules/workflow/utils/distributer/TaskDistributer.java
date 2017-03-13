package com.rongdu.eloan.modules.workflow.utils.distributer;


/**
 * 任务分配器接口
 *
 */
public interface TaskDistributer {
	/**
	 * 分配任务
	 * @param task 那个需要被分配执行人员的任务
	 */
	void assignTask(TaskWrapper task);
}
