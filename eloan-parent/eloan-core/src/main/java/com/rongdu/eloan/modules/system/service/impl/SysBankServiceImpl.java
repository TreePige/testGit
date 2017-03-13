package com.rongdu.eloan.modules.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysBankDao;
import com.rongdu.eloan.modules.system.domain.SysBank;
import com.rongdu.eloan.modules.system.service.SysBankService;

/**
 * 公司银行账号表Service
 * @author wgc
 * @version 1.0
 * @since 2015-01-22
 */
@SuppressWarnings("rawtypes")
@Service(value = "sysBankService")
public class SysBankServiceImpl extends BaseServiceImpl implements SysBankService {

	@Autowired
	private SysBankDao sysBankDao;
	
	@Override
	public long insert(SysBank sysBank) throws Exception {
		try {
			return sysBankDao.insert(sysBank);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public long update(SysBank sysBank) throws ServiceException {
		try {
			return sysBankDao.update(sysBank);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public List<SysBank> getPageListByMap(Map<String, Object> map) throws Exception {
		try {
			return sysBankDao.getPageListByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int getPageCountByMap(Map<String, Object> data) throws Exception {
		try {
			return sysBankDao.getPageCountByMap(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int deleteById(long id) throws Exception {
		try {
			return sysBankDao.deleteById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public BaseDao getMapper() {
		return sysBankDao;
	}
}