package com.rongdu.eloan.modules.common.service;

import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;

public interface HistoryRecordableService {
	void recordLoanProcessHistory(BasicServiceDataBean basicServiceDataBean) throws Exception;
}
