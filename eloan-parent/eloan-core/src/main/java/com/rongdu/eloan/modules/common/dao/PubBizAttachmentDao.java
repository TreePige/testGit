package com.rongdu.eloan.modules.common.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.domain.PubBizAttachment;

/**
* User:    lyz
* DateTime:2016-07-18 02:58:27
* details: 业务附件表,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface PubBizAttachmentDao extends BaseDao<PubBizAttachment,Long> {

    /**
     * 业务附件表表,分页查询数据
     * @param pubBizAttachment
     * @return List<PubBizAttachment>
     */
    public List<PubBizAttachment> getPageListByMap(Map<String, Object> map, PageBounds pageBounds);

    /**
     * 业务附件表表,查询数据记录数
     * @param pubBizAttachment
     * @return int
     */
    public int getPageCountByMap(Map<String, Object> data);
    
    /**
    * 业务附件表表,删除数据
    * @param id 主键
    * @return
    */
    public int deleteById(long id);

	/**
	 * @description
	 * @param map
	 * @return
	 * @author liyc
	 * @return List<PubBizAttachment>
	 * @since  1.0.0
	*/
	public List<PubBizAttachment> queryAll(Map<String, Object> map);
}
