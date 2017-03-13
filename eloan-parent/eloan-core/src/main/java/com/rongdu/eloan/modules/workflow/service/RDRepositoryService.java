package com.rongdu.eloan.modules.workflow.service;

import java.util.Collection;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;

import com.rongdu.eloan.modules.common.exception.ServiceException;

/**
 * 融都工作流仓库Service
 */
public interface RDRepositoryService {

    ProcessDefinition deploy(String resource);


    /**
     * 查询指定流程实例中，活动任务的按钮名字，此方法只适用于串行流程
     *
     * @param processInstanceId
     * @return
     */
    Collection<Map<String, String>> queryButtonNameList(String processInstanceId) throws ServiceException;

    /**
     * 获取几个结点所有可能的处理意见，如果相同，将会被合并
     *
     *
     *
     * @param processInstanceId
     * @param taskType
     * @param roleId
     * @return
     * @throws ServiceException
     */
    Collection<Map<String, String>> queryPossibleComments(String processInstanceId, String taskType, Long roleId) throws ServiceException;
    
    /**
     * 会签节点,查询任务路由
     *
     * @param TaskId
     * @return
     */
    Collection<Map<String, String>> queryButtonNameListByTaskId(String taskId) throws ServiceException;
}
