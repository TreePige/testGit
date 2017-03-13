package com.rongdu.eloan.modules.system.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.system.domain.SysConfig;

/**
* User:    mcwang
* DateTime:2016-08-04 03:26:22
* details: 系统参数表,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface SysConfigDao extends BaseDao<SysConfig,Long> {


    /**
     * 系统参数表表,查询数据
     * @param map,pageBounds
     * @return List<SysConfig>
     * @throws PersistentDataException
     */
    List<SysConfig> select(Map<String, Object> map,PageBounds pageBounds);

    /**
     * 系统参数表表,总数
     * @param map
     * @return Integer
     * @throws PersistentDataException
     */
    Integer total(Map<String, Object> map);
    
}
