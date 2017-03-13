package com.rongdu.eloan.modules.system.dao;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.system.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 
 * 角色DAO
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:51:25
 */
@RDBatisDao
public interface SysRoleDao extends BaseDao<SysRole,Long> {
	List<? extends SysRole> getRolePageList(Map<String, Object> data);

	List<SysRole> getRoleListByUserId(Long userId);

	List<SysRole> getListByMap(Map<String, Object> param);

	int deleteById(long id);

	int updateByMap(Map<String, Object> arg);
	
	List<Map<String, Object>> getByUserPassRolesList(Map<String, Object> data);

	int getRolecount(Map<String, Object> mapdata);

	long insertByMap(Map<String, Object> data);

}
