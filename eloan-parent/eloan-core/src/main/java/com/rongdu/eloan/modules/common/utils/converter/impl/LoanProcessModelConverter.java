package com.rongdu.eloan.modules.common.utils.converter.impl;

import com.rongdu.eloan.common.utils.DateUtil;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.domain.PubLoanprocess;
import com.rongdu.eloan.modules.common.utils.converter.DataBeanConverter;
import com.rongdu.eloan.modules.common.utils.databean.BasicDataBean;
import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;

/**
 * 
 * @author FHJ
 *
 * @param <T>
 */
public class LoanProcessModelConverter<T> extends DataBeanConverter<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T convert(BasicDataBean basicDataBean) {
		BasicServiceDataBean basicServiceDataBean = (BasicServiceDataBean)basicDataBean;
		
		PubLoanprocess loanprocess = new PubLoanprocess();
		loanprocess.setTaskId(basicServiceDataBean.getTaskId());
        loanprocess.setProjectId(basicServiceDataBean.getProjectId());
		loanprocess.setAmount(basicServiceDataBean.getAmountComment());
		loanprocess.setConsultId(basicServiceDataBean.getConsultId());
		loanprocess.setCreator(basicServiceDataBean.getLoginUserId());
		loanprocess.setIsDelete(SystemConstant.IS_DELETE_NORMAL);
		loanprocess.setOfficeId(basicServiceDataBean.getOfficeId());
		loanprocess.setReceiveType(basicServiceDataBean.getReceiveType());
		loanprocess.setProcessInstanceId(basicServiceDataBean.getProcessInstanceId());
		loanprocess.setRemark(basicServiceDataBean.getRemarkComment());
		loanprocess.setType(basicServiceDataBean.getNextStep());
		loanprocess.setProcessState(basicServiceDataBean.getProcessStateCode());
		loanprocess.setCreateTime(DateUtil.now());
		loanprocess.setHousingValueFaster(basicServiceDataBean.getHousingValueFaster());
		loanprocess.setManualAssignee(basicServiceDataBean.getManualAssignee());
        String category = null;
        if (SystemConstant.MATERIAL_TYPE.equals(basicServiceDataBean.getNextStep())) {
            category = basicServiceDataBean.getMaterialCategoryComment();
        } else if (SystemConstant.UNPASS_TYPE.equals(basicServiceDataBean.getNextStep())) {
            category = basicServiceDataBean.getRejectionCategoryComment(); //信贷拒贷标识
        } else if (SystemConstant.REJECT_PROCESS.equals(basicServiceDataBean.getNextStep())) {
        	category = basicServiceDataBean.getRejectionCategoryComment(); //车贷拒贷标识
        }
        loanprocess.setCategory(category);
		return (T) loanprocess;
	}
}
