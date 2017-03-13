package com.rongdu.eloan.modules.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.dao.ApprovalCommentsDao;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.ApprovalCommentsService;

/**
 * 获取流程审批意见
 * 
 * @author wulb
 * @version 1.0
 * @since 2016-08-10
 */
@SuppressWarnings("rawtypes")
@Service(value = "approvalCommentsServiceImpl")
public class ApprovalCommentsServiceImpl extends BaseServiceImpl implements ApprovalCommentsService {
	private static final Logger log = LoggerFactory.getLogger(ApprovalCommentsServiceImpl.class);

	@Autowired
	private ApprovalCommentsDao approvalCommentsDao;

	@Override
	public Map<String, Object> getApprovalComment(String processInstanceId, String processState, String taskId)
			throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("processInstanceId", processInstanceId);
			params.put("processState", processState);
			params.put("taskId", taskId);
			return approvalCommentsDao.getApprovalComment(params);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	@Override
	public BaseDao getMapper() {
		return approvalCommentsDao;
	}
}