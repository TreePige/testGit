package com.rongdu.eloan.modules.common.service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface ComboDataSourceService {
	
	/**
	 * 根据typeCode查询字典相关信息
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> queryDic(Map<String, Object> data) throws Exception ;
	
	/**
	 * 获取借款需求产品下拉信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllProductSimpleInfos(Map<String, Object> data) throws Exception ;
	
	/**
	 * 根据产品id获取相关信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> queryProductAbout(Map<String, Object> data) throws Exception ;
}
