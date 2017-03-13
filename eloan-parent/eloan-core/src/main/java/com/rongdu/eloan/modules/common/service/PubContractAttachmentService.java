package com.rongdu.eloan.modules.common.service;

import java.util.Map;
import java.util.List;
import  com.rongdu.eloan.modules.common.domain.PubContractAttachment;

/**
* User:    wulb
* DateTime:2016-08-29 10:50:06
* details: 合同附件信息,Service接口层
* source:  代码生成器
*/
public interface PubContractAttachmentService {

    /**
     * 合同附件信息表,插入数据
     * @param pubContractAttachment 合同附件信息类
     * @return           返回页面map
     * @throws Exception
     */
    public long insert(PubContractAttachment pubContractAttachment) throws Exception;

    /**
     * 合同附件信息表,修改数据
     * @param pubContractAttachment 合同附件信息类
     * @return           返回页面map
     * @throws Exception
     */
    public long update(PubContractAttachment pubContractAttachment) throws Exception;

	/**
     * 合同附件信息表,分页查询数据
     * @param data
     * @return
     * @throws Exception
     */
    public List<PubContractAttachment> getPageListByMap(Map<String , Object> data) throws Exception;
    
	/**
     * 合同附件信息表,根据id查询数据
     * @param id
     * @return
     * @throws Exception
     */
    public PubContractAttachment getItemInfoById(long id) throws Exception;
    
	/**
     * 合同附件信息表,根据流程id查询数据
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public PubContractAttachment getItemInfoByProcessInstanceId(String processInstanceId) throws Exception;

    /**
    * 合同附件信息表,删除数据
    * @param id 主键
    * @return   返回页面map
    * @throws Exception
    */
    public int deleteById(long id) throws Exception;
}
