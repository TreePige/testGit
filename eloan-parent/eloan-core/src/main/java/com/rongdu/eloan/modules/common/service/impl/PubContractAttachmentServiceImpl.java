package com.rongdu.eloan.modules.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.common.dao.PubContractAttachmentDao;
import com.rongdu.eloan.modules.common.domain.PubContractAttachment;
import com.rongdu.eloan.modules.common.service.PubContractAttachmentService;

/**
* User:    wulb
* DateTime:2016-08-29 10:50:06
* details: 合同附件信息,Service实现层
* source:  代码生成器
*/
@SuppressWarnings("rawtypes")
@Service(value = "pubContractAttachmentServiceImpl")
public class PubContractAttachmentServiceImpl extends BaseServiceImpl implements PubContractAttachmentService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(PubContractAttachmentServiceImpl.class);
    /**
	 * 合同附件信息dao层
	 */
    @Autowired
    private PubContractAttachmentDao pubContractAttachmentDao;

	/**
	 * 合同附件信息表,插入数据
	 * @param collateral 合同附件信息类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public long insert(PubContractAttachment pubContractAttachment) throws Exception {
		try {
			return pubContractAttachmentDao.insert(pubContractAttachment);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 合同附件信息表,修改数据
	* @param collateral 合同附件信息类
	* @return           返回页面map
	* @throws Exception
	*/
	@Override
	public long update(PubContractAttachment pubContractAttachment) throws Exception {
		try {
			return pubContractAttachmentDao.update(pubContractAttachment);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 合同附件信息表,分页查询数据
	 * @param data
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public List<PubContractAttachment> getPageListByMap(Map<String , Object> data) throws Exception {
		try {
			return pubContractAttachmentDao.getPageListByMap(data);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 合同附件信息表,根据id查询数据
	 * @param id
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public PubContractAttachment getItemInfoById(long id) throws Exception {
		try {
			return pubContractAttachmentDao.getItemInfoById(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 合同附件信息表,根据流程id查询数据
	 * @param processInstanceId
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public PubContractAttachment getItemInfoByProcessInstanceId(String processInstanceId) throws Exception {
		try {
			return pubContractAttachmentDao.getItemInfoByProcessInstanceId(processInstanceId);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	* 合同附件信息表,删除数据
	* @param id 主键
	* @return   返回页面map
	* @throws Exception
	*/
	@Override
	public int deleteById(long id) throws Exception {
		try {
			return pubContractAttachmentDao.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public BaseDao getMapper() {
		return pubContractAttachmentDao;
	}
}