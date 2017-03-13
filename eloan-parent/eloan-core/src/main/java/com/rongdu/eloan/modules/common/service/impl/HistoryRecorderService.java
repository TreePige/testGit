package com.rongdu.eloan.modules.common.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.rongdu.eloan.modules.common.dao.PubLoanprocessDao;
import com.rongdu.eloan.modules.common.domain.PlConsult;
import com.rongdu.eloan.modules.common.domain.PubLoanprocess;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.BusinessBaseService;
import com.rongdu.eloan.modules.common.service.HistoryRecordableService;
import com.rongdu.eloan.modules.common.service.PlConsultService;
import com.rongdu.eloan.modules.common.utils.converter.impl.LoanProcessModelConverter;
import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;

public abstract class HistoryRecorderService extends BusinessBaseService implements HistoryRecordableService {
    private static final String REJECTED = "rejected";
    private static final String GAVE_UP = "gave-up";
    private static final String LOANED = "loaned";
    private static final String NEXT_STEP_REJECT_PROCESS = "rejectProcess";
    private static final String NEXT_STEP_CREDIT_CUSTOMER_GIVE_UP = "customerGiveUpProcess";
    private static final String NEXT_STEP_LOAN_MONEY_SUCCESS = "loanMoneySuccess";
    private static final String NEXT_STEP_COMFIRM_GIVE_UP = "comfirm-give-up";
    private static final String NEXT_STEP_CUSTOMER_GIVE_UP = "customerGiveUp";
    private static final String NEXT_STEP_DEDUCT_MONEY_SUCCESS = "deductMoneySuccess";
    private static final String NEXT_STEP_CREDIT_UNPASS = "unpass";
    private static final String NEXT_STEP_CHANGEINSURANCESUCCESS = "changeInsuranceSuccess";
    private static final String DEDUCTED = "deducted";

    @Autowired
	private PubLoanprocessDao pubLoanprocessDao;
    @Resource(name="plConsultServiceImpl")
    private PlConsultService plConsultService;
	/**
	 * 记录贷款处理(审批)日志
	 * @param basicServiceDataBean
	 * @throws ServiceException
	 */
	@Override
	public void recordLoanProcessHistory(BasicServiceDataBean basicServiceDataBean) throws Exception {
		super.preCheckServiceParams(basicServiceDataBean);
        super.preCheckWorkflowParams(basicServiceDataBean);
        
		doRecordLoanProcessHistory(basicServiceDataBean);
        // 处理信贷流程结束的状态变更
        dealWithCreditEndPoint(basicServiceDataBean);
        // 处理车贷流程结束的状态变更
        dealWithCarEndPoint(basicServiceDataBean);
        // 处理通用的几个流程结束状态
        dealWithGenericEndPoint(basicServiceDataBean);
    }

	@Deprecated
    protected void dealWithGenericEndPoint(BasicServiceDataBean basicServiceDataBean) throws Exception {
        if (NEXT_STEP_LOAN_MONEY_SUCCESS.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, LOANED);
        }
        if (NEXT_STEP_CHANGEINSURANCESUCCESS.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, NEXT_STEP_CHANGEINSURANCESUCCESS);
        }
    }

    private void dealWithCarEndPoint(BasicServiceDataBean basicServiceDataBean) throws Exception {
        // 以下几种情况会使车贷流程结束,并且需要更新咨询的状态
        if (NEXT_STEP_COMFIRM_GIVE_UP.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, GAVE_UP);
        }
        
        if (NEXT_STEP_CUSTOMER_GIVE_UP.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, GAVE_UP);
        }

        if (NEXT_STEP_DEDUCT_MONEY_SUCCESS.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, DEDUCTED);
        }
                
        if (NEXT_STEP_REJECT_PROCESS.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, REJECTED);
        }
    }

    public void dealWithCreditEndPoint(BasicServiceDataBean basicServiceDataBean) throws Exception {
        // 以下几种情况会使信贷流程结束,并且需要更新咨询的状态
        if(NEXT_STEP_CREDIT_CUSTOMER_GIVE_UP.equals(basicServiceDataBean.getNextStep())){
            finishProcessWithState(basicServiceDataBean, GAVE_UP);
        }
        
        if (NEXT_STEP_CREDIT_UNPASS.equals(basicServiceDataBean.getNextStep())) {
            finishProcessWithState(basicServiceDataBean, REJECTED);
        }
    }
    
    /**
     * @description
     * 暂留.   后续最好自行扩展.
     * @param basicServiceDataBean
     * @throws ServiceException
     * @author wangdk
     * @return void
     * @since  1.0.0
    */
    @Deprecated
    protected void doRecordLoanProcessHistory(BasicServiceDataBean basicServiceDataBean) throws ServiceException {
		LoanProcessModelConverter<PubLoanprocess> loanProcessModelConverter = new LoanProcessModelConverter<PubLoanprocess>();
		PubLoanprocess loanprocess = loanProcessModelConverter.convert(basicServiceDataBean);
		// TODO FHJ 记录贷款处理(审批)日志
		// TODO FHJ 查询SQL
		pubLoanprocessDao.insert(loanprocess);
	}

    
    /**
     * 结束流程，并且更新咨询的状态
     * @param basicServiceDataBean
     * @param status
     * @throws Exception
     */
    private void finishProcessWithState(BasicServiceDataBean basicServiceDataBean, String status) throws Exception {
        PlConsult creditconsult = plConsultService.getItemInfoById(basicServiceDataBean.getConsultId());
        creditconsult.setStatus(status);
        plConsultService.update(creditconsult);
    }
}
