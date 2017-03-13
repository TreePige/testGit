package com.rongdu.eloan.modules.common.service;

import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;

/**
 * 可持久化草稿
 * @author FHJ
 *
 */
public interface DraftPersistable {
	/**
	 * 持久化
	 * @param serviceDataBean
	 * @throws Exception
	 */
	void saveDraft(BasicServiceDataBean serviceDataBean) throws Exception;
}
