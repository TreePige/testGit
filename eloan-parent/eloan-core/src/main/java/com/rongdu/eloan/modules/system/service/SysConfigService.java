package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import  com.rongdu.eloan.modules.system.domain.SysConfig;

/**
* User:    mcwang
* DateTime:2016-08-04 03:26:22
* details: 系统参数表,Service接口层
* source:  代码生成器
*/
public interface SysConfigService {

    /**
     * 系统参数表表,插入数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws Exception
     */
    long insertSysConfig(SysConfig sysConfig)throws Exception;

    /**
     * 系统参数表表,修改数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws Exception
     */
  	long updateSysConfig(SysConfig sysConfig)throws Exception;


    /**
     * 系统参数表表,查询数据
     * @param sysConfig 系统参数表类
     * @return           返回页面map
     * @throws Exception
     */
  	 List<SysConfig> getSysConfigList (Map<String, Object> map, PageBounds pageBounds)throws Exception;
    
    
    /**
    * @Description: 获取总记录数 
    * @param @param map
    * @param @return
    * @param @throws Exception    设定文件 
    * @author wangmc
    * @return int    返回类型 
    * @throws
     */
   	 int getTotalCount(Map<String,Object> map)throws Exception;
}
