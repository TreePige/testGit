package com.rongdu.eloan.modules.common.utils.saver;

/**
 * 业务表单草稿保存器
 * @author FHJ
 *
 */
public interface ServiceFormDraftSaver {
	/**
	 * 保存业务表单草稿
     * @param serviceVariables 业务数据
     * @param loginInfo 登录信息
	 * @throws Exception
	 */
	void saveDraft(String serviceVariables, LoginContext loginInfo) throws Exception;
}
