package com.rongdu.eloan.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.system.domain.SysDict;

/**
 * 
 * 数据字典DAO接口
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:44:54
 */
@RDBatisDao
public interface SysDictDao extends BaseDao<SysDict,Long> {

	/**
	 * 取得数据字典类型列表
	 * 
	 * @return 数据字典类型列表
	 */
	List<String> getTypeList();
	
	/**
	 * 查询数据字典
	 * @param typeArr 字典组名
	 * @return 数据字典
	 */
	List<SysDict> getDictByTypeArr(Map<String, Object> map);
	
	
	
	Long saveOrUpdate(Map<String, Object> arg,String status) ;
	
	
	List<Map<String, Object>> getDictsCache(String typeDict) ;

	List<SysDict> getItemListByMap(Map<String, Object> map);

	Long getCount(Map<String, Object> arg);

	long deleteById(Long id);

	
	
	

}
