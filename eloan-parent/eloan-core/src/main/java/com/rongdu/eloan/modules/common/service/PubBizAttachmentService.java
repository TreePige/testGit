package com.rongdu.eloan.modules.common.service;

import java.io.File;
import java.util.Map;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import  com.rongdu.eloan.modules.common.domain.PubBizAttachment;
import com.rongdu.eloan.modules.common.exception.ServiceException;

/**
* User:    lyz
* DateTime:2016-07-18 02:58:27
* details: 业务附件表,Service接口层
* source:  代码生成器
*/
public interface PubBizAttachmentService {

    /**
     * 业务附件表表,插入数据
     * @param pubBizAttachment 业务附件表类
     * @return           返回页面map
     * @throws Exception
     */
    public long insert(PubBizAttachment pubBizAttachment) throws Exception;

    /**
     * 业务附件表表,修改数据
     * @param pubBizAttachment 业务附件表类
     * @return           返回页面map
     * @throws Exception
     */
    public long update(PubBizAttachment pubBizAttachment) throws Exception;

	/**
     * 业务附件表表,分页查询数据
     * @param data
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public List<PubBizAttachment> getPageListByMap(Map<String , Object> data, PageBounds pageBounds) throws Exception;

    /**
     * 业务附件表表,查询数据记录数
     * @param data
     * @return
     * @throws Exception
     */
    public int getPageCountByMap(Map<String, Object> data) throws Exception;

    /**
    * 业务附件表表,删除数据
    * @param id 主键
    * @return   返回页面map
    * @throws Exception
    */
    public int deleteById(long id) throws Exception;
    
    /**
     * 
     * @description
     * @param map
     * @return
     * @throws ServiceException
     * @author liyc
     * @return List<PubBizAttachment>
     * @since  1.0.0
     */
    public List<PubBizAttachment> queryAll(Map<String, Object> map)throws Exception;

	/**
	 * @description
	 * @param idList
	 * @param file
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	*/
	public void deletes(List<Long> idList, File file) throws Exception;
}
