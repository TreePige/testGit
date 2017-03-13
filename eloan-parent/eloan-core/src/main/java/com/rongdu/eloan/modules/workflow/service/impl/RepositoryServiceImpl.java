package com.rongdu.eloan.modules.workflow.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.utils.ApprovalResultsUtil;
import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysRoleDao;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.workflow.dao.ButtonDao;
import com.rongdu.eloan.modules.workflow.service.RDRepositoryService;

/**
 * 获取Activiti资源
 * @author FHJ
 *
 */
@Service("repositoryServiceImpl")
public class RepositoryServiceImpl implements RDRepositoryService{

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SysRoleDao sysRoleDao;
    
    @Autowired
    private ButtonDao buttonDao;
	

	@Override
	public ProcessDefinition deploy(String resource) {
		Deployment deploy = repositoryService.createDeployment().addClasspathResource(resource).deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		return processDefinition;
	}

	@Override
	public Collection<Map<String,String>> queryButtonNameList(String processInstanceId) throws ServiceException {
		ParamChecker.checkEmpty(processInstanceId, "processInstanceId");
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		
//		ProcessInstance pii = runtimeService.
		// 获取该流程实例下当前活动结点的activityId
        String activityId = null;
        try {
            activityId = pi.getActivityId();
        } catch (Exception e) {
            throw new ServiceException("获取不到当前激活的流程结点，流程可能已经结束！", e);
        }

        String processDefinitionId = pi.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		
		ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
		
		// 获取当前activity活动结点的下一个结点
        return queryPossibleComments(activity);
	}
	
	@Override
	public Collection<Map<String, String>> queryButtonNameListByTaskId(
			String taskId) throws ServiceException {

		ParamChecker.checkEmpty(taskId, "taskId");
		
		
		// 获取该流程实例下当前活动结点的activityId
        String activityId = null;
        String processDefinitionId = null;
		
		Map<String, Object> resultMap;
		try {
			resultMap = buttonDao.queryActivityId(taskId);
			
			processDefinitionId = resultMap.get("procDefId").toString();
        	activityId = resultMap.get("activityId").toString();
		} catch (Exception e) {
			 throw new ServiceException("获取不到当前激活的流程结点，流程可能已经结束！", e);
		}
		
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		
		ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
		
		// 获取当前activity活动结点的下一个结点
        return queryPossibleComments(activity);
	
	}

    @Override
    public Collection<Map<String, String>> queryPossibleComments(String processInstanceId, String taskType, Long roleId)
            throws ServiceException {
        String roleName = getRoleName(roleId);
        HashSet<Map<String, String>> allPossibleComments = ApprovalResultsUtil.getApprovalResults(roleName);
        return allPossibleComments;
    }

    private List<ActivityImpl> getActivities(String processDefinitionId, List<String> activityNameList) {
        List<ActivityImpl> activities = new ArrayList<ActivityImpl>();

        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);

        for (String activityName : activityNameList) {
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityName);
            if (activityImpl != null) {
                activities.add(activityImpl);
            }
        }

        return activities;
    }

    private Collection<Map<String, String>> queryPossibleComments(ActivityImpl activity) throws ServiceException {
        List<PvmTransition> transitions = activity.getOutgoingTransitions();
        PvmTransition pvmTransition = transitions.get(0);
        PvmActivity destination = pvmTransition.getDestination();

        List<Map<String, String>> possibilityList = new ArrayList<Map<String, String>>();

        // 判断是不是分支结点
        if (isExclusiveNode(destination)) {
            // 取分支结点散发出去的每一条线的名称作为按钮名称，并且按flow的id从小到大排序,
            // 所以业务上如果想要xxx处理意见排列在第一个，必须修改流程图配置，确保xxx所在的flow id最小。
            List<PvmTransition> outgoingTransitions = destination.getOutgoingTransitions();
            Collections.sort(outgoingTransitions, new Comparator<PvmTransition>() {
                @Override
                public int compare(PvmTransition o1, PvmTransition o2) {
                	System.out.println("ddd");
                    return o1.getId().compareTo(o2.getId());
                }
            });
            for (PvmTransition pvmTst : outgoingTransitions) {
                Map<String, String> possibility = new HashMap<String, String>();

                String text = (String) pvmTst.getProperty("name");
                String value = (String) pvmTst.getProperty("documentation");
                possibility.put("text", text);
                possibility.put("value", value);

                possibilityList.add(possibility);
            }
        } else {
            // 直接取destination与活动结点中间的线的名称作为按钮名称
            Map<String, String> button = new HashMap<String, String>();

            String text = (String) pvmTransition.getProperty("name");
            String value = (String) pvmTransition.getProperty("documentation");
            button.put("text", text);
            button.put("value", value);

            possibilityList.add(button);
        }

        return possibilityList;
    }

    private boolean isExclusiveNode(PvmActivity destination) {
		String id = destination.getId();
		if(id.contains("exclusive")) {
			return true;
		} else {
			return false;
		}
	}

    private String getRoleName(Long roleId) throws ServiceException {
        SysRole role = null;
        try {
            role = sysRoleDao.getByPrimary(roleId);
        } catch (Exception e) {
            throw new ServiceException("角色查询失败:" + e.getMessage(), e);
        }
        return role.getNid();
    }
}
