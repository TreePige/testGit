package com.rongdu.eloan.modules.common.utils.converter.impl;

import com.rongdu.eloan.common.utils.DateUtil;
import com.rongdu.eloan.modules.common.domain.PubLoan;
import com.rongdu.eloan.modules.common.utils.converter.DataBeanConverter;
import com.rongdu.eloan.modules.common.utils.databean.BasicDataBean;
import com.rongdu.eloan.modules.common.utils.databean.LoanDataBean;

public class LoanModelConverter<T> extends DataBeanConverter<T>{
	protected PubLoan loan;
	private static final Byte NORMAL = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public T convert(BasicDataBean basicDataBean) {
		LoanDataBean loanDataBean = (LoanDataBean) basicDataBean;
		loan = new PubLoan();
		
		loan.setLoanTime(loanDataBean.getLoanTime());
		loan.setAccount(loanDataBean.getLoanAmount());
		loan.setBankAccount(loanDataBean.getBankCardId());
		loan.setBankFlag(loanDataBean.getBankFlag());
		loan.setCreateTime(DateUtil.now());
		loan.setCreator(loanDataBean.getLoginUserId());
		loan.setProcessinstanceid(loanDataBean.getProcessInstanceId());
		loan.setIsDelete(NORMAL);
		loan.setModifier(loanDataBean.getLoginUserId());
		loan.setModifyTime(DateUtil.now());
		loan.setOperator(loanDataBean.getLoginUserId());
		loan.setProjectId(loanDataBean.getProjectId());
		
		return (T) loan;
	}

}
