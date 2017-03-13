package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;
import org.activiti.engine.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 直接用户 任务分配器
 * 也就是说，任务会直接分配给指定的用户
 * @author FHJ
 *
 */
public class DirectUserBasedTaskDistributer implements TaskDistributer{
    private static final Logger logger = LoggerFactory.getLogger(DirectUserBasedTaskDistributer.class);

    /**
     *
     * @param taskWrapper
     */
	@Override
	public void assignTask(TaskWrapper taskWrapper) {
        ParamChecker.checkEmpty(taskWrapper, "taskWrapper");

        DelegateTask delegateTask = taskWrapper.getDelegateTask();

        String assignee = delegateTask.getAssignee();
        // "DIRECT_USER:process_launcher" -> "process_launcher"
        String processVar = assignee.substring(assignee.indexOf(":") + 1);

        String userName = (String) delegateTask.getVariable(processVar);

        if (StringUtils.isEmpty(userName)) {
            throw new RDRuntimeException("后续任务分配失败。");
        }

        logger.info("任务[taskId:{}]被分配给[{}]", delegateTask.getId(), userName);
        delegateTask.setAssignee(userName);
    }

}
