package com.rongdu.eloan.modules.common.dao;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.domain.PlConsult;

/**
* User:    wulb
* DateTime:2016-08-08 01:01:45
* details: 咨询信息,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface PlConsultDao extends BaseDao<PlConsult,Long> {

    /**
     * 咨询信息表,分页查询数据
     * @param map
     * @return List<PlConsult>
     */
    public List<PlConsult> getPageListByMap(Map<String, Object> map);
    
    /**
     * 咨询信息表,根据id查询数据
     * @param id
     * @return PlConsult
     */
    public PlConsult getItemInfoById(long id);
    
    /**
     * 咨询信息表,根据流程id查询数据
     * @param processInstanceId
     * @return PlConsult
     */
    public PlConsult getItemInfoByProcessInstanceId(String processInstanceId);

    /**
    * 咨询信息表,删除数据
    * @param id 主键
    * @return
    */
    public int deleteById(long id);

    /**
     * 咨询信息表,根据projectId查询数据
     * @param projectId
     * @return PlConsult
     */
	public PlConsult getPlConsultByProjectId(long projectId);
}
