package com.rongdu.eloan.modules.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.modules.common.dao.PlConsultDao;
import com.rongdu.eloan.modules.common.domain.PlConsult;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.BusinessBaseService;
import com.rongdu.eloan.modules.common.service.PlConsultService;
import com.rongdu.eloan.modules.common.utils.databean.ProjectWorkflowDataBean;

/**
* User:    wulb
* DateTime:2016-08-08 01:01:45
* details: 咨询信息,Service实现层
* source:  代码生成器
*/
@Service(value = "plConsultServiceImpl")
public class PlConsultServiceImpl extends BusinessBaseService implements PlConsultService {
    private static final Logger logger = LoggerFactory.getLogger(PlConsultServiceImpl.class);
    @Autowired
    private PlConsultDao plConsultDao;
	
	/**
	 * 咨询信息表,插入数据
	 * @param collateral 咨询信息类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public long insert(PlConsult plConsult) throws Exception {
		try {
			return plConsultDao.insert(plConsult);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 咨询信息表,修改数据
	* @param collateral 咨询信息类
	* @return           返回页面map
	* @throws Exception
	*/
	@Override
	public long update(PlConsult plConsult) throws Exception {
		try {
			return plConsultDao.update(plConsult);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 咨询信息表,分页查询数据
	 * @param data
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public List<PlConsult> getPageListByMap(Map<String , Object> data) throws Exception {
		try {
			return plConsultDao.getPageListByMap(data);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 咨询信息表,根据id查询数据
	 * @param id
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public PlConsult getItemInfoById(long id) throws Exception {
		try {
			return plConsultDao.getItemInfoById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 咨询信息表,根据projectId查询数据
	 * @param projectId
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public PlConsult getPlConsultByProjectId(long projectId) throws Exception {
		try {
			return plConsultDao.getPlConsultByProjectId(projectId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 咨询信息表,根据流程id查询数据
	 * @param processInstanceId
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public PlConsult getItemInfoByProcessInstanceId(String processInstanceId) throws Exception {
		try {
			return plConsultDao.getItemInfoByProcessInstanceId(processInstanceId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	* 咨询信息表,删除数据
	* @param id 主键
	* @return   返回页面map
	* @throws Exception
	*/
	@Override
	public int deleteById(long id) throws Exception {
		try {
			return plConsultDao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	protected void preCheckCurrentWorkflowState(ProjectWorkflowDataBean projectWorkflowDataBean)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void preCheckWorkflowParams(ProjectWorkflowDataBean projectWorkflowDataBean) throws ServiceException {}
}