package com.rongdu.eloan.modules.common.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;

/**
 * 
 *	@Description 附件Dao
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月12日 下午2:03:15
 *  @since version 1.0.0
 */
@RDBatisDao
public interface PubAttachmentDao extends BaseDao<PubAttachment,Long> {


    /**
     * 附件表,查询数据
     * @param pubAttachment
     * @return List<PubAttachment>
     * @throws PersistentDataException
     */
    public List<PubAttachment> select(Map<String, Object> map,PageBounds pageBounds);

    /**
     * 附件表,总数
     * @param pubAttachment
     * @return Integer
     * @throws PersistentDataException
     */
    public Integer total(Map<String, Object> map);
}
