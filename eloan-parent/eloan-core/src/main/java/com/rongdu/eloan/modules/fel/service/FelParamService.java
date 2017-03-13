package com.rongdu.eloan.modules.fel.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import  com.rongdu.eloan.modules.fel.domain.FelParam;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 02:14:50
* details: 公式配置-------参数模块,Service接口层
* source:  代码生成器
*/
public interface FelParamService {

    /**
     * 公式配置-------参数模块表,插入数据
     * @param felparam
     * @return
     * @throws Exception
     */
    public long Insert(FelParam felparam) throws Exception;

    /**
     * 公式配置-------参数模块表,修改数据
     * @param felparam 公式配置-------参数模块类
     * @return           返回页面map
     * @throws ServiceException
     */
    public long Update(FelParam felparam) throws Exception;
    
    /**
     * 查询数据
     * @param map
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public List<FelParam> getPageListByMap(Map<String, Object> map) throws Exception;
    
    /**
     * 根据条件查询数据条数
     * @param map
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public int getPageCountByMap(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @description 子参数,公式查询
     * @return
     * @throws Exception
     * @author 孙凯伦
     * @return Map<String,Object>
     * @since  1.0.0
     */
	public List<Map<String, Object>> formulaQuery() throws Exception;
	/**
	 * 
	 * @description 条件查询
	 * @return
	 * @throws ServiceException
	 * @author 孙凯伦
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> conditionsQuery(FelParam felparam) throws ServiceException;
	
	
}
