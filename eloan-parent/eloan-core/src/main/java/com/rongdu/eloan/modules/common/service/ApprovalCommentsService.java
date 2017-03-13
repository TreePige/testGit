package com.rongdu.eloan.modules.common.service;

import java.util.Map;

/** 
 * 获取审批意见service
* @author wulb
* @version 1.0
* @since 2016-08-10
*/
public interface ApprovalCommentsService{
	
	/**
	 * 根据流程ID获取流程审批意见.
	 *
	 * @param processId the process id
	 * @return the approval comment
	 * @throws Exception
	 */
	Map<String,Object> getApprovalComment(String processInstanceId,String processState,String taskId) throws Exception;
}
