package com.rongdu.eloan.modules.common.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.ApprovalCommentsService;

/**
 * 审批意见查询.
 * 
 * @version 1.0
 * @author wulb
 * @since 2016-08-10
 */
@RequestMapping("/modules/common/action/ApprovalCommentsAction")
@Controller
public class ApprovalCommentsAction extends BaseAction {
	@Autowired
	private ApprovalCommentsService approvalCommentsService;

	/**
	 * 根据流程ID获取流程审批状态
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @author wulb
	 * @throws ServiceException
	 * @created 2016-08-10
	 */
	@RequestMapping("/getApprovalComment.htm")
	public void getApprovalComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String processInstanceId = this.paramString(request, "processInstanceId");
		String processState = this.paramString(request, "processState");
		String taskId = this.paramString(request, "taskId");
		Map<String, Object> rs = approvalCommentsService.getApprovalComment(processInstanceId, processState, taskId);
		if (rs == null) {
			rs = new HashMap<String, Object>();
		}
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, "流程审批状态获取成功！");
		returnMap.put(Constant.RESPONSE_DATA, rs);
		ServletUtils.writeToResponse(response, returnMap);
	}
}
