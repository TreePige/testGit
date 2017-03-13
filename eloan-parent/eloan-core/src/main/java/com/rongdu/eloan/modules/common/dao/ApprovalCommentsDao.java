package com.rongdu.eloan.modules.common.dao;

import java.util.Map;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

/**
 * 查询流程处理意见
 * @author wulb	
 * @version 1.0
 * @since 2016-08-10
 */
@SuppressWarnings("rawtypes")
@RDBatisDao 
public interface ApprovalCommentsDao extends BaseDao{
	/**
	 * 根据流程ID获取流程审批意见.
	 * @param params
	 * @return the approval comment
	 */
	Map<String,Object> getApprovalComment(Map<String, Object> params);
}
