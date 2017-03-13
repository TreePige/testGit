package com.rongdu.eloan.modules.common.dao;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.domain.PlFeeinfo;

/**
* User:    wulb
* DateTime:2016-08-18 17:01:44
* details: 费用信息,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface PlFeeinfoDao extends BaseDao<PlFeeinfo,Long> {

    /**
     * 费用信息表,分页查询数据
     * @param map
     * @return List<PlFeeinfo>
     */
    public List<PlFeeinfo> getPageListByMap(Map<String, Object> map);
    
    /**
     * 费用信息表,根据id查询数据
     * @param id
     * @return PlFeeinfo
     */
    public PlFeeinfo getItemInfoById(long id);
    
    /**
     * 费用信息表,根据流程id查询数据
     * @param processInstanceId
     * @return PlFeeinfo
     */
    public PlFeeinfo getItemInfoByProcessInstanceId(String processInstanceId);

    /**
    * 费用信息表,删除数据
    * @param id 主键
    * @return
    */
    public int deleteById(long id);

	public Map<String, Object> getItemMapByProcInsId(String procInsId);
}
