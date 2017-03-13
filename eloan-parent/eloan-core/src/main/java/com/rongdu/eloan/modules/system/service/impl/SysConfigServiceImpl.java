package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.system.dao.SysConfigDao;
import com.rongdu.eloan.modules.system.domain.SysConfig;
import com.rongdu.eloan.modules.system.service.SysConfigService;

/**
* User:    mcwang
* DateTime:2016-08-04 03:26:22
* details: 系统参数表,Service实现层
* source:  代码生成器
*/
@Service(value = "sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);
    /**
	 * 系统参数表dao层
	 */
    @Autowired
    private SysConfigDao sysConfigDao;
	
	/**
	*
	*继承关系
	*/
	@Override
	public BaseDao getMapper() {
		// TODO Auto-generated method stub
		return sysConfigDao;
	}
	
	/**
	 * 系统参数表表,插入数据
	 * @param collateral 系统参数表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public long insertSysConfig(SysConfig sysConfig) throws Exception {
		try {
			return	sysConfigDao.insert(sysConfig);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 系统参数表表,修改数据
	* @param collateral 系统参数表类
	* @return           返回页面map
	* @throws ServiceException
	*/
	public long updateSysConfig(SysConfig sysConfig) throws Exception {
		try {
			return	sysConfigDao.update(sysConfig);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	/**
	 * 系统参数表表,查询数据
	 * @param sysConfig 系统参数表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public List<SysConfig> getSysConfigList(Map<String, Object> map,PageBounds pageBounds) throws Exception {
	
		try {
		
			return	sysConfigDao.select(map,pageBounds);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
		//返回已经查询完毕的参数
	}
	
	/**
	 * 系统参数表表,查询数据
	 * @param map类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			return sysConfigDao.total(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
}