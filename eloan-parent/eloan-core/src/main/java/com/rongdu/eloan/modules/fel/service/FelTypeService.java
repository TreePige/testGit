package com.rongdu.eloan.modules.fel.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.fel.domain.FelType;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 03:28:56 details:
 * 公式配置,类型模块,Service接口层 source: 代码生成器
 */
public interface FelTypeService {

	/**
	 * 公式配置,类型模块表,插入数据
	 * @param feltype
	 * @return
	 * @throws Exception
	 */
	public long Insert(FelType feltype) throws Exception;

	/**
	 * 公式配置,类型模块表,修改数据
	 * @param feltype
	 * @return
	 * @throws Exception
	 */
	public long Update(FelType feltype) throws Exception;

    /**
     * 查询数据
     * @param map
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public List<FelType> getPageListByMap(Map<String, Object> map) throws Exception;
    
    /**
     * 根据条件查询数据条数
     * @param map
     * @return
     * @throws Exception
     */
    public int getPageCountByMap(Map<String, Object> map) throws Exception;

	/**
	 * 公式配置,类型模块表,下拉数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public List<Map<String, Object>> getListByMap(Map<String, Object> map) throws Exception;
}
