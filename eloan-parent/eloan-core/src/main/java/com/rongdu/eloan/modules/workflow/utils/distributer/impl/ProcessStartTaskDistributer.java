package com.rongdu.eloan.modules.workflow.utils.distributer.impl;

import org.activiti.engine.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.ApplicationContextHelperBean;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysUserService;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskDistributer;
import com.rongdu.eloan.modules.workflow.utils.distributer.TaskWrapper;

/**
 *	@Description 分配任务给流程启动者
 *  @author wtc,wtc@erongdu.com
 *  @CreatTime 2015年6月25日 下午4:24:41
 *  @since version 1.0.0
 */
public class ProcessStartTaskDistributer implements TaskDistributer {
    private static final Logger logger = LoggerFactory.getLogger(ProcessStartTaskDistributer.class);
    @Override
    public void assignTask(TaskWrapper task) {
        ParamChecker.checkEmpty(task, "taskWrapper");
        DelegateTask delegateTask = task.getDelegateTask();
        String userName = delegateTask.getVariable(SystemConstant.PROCESS_LAUNCHER,String.class);
        Long userRoleId = delegateTask.getVariable(SystemConstant.PROCESS_LAUNCHER_ROLEID,Long.class);
        if(266L == userRoleId || 1 == userRoleId){//客服专员A||系统管理员
	        if (StringUtils.isEmpty(userName)) {
	            throw new RDRuntimeException("后续任务分配失败。");
	        }
	        logger.info("任务[taskId:{}]被分配给[{}]", delegateTask.getId(), userName);
	        delegateTask.setAssignee(userName);
        }else if(271L == userRoleId){//报单专员
    		String assignee = delegateTask.getAssignee();
    		// "role:业务部" -> "业务部"
    		String roleName = assignee.substring(assignee.indexOf(":") + 1);

    		SysUserService sysUserService = ApplicationContextHelperBean.getBean(SysUserService.class);
    		
    		SysUser sysUser;
    		
    		try {
    			String processInstanceId = delegateTask.getProcessInstanceId();
    			String taskDefinition = null;
    			// TODO FHJ查询出来的人必须和启动流程的用户是同一个机构
    			// 首先查询出启动流程的用户
    			String processLauncher = (String) delegateTask.getVariable(SystemConstant.PROCESS_LAUNCHER);
    			SysUser launcher = sysUserService.getUserByUserName(processLauncher);
    			
    			//sysUser = sysUserService.queryTheLeastBusyUserByHeadRoleName(roleName, processInstanceId, taskDefinition);
    			sysUser = sysUserService.queryTheLeastBusyUserByHeadRoleName(roleName,launcher.getOfficeId(),processInstanceId, taskDefinition);
    		} catch (ServiceException e) {
    			throw new RDRuntimeException(e.getMessage(), e);
    		}

            if (sysUser == null) {
                throw new RDRuntimeException("后续任务分配失败。");
            }
    		
    		delegateTask.setAssignee(sysUser.getUserName());
        }
    }

}
