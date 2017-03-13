package com.rongdu.eloan.modules.system.service;

import java.util.List;

import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRoleMenu;


/**
 * 
 * 角色菜单关联信息service接口
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:55:37
 */
public interface SysRoleMenuService {
	/**
	 * 角色菜单关联信息查询
	 * @param roleId 角色ID
	 * @return 角色List
	 * @throws ServiceException 
	 */
	List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException;
	
	/**
	 * 获取角色url 
	 */
	List<String> getRoleMenuHrefList(Long roleId) throws Exception;
	
}
