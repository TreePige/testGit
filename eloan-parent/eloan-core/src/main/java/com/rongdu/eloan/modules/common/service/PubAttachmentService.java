package com.rongdu.eloan.modules.common.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import com.rongdu.eloan.modules.common.exception.ServiceException;

/**
* User:    lyc
* DateTime:2016-07-12 01:50:12
* details: 附件,Service接口层
* source:  代码生成器
*/
public interface PubAttachmentService {

    /**
     * 
     * @description 插入附件返回id
     * @param pubAttachment
     * @return
     * @throws ServiceException
     * @author liyc
     * @return long
     * @since  1.0.0
     */
    public long insert(PubAttachment pubAttachment)throws ServiceException;

    /**
     * 
     * @description 更新
     * @param pubAttachment
     * @return
     * @throws ServiceException
     * @author liyc
     * @return int
     * @since  1.0.0
     */
    public int update(PubAttachment pubAttachment)throws ServiceException;


    /**
     * 
     * @description 查询带分页
     * @param map
     * @param pageBounds
     * @return
     * @throws ServiceException
     * @author liyc
     * @return Map<String,Object>
     * @since  1.0.0
     */
//    public Map<String, Object> select(Map<String, Object> map, PageBounds pageBounds)throws ServiceException;
    
    /**
     * 
     * @description 查询所有
     * @param map
     * @return
     * @throws ServiceException
     * @author liyc
     * @return Map<String,Object>
     * @since  1.0.0
     */
    public List<PubAttachment> select(Map<String, Object> map)throws ServiceException;
    
    /**
    * 附件表,删除数据
    * @param id 主键
    * @return   返回页面map
    * @throws ServiceException
    */
    public int delete (Integer id)throws ServiceException;
    
    /**
     * 
     * @description 删除多个附件 
     * @param ids
     * @param webRoot
     * @return
     * @throws ServiceException
     * @author liyc
     * @return int
     * @since  1.0.0
     */
    void deletes(List<Long> ids,File webRoot) throws ServiceException;
}
