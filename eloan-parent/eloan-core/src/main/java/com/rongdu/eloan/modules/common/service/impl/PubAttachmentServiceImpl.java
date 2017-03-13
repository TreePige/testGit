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
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.modules.common.dao.PubAttachmentDao;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.PubAttachmentService;

/**
 * 
 *	@Description pubAttachmentService
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月12日 下午7:17:08
 *  @since version 1.0.0
 */
@Service(value = "pubAttachmentService")
public class PubAttachmentServiceImpl extends BaseServiceImpl implements PubAttachmentService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(PubAttachmentServiceImpl.class);
    /**
	 * 附件dao层
	 */
    @Autowired
    private PubAttachmentDao pubAttachmentDao;

	/**
	 * 附件表,插入数据
	 * @param collateral 附件类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
    @Transactional(rollbackFor = Exception.class)
	public long insert(PubAttachment pubAttachment) throws ServiceException {
		return pubAttachmentDao.insert(pubAttachment);
	}

	/**
	* 附件表,修改数据
	* @param collateral 附件类
	* @return           返回页面map
	* @throws ServiceException
	*/
    @Transactional(rollbackFor = Exception.class)
	public int update(PubAttachment pubAttachment) throws ServiceException {
		return pubAttachmentDao.update(pubAttachment);
	}
	/**
	 * 附件表,查询数据
	 * @param pubAttachment 附件类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public Map<String, Object> select(Map<String, Object> map,PageBounds pageBounds) throws ServiceException {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<PubAttachment> List = pubAttachmentDao.select(map,pageBounds);
			Integer Total = pubAttachmentDao.total(map);
			//返回给页面的参数
			res.put(Constant.RESPONSE_DATA, List);				            //后台查询出的list值
			res.put(Constant.RESPONSE_DATA_TOTALCOUNT, Total);		            //这些数据的总页数
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		//返回已经查询完毕的参数
		return res;
	}
	
	/**
	* 附件表,删除数据
	* @param id 主键
	* @return   返回页面map
	* @throws ServiceException
	*/
	@Transactional(rollbackFor = Exception.class)
	public int delete (Integer id) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return pubAttachmentDao.delete(param);
	}

	@Override
	public BaseDao getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deletes(List<Long> ids, File webRoot) throws ServiceException {
		for (Long id : ids) {
			PubAttachment file = pubAttachmentDao.getByPrimary(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			pubAttachmentDao.delete(param);
			String path= file.getFilePath();
            if(path!=null){
                File f=new File(webRoot,path.toString());
                if(f.exists())f.delete();
            }
		}
	}

	@Override
	public List<PubAttachment> select(Map<String, Object> map)
			throws ServiceException {
		return pubAttachmentDao.select(map);
	}
}