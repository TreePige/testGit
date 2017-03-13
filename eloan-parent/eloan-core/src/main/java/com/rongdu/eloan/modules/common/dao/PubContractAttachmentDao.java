package com.rongdu.eloan.modules.common.dao;

import com.rongdu.eloan.common.dao.BaseDao;
import java.util.List;
import java.util.Map;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.domain.PubContractAttachment;

/**
* User:    wulb
* DateTime:2016-08-29 10:50:06
* details: 合同附件信息,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface PubContractAttachmentDao extends BaseDao<PubContractAttachment,Long> {

    /**
     * 合同附件信息表,分页查询数据
     * @param map
     * @return List<PubContractAttachment>
     */
    public List<PubContractAttachment> getPageListByMap(Map<String, Object> map);
    
    /**
     * 合同附件信息表,根据id查询数据
     * @param id
     * @return PubContractAttachment
     */
    public PubContractAttachment getItemInfoById(long id);
    
    /**
     * 合同附件信息表,根据流程id查询数据
     * @param processInstanceId
     * @return PubContractAttachment
     */
    public PubContractAttachment getItemInfoByProcessInstanceId(String processInstanceId);

    /**
    * 合同附件信息表,删除数据
    * @param id 主键
    * @return
    */
    public int deleteById(long id);
}
