package com.rongdu.eloan.modules.common.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.dao.PubBizAttachmentDao;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import com.rongdu.eloan.modules.common.domain.PubBizAttachment;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.PubBizAttachmentService;

/**
* User:    lyz
* DateTime:2016-07-18 02:58:27
* details: 业务附件表,Service实现层
* source:  代码生成器
*/
@SuppressWarnings("rawtypes")
@Service(value = "pubBizAttachmentServiceImpl")
public class PubBizAttachmentServiceImpl extends BaseServiceImpl implements PubBizAttachmentService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(PubBizAttachmentServiceImpl.class);
    /**
	 * 业务附件表dao层
	 */
    @Autowired
    private PubBizAttachmentDao pubBizAttachmentDao;

	/**
	 * 业务附件表表,插入数据
	 * @param collateral 业务附件表类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public long insert(PubBizAttachment pubBizAttachment) throws Exception {
		try {
			return pubBizAttachmentDao.insert(pubBizAttachment);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	* 业务附件表表,修改数据
	* @param collateral 业务附件表类
	* @return           返回页面map
	* @throws Exception
	*/
	@Override
	public long update(PubBizAttachment pubBizAttachment) throws Exception {
		try {
			return pubBizAttachmentDao.update(pubBizAttachment);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 业务附件表表,分页查询数据
	 * @param pubBizAttachment 业务附件表类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public List<PubBizAttachment> getPageListByMap(Map<String , Object> data, PageBounds pageBounds) throws Exception {
		try {
			return pubBizAttachmentDao.getPageListByMap(data,pageBounds);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 业务附件表表,查询数据记录数
	 * @param pubBizAttachment 业务附件表类
	 * @return           返回页面map
	 * @throws Exception
	 */
	@Override
	public int getPageCountByMap(Map<String, Object> data) throws Exception {
		try {
			return pubBizAttachmentDao.getPageCountByMap(data);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	* 业务附件表表,删除数据
	* @param id 主键
	* @return   返回页面map
	* @throws Exception
	*/
	@Override
	public int deleteById(long id) throws Exception {
		try {
			return pubBizAttachmentDao.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public BaseDao getMapper() {
		return pubBizAttachmentDao;
	}

	@Override
	public List<PubBizAttachment> queryAll(Map<String, Object> map)
			throws Exception {
		return pubBizAttachmentDao.queryAll(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deletes(List<Long> idList, File webRoot) throws Exception {
		for (Long id : idList) {
			PubBizAttachment file = pubBizAttachmentDao.getByPrimary(id);
			pubBizAttachmentDao.deleteById(id);
			String path= file.getFilePath();
            if(path!=null){
                File f=new File(webRoot,path.toString());
                if(f.exists())f.delete();
            }
		}
	}
}