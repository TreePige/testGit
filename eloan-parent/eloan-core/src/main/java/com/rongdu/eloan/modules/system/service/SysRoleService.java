package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;



//import com.rongdu.eloan.common.domain.query.Pagination;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;

/**
 * 
 * 系统角色服务类
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:55:16
 */
public interface SysRoleService {

	/**
	 * 查询查询用户所拥有的角色
	 * @param userId
	 *            用户ID
	 * @return 角色List
	 */
	List<SysRole> getRoleListByUserId(long userId) throws ServiceException;

	/**
	 * 查询角色
	 * @param id
	 *            主键ID
	 * @return 角色
	 */
	SysRole getRoleById(long id)throws ServiceException ;

	/**
	 * 角色查询
	 * @return 角色List
	 */
	List<SysRole> getListByMap(Map<String, Object> arg)throws ServiceException;

	/**
	 * 角色删除
	 * @param id
	 *            主键ID
	 */
	int deleteRole(long id)throws ServiceException;
	
	int getRolecount(Map<String, Object> mapdata)throws ServiceException;

	List<? extends SysRole> getRolePageList(Map<String, Object> mapdata) throws ServiceException;

	long addRole(SysRole role)throws ServiceException;
	
	long insertByMap(Map<String, Object> data) throws Exception;
	
	int  updateRole(Map<String, Object> arg) throws ServiceException;
	
	List<Map<String,Object>> getByUserPassRolesList(String username,String password) throws ServiceException;
}
