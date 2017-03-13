package com.rongdu.eloan.modules.system.dao;

import java.util.List;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.system.domain.SysRoleMenu;

/**
 * 
 * 角色菜单关系DAO
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:48:38
 */
@RDBatisDao
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu,Long> {
	
	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年9月22日
	 */
	void deleteByRoleId(long roleId);
	
	/**
	 * 角色菜单关联信息查询 LIST
	 * @param roleId  角色ID
	 * @return 角色菜单关系列表
	 * @version 1.0
	 * @author 吴国成
	 * @throws PersistentDataException 
	 * @created 2014年9月22日
	 */
	List<SysRoleMenu> getRoleMenuList(long roleId) throws PersistentDataException;
	
	void addRoleMenu(long roleId,Long menuId);
	
	
	List<String>  getRoleMenuHrefList(Long roleId)throws Exception;
}
