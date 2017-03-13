package com.rongdu.eloan.modules.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.common.dao.PubProfileDao;
import com.rongdu.eloan.modules.common.domain.PubProfile;
import com.rongdu.eloan.modules.common.service.PubProfileService;

/**
 * 
 *	@Description 附件类型管理,Service实现层
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月15日 下午1:38:46
 *  @since version 1.0.0
 */
@SuppressWarnings("rawtypes")
@Service(value = "pubProfileServiceImpl")
public class PubProfileServiceImpl extends BaseServiceImpl implements PubProfileService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(PubProfileServiceImpl.class);
    /**
	 * 附件类型管理dao层
	 */
    @Autowired
    private PubProfileDao pubProfileDao;

	/**
	 * 附件类型管理表,插入数据
	 * @param collateral 附件类型管理类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public long insert(PubProfile pubProfile) throws Exception {
		try {
			return pubProfileDao.insert(pubProfile);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 附件类型管理表,修改数据
	* @param collateral 附件类型管理类
	* @return           返回页面map
	* @throws Exception
	*/
	@Override
	public long update(PubProfile pubProfile) throws Exception {
		try {
			return pubProfileDao.update(pubProfile);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 附件类型管理表,分页查询数据
	 * @param pubProfile 附件类型管理类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public List<PubProfile> getPageListByMap(Map<String , Object> data, PageBounds pageBounds) throws Exception {
		try {
			return pubProfileDao.getPageListByMap(data,pageBounds);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 附件类型管理表,查询数据记录数
	 * @param pubProfile 附件类型管理类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public int getPageCountByMap(Map<String, Object> data) throws Exception {
		try {
			return pubProfileDao.getPageCountByMap(data);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	* 附件类型管理表,删除数据
	* @param id 主键
	* @return   返回页面map
	* @throws Exception
	*/
	@Override
	public int deleteById(long id) throws Exception {
		try {
			return pubProfileDao.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public BaseDao getMapper() {
		return pubProfileDao;
	}

	@Override
	public List<Map<String, Object>> fetchAllAttach() throws Exception {
		return pubProfileDao.fetchAllAttach();
	}

	@Override
	public PubProfile getProfileById(Long id) throws Exception {
		return pubProfileDao.getByPrimary(id);
	}

	@Override
	public void saveOrUpdate(PubProfile profile) throws Exception {
		if (null == profile.getId()) {
			pubProfileDao.insert(profile);
		} else {
			pubProfileDao.update(profile);
		}
	}

	@Override
	public List<Map<String, Object>> queryAttachTreeByTaskId(Long taskId)
			throws Exception {
		return pubProfileDao.queryAttachTreeByTaskId(taskId);
	}

	@Override
	public List<Map<String, Object>> queryAttachTreeByProjectId(Long taskId) throws Exception {
		return pubProfileDao.queryAttachTreeByProjectId(taskId);
	}

	@Override
	public List<Map<String, Object>> queryAttachTreeByConsultId(Long taskId)
			throws Exception {
		return pubProfileDao.queryAttachTreeByConsultId(taskId);
	}
}