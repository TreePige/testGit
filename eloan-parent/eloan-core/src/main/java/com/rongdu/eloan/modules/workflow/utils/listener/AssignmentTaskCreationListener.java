package com.rongdu.eloan.modules.workflow.utils.listener;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.workflow.service.RDHistoryService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.ClaimTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.DirectUserBasedTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.EmptyTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.HeadRoleBasedTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.ManualTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.ProcessStartTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.RoleBasedTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.SelectedUserBasedTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.SuperiorForTaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.impl.SuperiorForUserDistributer;
import com.rongdu.eloan.modules.workflow.utils.observation.TaskAssignerCenter;

/**
 * 
 * 自定义一套规则来分配任务
 * 
 * @author FHJ
 *
 */
@SuppressWarnings("serial")
public abstract class AssignmentTaskCreationListener extends DefaultTaskListener {
	public static final String DEFAULT_ROLE_STRING = "ROLE:";
	public static final String HEAD_DEFAULT_ROLE_STRING = "HEAD:";
	public static final String SAME_AS_USER_TASK = "SAME_AS_TASK:";
	public static final String DIRECT_USER = "DIRECT_USER:";
	public static final String SUPERIOR_FOR_TASK = "SUPERIOR_FOR_TASK:";
	public static final String SUPERIOR_FOR_USER = "SUPERIOR_FOR_USER:";
	public static final String PROCESS_STARTER="PROCESS_STARTER";
	public static final String MANUAL="MANUAL:";
	public static final String CLAIM="CLAIM:";
	public static Logger logger = LoggerFactory.getLogger(AssignmentTaskCreationListener.class);

	public AssignmentTaskCreationListener() {
	}

	@Override
	public void onCreate(DelegateTask delegateTask) {
		
		logger.info("开始分配任务");
		
		//维护流程状态
		changeProcessState(delegateTask);
		
		// 分配任务
		assignTask(delegateTask);
		logger.info("开始分配结束");
	}

	protected void assignTask(DelegateTask delegateTask) {
		// 根据对应情况获取任务分配器
		TaskDistributer taskDistributer = getTaskdistributerByTaskProperty(delegateTask);
		// 用任务分配器分配任务
		taskDistributer.assignTask(new TaskWrapper(delegateTask));
		// TODO FHJ 这个用来测试
		TaskAssignerCenter.taskAssignee.set(delegateTask.getAssignee());
		TaskAssignerCenter.isNew = true;
	}

	/**
	 * 根据任务配置属性决定用哪种任务分配器
	 * 
	 * @param delegateTask
	 * @return
	 */
	public TaskDistributer getTaskdistributerByTaskProperty(
			DelegateTask delegateTask) {
		// TODO FHJ 由于assignee是会在数据库中直接关联查询的，所以在这里不要把规则定义在这个字段中。
		String assignee = delegateTask.getAssignee();
		if (StringUtils.isEmpty(assignee)) {
			// 这里属于没有办法拯救的错误，直接runtime exception抛出去
			throw new RDRuntimeException("流程配置出错");
		}
		TaskDistributer taskDistributer = null;

		// 非常重要！有几种情况
		// 1.
		// 使用者在每个可以驳回的结点或者之后的结点上都有可能执行过，所以第一先需要查询下这个任务结点在当下流程实例中最近是由哪个用户执行的，如果能查询出来，
		// 将该用户设置成当前任务的执行人
		// 2. 如果#1没有查询出结果，那么该任务结点没有被执行过，按照以下的逻辑走
		tryToRetrieveTaskAssigneeFromHistory(delegateTask);
		
		assignee = delegateTask.getAssignee();
		
		if (assignee.contains(HEAD_DEFAULT_ROLE_STRING)) {
			taskDistributer = new HeadRoleBasedTaskDistributer();
		}else if(assignee.contains(DEFAULT_ROLE_STRING)) {
			taskDistributer = new RoleBasedTaskDistributer();
		}else if (assignee.contains(SAME_AS_USER_TASK)) {
			taskDistributer = new SelectedUserBasedTaskDistributer();
		} else if (assignee.contains(SUPERIOR_FOR_TASK)) {
			taskDistributer = new SuperiorForTaskDistributer();
		} else if (assignee.contains(SUPERIOR_FOR_USER)) {
            taskDistributer = new SuperiorForUserDistributer();
        } else if (assignee.contains(DIRECT_USER)) {
            taskDistributer = new DirectUserBasedTaskDistributer();
        } else if(assignee.contains(PROCESS_STARTER)){
            taskDistributer = new ProcessStartTaskDistributer();
        }else if(assignee.contains(MANUAL)){
        	taskDistributer = new ManualTaskDistributer();
        }else if(assignee.contains(CLAIM)){
        	taskDistributer = new ClaimTaskDistributer();
        }else{
            taskDistributer = new EmptyTaskDistributer();
        }
        return taskDistributer;
	}

	/**
	 * 尝试获取历史中的任务执行者
	 * @param delegateTask
	 */
	public void tryToRetrieveTaskAssigneeFromHistory(DelegateTask delegateTask) {
		String processInstanceId = delegateTask.getProcessInstanceId();
		String taskDef = delegateTask.getTaskDefinitionKey();
		Map<String, Object> theLastHistoricInstance;
		try {
			RDHistoryService historyService = ApplicationContextHelperBean.getBean(RDHistoryService.class);
			theLastHistoricInstance = historyService.queryTheLatestHistoricTask(processInstanceId, taskDef);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new RDRuntimeException(e.getMessage());
		}
		if(theLastHistoricInstance != null) {
			// 把历史执行人设置到当前任务执行人里
			delegateTask.setAssignee(theLastHistoricInstance.get("ASSIGNEE_").toString());
		}
	}
	
	/**
	 * @description
	 * 查询当前任务的上一条任务ID
	 * @param processInstanceId
	 * @param historyService
	 * @return
	 * @author wangdk
	 * @return String
	 * @since  1.0.0
	*/
	protected String getPrevisousTaskId(String processInstanceId,
			HistoryService historyService) {
		int firstResult = 0;
		int maxResults = 1;
		List<HistoricTaskInstance> listPage = historyService.createHistoricTaskInstanceQuery()
		        .processInstanceId(processInstanceId).orderByTaskId().desc().listPage(firstResult, maxResults);
		return listPage.size() == 0 ? null : listPage.get(0).getId();
	}
	
	/**
	 * @description
	 * 维护流程业务状态
	 * @param delegateTask
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	public abstract void changeProcessState(DelegateTask delegateTask);

}