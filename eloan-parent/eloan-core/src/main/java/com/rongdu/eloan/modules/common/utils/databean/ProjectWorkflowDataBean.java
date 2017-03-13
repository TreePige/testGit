package com.rongdu.eloan.modules.common.utils.databean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/**
 * 与流程相关的数据Bean
 *
 */
public class ProjectWorkflowDataBean extends BasicDataBean {
	/**
	 * 咨询ID,可以考虑把这个去掉
	 */
	protected Long consultId;
	/**
	 * 流程实例编号
	 */
	protected String processInstanceId;
    /**
     * 项目ID
     */
    protected Long projectId;
    /**
     * 项目名称
     */
    protected String projectName;
	/**
	 * 任务ID
	 */
	protected String taskId;
	/**
	 * 当前流程状态Code
	 */
	protected String processStateCode;
	/**
	 * 处理意见操作
	 */
	protected String nextStep;

	public Long getConsultId() {
		return consultId;
	}

	public void setConsultId(Long consultId) {
		this.consultId = consultId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessStateCode() {
		return processStateCode;
	}

	public void setProcessStateCode(String processStateCode) {
		this.processStateCode = processStateCode;
	}

	public String getNextStep() {
		return nextStep;
	}

	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}