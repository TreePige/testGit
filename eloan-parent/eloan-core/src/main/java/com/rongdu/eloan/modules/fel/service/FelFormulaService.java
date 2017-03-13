package com.rongdu.eloan.modules.fel.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.domain.FelFormula;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 03:17:39 details:
 * 公式配置,公式模块,Service接口层 source: 代码生成器
 */
public interface FelFormulaService {

	/**
	 * 公式配置,公式模块表,插入数据
	 * @param felformula
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public long Insert(FelFormula felformula, List<Map<String, Object>> list) throws Exception;

	/**
	 * 公式配置,公式模块表,修改数据
	 * 
	 * @param felformula
	 *            公式配置,公式模块类
	 * @return 返回页面map
	 * @throws ServiceException
	 */
	public long Update(FelFormula felformula, List<Map<String, Object>> list) throws Exception;

	/**
	 * 资产渠道表,查询数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 * @throws Exception
	 */
	public List<FelFormula> getPageListByMap(Map<String, Object> map) throws Exception;

	/**
	 * 根据条件查询数据条数
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 * @throws Exception
	 */
	public int getPageCountByMap(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @description 公式配置,公式模块表,删除数据
	 * @param id
	 * @return
	 * @author 孙凯伦
	 * @return Map<String,Object>
	 * @since 1.0.0
	 */
	public long Delete(Integer id) throws Exception;


	/**
	 * 
	 * @description
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return List<Felformula>
	 * @since 1.0.0
	 */
	 public List<FelFormula> queryByProductId(Long id) throws
	 ServiceException;

}
