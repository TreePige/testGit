package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.common.domain.PageBean;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysDict;

/**
 * 
 * 数据字典Service
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午10:03:05
 */
public interface SysDictService {
	/**
	 * 查询数据字典
	 * @param typeArr 字典组名
	 * @return 数据字典
	 */
	List<SysDict> getDictByTypeArr(String... typeArr);
	
	/**
	 * 获取数据字典结合
	 * @param map 查询参数
	 * @return 数据字典信息
	 * @throws ServiceException 
	 *//*
	List<SysDict> getDictList(Map<String , Object> map) throws ServiceException;*/
	
	Long getDictCount(Map<String, Object> arg) throws ServiceException;

	//List<Map<String, Object>> getDictDetailList(Map<String, Object> data) throws ServiceException;

	long getDictDetailCount(Map<String, Object> data)throws ServiceException;

	boolean addOrModify(SysDict sysDict, String status) throws ServiceException;

	boolean deleteDict(Long id) throws ServiceException;

	PageBean getDictListAndCount(Map<String, Object> searchMap,int currentPage, int pageSize) throws ServiceException;

	PageBean getDictDetailList(Map<String, Object> data, int currentPage,int pageSize) throws ServiceException;

	List<Map<String, Object>> getDictsCache(String type) throws ServiceException;
}
