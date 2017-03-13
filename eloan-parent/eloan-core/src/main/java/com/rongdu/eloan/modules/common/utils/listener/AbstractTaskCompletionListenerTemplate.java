package com.rongdu.eloan.modules.common.utils.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.utils.databean.BasicDataBean;
import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;
import com.rongdu.eloan.modules.common.utils.databean.ProjectWorkflowDataBean;
import com.rongdu.eloan.modules.common.utils.parser.ClassTypeParser;
import com.rongdu.eloan.modules.common.utils.parser.impl.DefaultClassTypeParser;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.workflow.utils.listener.DefaultTaskListener;

/**
 * 抽象监听器模板(本类中定义整个任务执行的过程，从参数验证，参数解析，到初始化参数，再到执行Service)
 * 模板方法模式
 * @author FHJ
 */
public abstract class AbstractTaskCompletionListenerTemplate extends DefaultTaskListener{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractTaskCompletionListenerTemplate.class);
	private ClassTypeParser defaultClassTypeParser = new DefaultClassTypeParser();

	/**
	 * 整个过程大概分成以下几个部分
	 * 验证参数
	 * 解析Databean(每个Listener有各自不同的解析过程)
	 * 初始化登录与任务信息(大家都一样，所以直接在抽象类中实现，子类直接继承过来用)
	 * 获取Service并且调用它
	 * @param delegateTask
	 */
	@Override
	public void onComplete(DelegateTask delegateTask) {
		logger.info("当前任务结点：{}", delegateTask.getName());
		// 检查流程配置
		 checkProcessConfig(delegateTask);
		// 检查权限
		checkPriviledge(delegateTask);
		// 解析Databean
		BasicServiceDataBean serviceDataBean = parseDataBean(delegateTask, defaultClassTypeParser);
		// 初始化登录与任务信息
		initDataBean(serviceDataBean, delegateTask);
		// 调用具体Service层代码
		doComplete(serviceDataBean, delegateTask);

		afterComplete(delegateTask);
	}

	/**
	 * 将展示层传来的参数解析成ServiceDataBean
	 * @param delegateTask
	 * @param classTypeParser
	 * @return
	 */
	protected abstract BasicServiceDataBean parseDataBean(DelegateTask delegateTask, ClassTypeParser classTypeParser);

	/**
	 * 初始化登录与任务信息
	 * @param delegateTask
	 */
	protected void initDataBean(BasicServiceDataBean serviceDataBean, DelegateTask delegateTask) {
		initLoginInfoParams(serviceDataBean, delegateTask);
		initTaskInfoParams(serviceDataBean, delegateTask);
	}

	/**
	 * 检查执行者权限，如果用户没有执行的权限，会抛出RDRuntimeException
	 * @param delegateTask
	 */
	private void checkPriviledge(DelegateTask delegateTask) {
		// TODO FHJ
	}

	/**
	 * 检查流程配置
	 * @param delegateTask
	 */
	private void checkProcessConfig(DelegateTask delegateTask) {
		String assignee = delegateTask.getAssignee();
		if(StringUtils.isEmpty(assignee)) {
			logger.error("致命错误：流程配置出错,任务: " + delegateTask.getName() + " 结点执行人不能为空！");
			throw new RDRuntimeException("致命错误：流程配置出错,任务: " + delegateTask.getName() + " 结点执行人不能为空！");
		}
		
		SysUser loginVariables = (SysUser) delegateTask.getVariable(SystemConstant.LOGIN_INFO_VARIABLES);
        SysRole role = (SysRole) delegateTask.getVariable(SystemConstant.LOGIN_ROLE_INFO_VARIABLES);
        if(loginVariables == null || role == null) {
            throw new RDRuntimeException("致命错误,登录信息不全。");
		}
	}

	/**
	 * 如果子类在执行完操作后有后续操作，可以自行Override本方法进行扩展
	 * @param delegateTask
	 * @return
	 */
	protected void afterComplete(DelegateTask delegateTask) {}
	
	/**
	 * 子类具体实现各自监听业务
	 * @param serviceDataBean
	 * @param delegateTask
	 */
	protected abstract void doComplete(BasicServiceDataBean serviceDataBean, DelegateTask delegateTask);
	
	/**
	 * 设置登录者信息
     * @param basicDataBean
     * @param delegateTask
     */
	private void initLoginInfoParams(
            BasicDataBean basicDataBean,
            DelegateTask delegateTask) {
        SysUser loginUser = (SysUser) delegateTask.getVariable(SystemConstant.LOGIN_INFO_VARIABLES);
        SysRole loginRole = (SysRole) delegateTask.getVariable(SystemConstant.LOGIN_ROLE_INFO_VARIABLES);

        basicDataBean.setLoginUserId(loginUser.getId());
		basicDataBean.setOfficeId(loginUser.getOfficeId());
		basicDataBean.setUserName(loginUser.getUserName());

        basicDataBean.setLoginUserRoleId(loginRole.getId());
	}
	
	/**
	 * 设置任务信息
	 * @param projectWorkflowDataBean
	 * @param delegateTask
	 */
	private void initTaskInfoParams(
			ProjectWorkflowDataBean projectWorkflowDataBean,
			DelegateTask delegateTask) {
		String taskId = delegateTask.getId();
		projectWorkflowDataBean.setTaskId(taskId);
	}
}
