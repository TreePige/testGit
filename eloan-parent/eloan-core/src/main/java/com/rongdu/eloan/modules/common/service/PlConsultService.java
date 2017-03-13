package com.rongdu.eloan.modules.common.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.common.domain.PlConsult;

/**
* User:    wulb
* DateTime:2016-08-08 01:01:45
* details: 咨询信息,Service接口层
* source:  代码生成器
*/
public interface PlConsultService {
	
    /**
     * 咨询信息表,插入数据
     * @param PlConsult 咨询信息类
     * @return           返回页面map
     * @throws Exception
     */
    public long insert(PlConsult plConsult) throws Exception;

    /**
     * 咨询信息表,修改数据
     * @param PlConsult 咨询信息类
     * @return           返回页面map
     * @throws Exception
     */
    public long update(PlConsult plConsult) throws Exception;

	/**
     * 咨询信息表,分页查询数据
     * @param data
     * @return
     * @throws Exception
     */
    public List<PlConsult> getPageListByMap(Map<String , Object> data) throws Exception;
    
    /**
     * 咨询信息表,根据projectId查询数据
     * @param projectId
     * @return
     * @throws Exception
     */
    public PlConsult getPlConsultByProjectId(long projectId) throws Exception;
    
	/**
     * 咨询信息表,根据id查询数据
     * @param id
     * @return
     * @throws Exception
     */
    public PlConsult getItemInfoById(long id) throws Exception;
    
	/**
     * 咨询信息表,根据流程id查询数据
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public PlConsult getItemInfoByProcessInstanceId(String processInstanceId) throws Exception;

    /**
    * 咨询信息表,删除数据
    * @param id 主键
    * @return   返回页面map
    * @throws Exception
    */
    public int deleteById(long id) throws Exception;
}
