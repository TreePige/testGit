package com.rongdu.eloan.modules.common.service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.service.BaseService;
import com.rongdu.eloan.common.utils.ParamChecker;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.databean.BasicDataBean;
import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;
import com.rongdu.eloan.modules.common.utils.databean.ProjectWorkflowDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务基础类
 * @author FHJ
 *
 */
@SuppressWarnings("rawtypes")
public abstract class BusinessBaseService implements BaseService{
	private static final Logger logger = LoggerFactory.getLogger(BusinessBaseService.class);
	
	/**
	 * 预先检查业务需要的参数
	 * @param basicServiceDataBean
	 * @throws ServiceException
	 */
	protected void preCheckServiceParams(BasicServiceDataBean basicServiceDataBean) throws ServiceException {
		try {
			if(SystemConstant.MATERIAL_TYPE.equals(basicServiceDataBean.getNextStep())) {
			} else if (SystemConstant.UNPASS_TYPE.equals(basicServiceDataBean.getNextStep())) {
				ParamChecker.checkEmpty(basicServiceDataBean.getRejectionCategoryComment(), SystemConstant.FORM_CN_REJECTION_TYPE);
			}
		} catch (IllegalArgumentException e) {
			throwServiceExceptionAndLog(logger, e.getMessage(), e, Constant.FAIL_CODE_PARAM_INSUFFICIENT);
		}
	}
	
	/**
	 * 预先检查流程相关的参数
	 * @param projectWorkflowDataBean
	 * @throws ServiceException
	 */
    protected void preCheckWorkflowParams(ProjectWorkflowDataBean projectWorkflowDataBean) throws ServiceException {
        logger.info("预告检查与审核流程相关的参数。");
        try {
            ParamChecker.checkEmpty(projectWorkflowDataBean.getProcessInstanceId(), "ProcessInstanceId");
            ParamChecker.checkEmpty(projectWorkflowDataBean.getNextStep(), SystemConstant.FORM_CN_NEXT_STEP);
            ParamChecker.checkEmpty(projectWorkflowDataBean.getProcessStateCode(), "processStateCode");
        } catch (IllegalArgumentException e) {
            throwServiceExceptionAndLog(logger, e.getMessage(), e,
                    Constant.FAIL_CODE_PARAM_INSUFFICIENT);
        }
    }

	/**
	 * 预先检查当前流程状态，具体由实现类去实现，主要可以做防止重复提交等工作
	 * @param projectWorkflowDataBean
	 * @throws ServiceException
	 */
	protected abstract void preCheckCurrentWorkflowState(ProjectWorkflowDataBean projectWorkflowDataBean) throws ServiceException;
	
	/**
	 * 预先检查基本参数，如果当前登录人等
	 * @param basicDataBean
	 * @throws ServiceException
	 */
	protected void preCheckBasicParams(BasicDataBean basicDataBean) throws ServiceException {
		if(basicDataBean == null) {
			// 该问题会比较严重
			throwServiceExceptionAndLog(logger, "系统错误，请联系管理员。");
		}
		Long loginUserId = basicDataBean.getLoginUserId();
		if(loginUserId == null) {
			throwServiceExceptionAndLog(logger, "登录者不能为空");
		}
	}
	
	/**
	 * 抛出ServiceException并且记录log
	 * 
	 * @param logger
	 * @param exceptionMsg
	 * @param cause
	 * @param exceptionCode
	 * @throws ServiceException
	 */
	protected void throwServiceExceptionAndLog(org.slf4j.Logger logger,
			String exceptionMsg, Exception cause, int exceptionCode)
			throws ServiceException {
		logger.error(exceptionMsg + cause.toString());
		throw new ServiceException(exceptionMsg, cause, exceptionCode);
	}
	
	/**
	 * 抛出ServiceException并且记录log
	 * 
	 * @param logger
	 * @param exceptionMsg
	 * @throws ServiceException
	 */
	protected void throwServiceExceptionAndLog(org.slf4j.Logger logger,
			String exceptionMsg) throws ServiceException {
		logger.error(exceptionMsg);
		throw new ServiceException(exceptionMsg);
	}
	
	/**
	 * 抛出ServiceException并且记录log
	 * 
	 * @param logger
	 * @param exceptionMsg
	 * @param exceptionCode
	 * @throws ServiceException
	 */
	protected void throwServiceExceptionAndLog(org.slf4j.Logger logger,
			String exceptionMsg, int exceptionCode) throws ServiceException {
		logger.error(exceptionMsg);
		throw new ServiceException(exceptionCode, exceptionMsg);
	}
	
	/**
	 * 检查插入操作是否成功，如果不成功，抛出ServiceException并且记录log
	 * 
	 * @param insertedId
	 * @param tableName
	 * @throws ServiceException
	 */
	protected void checkIfInsertSuccess(Long insertedId, String tableName)
			throws ServiceException {
		if (insertedId == null || insertedId == 0) {
			throwServiceExceptionAndLog(logger, tableName + " 插入失败。");
		}
	}

	/**
	 * 检查更新操作是否成功，如果不成功，抛出ServiceException并且记录log
	 * 
	 * @param updated
	 * @param tableName
	 * @throws ServiceException
	 */
	protected void checkUpdatedSuccess(Boolean updated, String tableName)
			throws ServiceException {
		if (updated == null || !updated) {
			throwServiceExceptionAndLog(logger, tableName + " 更新失败！");
		}
	}
}
