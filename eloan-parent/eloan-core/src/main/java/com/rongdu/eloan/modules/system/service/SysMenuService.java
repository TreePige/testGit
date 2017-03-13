package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysMenu;
import com.rongdu.eloan.modules.system.model.MenuModel;
import com.rongdu.eloan.modules.system.model.TreeSysMenu;

/**
 * 
 * 菜单服务类
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:54:35
 */
public interface SysMenuService {

	/**
	 * 根据角色ids查询菜单model list信息
	 * 
	 * @param roleIds
	 *            角色ids
	 * @return 菜单model list
	 * @throws ServiceException 
	 * @throws Exception 
	 */
	List<MenuModel> getMenuListByRoleIds(List<Long> roleIds) throws ServiceException;

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 *            菜单对象
	 * @return 返回菜单对象
	 * @throws ServiceException 
	 */
	SysMenu menuAdd(SysMenu menu) throws ServiceException;

	/**
	 * 跟新菜单
	 * 
	 * @param menu
	 *            需更新的对象
	 * @return 更新后对象
	 * @throws ServiceException 
	 */
	SysMenu menuUpdate(SysMenu menu) throws ServiceException;

	/**
	 * 查询菜单
	 * 
	 * @param id
	 *            对象id
	 * @return 返回菜单对象
	 * @throws ServiceException 
	 */
	SysMenu menuFind(long id) throws ServiceException;

	/**
	 * 删除菜单：理论删除
	 * 
	 * @param menu
	 *            删除对象
	 */
	void menuDelete(SysMenu menu);

	/**
	 * 获取使用中的菜单栏
	 * 
	 * @return 菜单集合
	 * @throws ServiceException 
	 */
	List<SysMenu> menuUseList() throws ServiceException;

	/**
	 * 删除菜单，删除时，要删除当前结构下的子菜单 当前考虑三级
	 * 
	 * @param id
	 *            主键
	 * @throws ServiceException 
	 */
	void menuDelete(long id) throws ServiceException;

	/**
	 * 获取使用中的菜单栏
	 * 
	 * @param operatorId
	 *            用户ID
	 * @param isMenu
	 *            是否菜单
	 * @return 菜单
	 */
	List<SysMenu> menuUseList(long operatorId, byte isMenu);

	/**
	 * 获取用户是否有此url的权限
	 * 
	 * @param userName
	 *            用户名
	 * @param href
	 *            菜单路径
	 * @return 是否拥有此菜单的权限
	 * @throws ServiceException 
	 */
	boolean getMenuPermission(String userName, String href) throws ServiceException;

	/**
	 * 根据href查询菜单信息
	 * 
	 * @param href
	 * @return
	 */
	SysMenu getMenuByHref(String href);

	/**
	 * 更新menu
	 * 
	 * @param menuMap
	 */

	int updateMenu(Map<String, Object> menuMap);

	List<? extends SysMenu> getMenuPanelByParentId(String useName,
			String parentId, int code,List<Long> roles) throws ServiceException;

	List<TreeSysMenu> getMenuByTreeParentId(String useName, String parentId);

	int addMenu(Map<String, Object> menuMap);

	List<Map<String, Object>> getRoleIdMenuList(int roleId);
	
	
	boolean saveOrUpdate(String roleId,String ids) throws ServiceException;
	
	/**
	 * 获得满足条件的菜单列表
	 * @param param
	 * @return 
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年12月19日
	 */
	List<SysMenu> getMenuList(Map<String, Object> param);


	List<Map<String, Object>> fetchRoleMenus(String sysType,Long... roleids) throws ServiceException;

	void saveOrUpdateMenuss(long roleId,List<Long> list) throws ServiceException;

	Map getMenuInfoById(Long id) throws ServiceException;
	
	List<Map<String, Object>> getAllMenuList(Map<String, Object> map) throws ServiceException;
	
	/**
	 * 
	 * @description 角色分配权限菜单页的查询
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	List<Map<String, Object>> fetchRoleMenuHas(Long roleId) throws ServiceException;
	
	List<Map<String, Object>> fetchAllMenu() throws ServiceException;

	/**
	 * 查询菜单下,可用的按钮列表
	 * @param scriptId
	 * @return
	 */
	List<String> findValidMenuListByScriptId(int parentId, int roleId);
}
