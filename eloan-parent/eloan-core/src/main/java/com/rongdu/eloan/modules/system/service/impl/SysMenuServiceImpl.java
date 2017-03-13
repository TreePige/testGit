package com.rongdu.eloan.modules.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.common.utils.ListUtil;
import com.rongdu.eloan.common.utils.MapUtil;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysMenuDao;
import com.rongdu.eloan.modules.system.dao.SysRoleMenuDao;
import com.rongdu.eloan.modules.system.domain.SysMenu;
import com.rongdu.eloan.modules.system.domain.SysRoleMenu;
import com.rongdu.eloan.modules.system.model.MenuModel;
import com.rongdu.eloan.modules.system.model.TreeSysMenu;
import com.rongdu.eloan.modules.system.service.SysMenuService;

@Service(value = "sysMenuServiceImpl")
public class SysMenuServiceImpl extends BaseServiceImpl implements
		SysMenuService {

	private static final Logger logger = LoggerFactory
			.getLogger(SysMenuServiceImpl.class);
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Transactional(rollbackFor = Exception.class)
	public SysMenu menuAdd(SysMenu menu) throws ServiceException {
		menu.setAddUser(this.getLoginName());
		this.sysMenuDao.insert(menu);
		return menu;
	}

	@Transactional(rollbackFor = Exception.class)
	public SysMenu menuUpdate(SysMenu menu) throws ServiceException {
		menu.setUpdateUser(this.getLoginName());
		this.sysMenuDao.update(menu);
		return menu;
	}

//	public SysMenu menuFind(long id) throws ServiceException {
//		try {
//			return this.sysMenuDao.getItem(id, "SysMenu");
//		} catch (PersistentDataException e) {
//			
//			 logger.error(e.getMessage(), e);
//			throw new ServiceException(Constant.FAIL_CODE_VALUE,
//					"get data fail");
//		}
//	}
//
//	@Override
//	public Map getMenuInfoById(Long id) throws ServiceException {
//		try {
//			return this.sysMenuDao.getMenuInfoById(id);
//		} catch (PersistentDataException e) {
//			throw new ServiceException(Constant.FAIL_CODE_VALUE,
//					"get data fail");
//		}
//	}
//
//	@Transactional
//	public void menuDelete(SysMenu menu) {
//		menu.setUpdateUser(this.getLoginName());
//		this.sysMenuDao.editMenuIsDelete(menu);
//	}
//
//	public List<SysMenu> menuUseList() throws ServiceException {
//		try {
//			return this.sysMenuDao.getItemListByMap(null, "SysMenu");
//		} catch (PersistentDataException e) {
//			throw new ServiceException(Constant.FAIL_CODE_VALUE,
//					"get data fail");
//		}
//	}
//
//	@Transactional(rollbackFor = Exception.class)
//	public void menuDelete(long id) throws ServiceException {
//		SysMenu menu;
//		try {
//			menu = sysMenuDao.getItem(id, "SysMenu");
//			// 查询当前删除节点级别
//			this.menuDelete(menu);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("parentId", menu.getId());
//			map.put("isDelete", 0);
//
//			List<SysMenu> menuList = sysMenuDao
//					.getItemListByMap(map, "SysMenu");
//			if (menuList != null && menuList.size() > 0) {
//				for (SysMenu newMenu : menuList) {
//					menuDelete(newMenu.getId());
//				}
//			}
//
//		} catch (PersistentDataException e) {
//			throw new ServiceException(Constant.FAIL_CODE_VALUE,
//					"delete data fail");
//		}
//	}

	public List<SysMenu> menuUseList(long userId, byte isMenu) {
		return this.sysMenuDao.menuUseList(userId, isMenu);
	}

	public boolean getMenuPermission(String userName, String href)
			throws ServiceException {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("href", href);
		// SysMenu Menu;
		// try {
		// Menu = sysMenuDao.getItemByMap(map, "SysMenu");
		//
		// SysMenu perm = this.sysMenuDao.getMenuPermission(userName, href);
		// if ((Menu == null && perm == null) || (Menu != null && perm != null))
		// {
		// return true;
		// }
		// } catch (PersistentDataException e) {
		// throw new ServiceException(Constant.FAIL_CODE_VALUE,
		// "get data fail");
		// }
		return true;
	}

	public SysMenu getMenuByHref(String href) {
		return this.sysMenuDao.getMenuByHref(href);
	}

	public List<MenuModel> getMenuListByRoleIds(List<Long> roleIds) throws ServiceException {
		return this.sysMenuDao.getMenuListByRoleIds(roleIds);
	}

	public SysMenuDao getSysMenuDao() {
		return sysMenuDao;
	}

	public void setSysMenuDao(SysMenuDao sysMenuDao) {
		this.sysMenuDao = sysMenuDao;
	}

	@Override
	public List<? extends SysMenu> getMenuPanelByParentId(String useName,
			String parentId, int code, List<Long> roles)
			throws ServiceException {

		// this.getRole();

		try {
			return sysMenuDao.getMenuPanelByParentId(useName, parentId, code,
					roles);
		} catch (PersistentDataException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<TreeSysMenu> getMenuByTreeParentId(String useName,
			String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int updateMenu(Map<String, Object> menuMap) {

		return sysMenuDao.updateMenu(menuMap);

	}

	@Override
	public int addMenu(Map<String, Object> menuMap) {

		return sysMenuDao.insertmap(menuMap);

	}

	@Override
	public List<Map<String, Object>> getRoleIdMenuList(int roleId) {

		return sysMenuDao.getRoleMenuList(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveOrUpdate(String roleId, String ids)
			throws ServiceException {

		try {
			sysMenuDao.saveOrupdateRoleMenu(roleId, ids);

			return true;
		} catch (PersistentDataException e) {

			e.printStackTrace();
			throw new ServiceException(204, e.getMessage());
		}

	}

	@Override
	public List<SysMenu> getMenuList(Map<String, Object> param) {
		return sysMenuDao.getMenuList(param);
	}

//	@Override
//	public List<Map> fetchRoleMenus(List<String> where, Long... roleids)
//			throws ServiceException {
//		try {
//			String roleIds = StringUtil.toStringArray(roleids);
//			StringBuilder sql = new StringBuilder("select\n" + "	distinct\n"
//					+ "	rm.menu_id\n" + "from\n" + "sys_role_menu rm\n"
//					+ "join sys_menu m on m.id=rm.menu_id\n" + "where\n"
//					+ "	rm.role_id in(" + roleIds + ")\n"
//					+ "	and not exists(\n"
//					+ "		select 1 from sys_menu where parent_id=rm.menu_id\n"
//					+ "	)");
//			if (where != null && where.size() > 0) {
//				sql.append("and " + StringUtil.toString(where, " and "));
//			}
//
//			@SuppressWarnings("rawtypes")
//			List<Map> leafMenus = getSysMenuDao().querySql(sql.toString());
//
//			String menuLeafIds = StringUtil.toString(MapUtil.collectProperty(
//					leafMenus, "MENU_ID")); // menu_id
//
//			String ids = StringUtil.toString(getSysMenuDao().queryTreeNodeIds(
//					"sys_menu", "id", "parent_id", menuLeafIds));
//
//			sql = new StringBuilder();
//			sql.append("select\n" + "	id,\n" + "	parent_id	parentId,\n"
//					+ "	name		text,\n" + "	icon_cls	iconCls,\n"
//					+ "	scriptid	scriptId,\n" + "	sort,\n"
//					+ "	controller_name	controllerName\n" + "from sys_menu\n"
//					+ "where\n" + "	is_delete=0\n" + "	and id in(" + ids
//					+ ")\n" + "order by\n" + "	sort,sys_type");
//			List<Map> menuList = getSysMenuDao().querySql(sql.toString());
//
//			menuList = ListUtil.list2Tree(menuList, "ID", "PARENTID"); // id
//																		// parentId
//			return menuList;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new ServiceException("查询菜单失败", e);
//		}
//	}

//	@Transactional(rollbackFor = Exception.class)
//	@Override
//	public void saveOrUpdateMenuss(long roleId, List<Map<String, Object>> list)
//			throws ServiceException {
//		try {
//			baseDao.execute("delete from sys_role_menu where role_id=" + roleId);
//			baseDao.saveOrUpdates("sys_role_menu", list);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
//	}
//
//	@Override
//	public List<Map<String, Object>> getAllMenuList(Map<String, Object> map)
//			throws ServiceException {
//
//	
//			return sysMenuDao.getAllMenuList(map);
//		
//		
//
//	}

	@Override
	public BaseDao getMapper() {
		// TODO Auto-generated method stub
		return sysMenuDao;
	}

	@Override
	public SysMenu menuFind(long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void menuDelete(SysMenu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SysMenu> menuUseList() throws ServiceException {
		Map<String, Object> param = new HashMap<>();
		return this.sysMenuDao.getMenuList(param);
	}

	@Override
	public void menuDelete(long id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryTreeNodeIds(String leafIds) throws PersistentDataException {
		try {
			List<Map> parents = null;
			String menuLeafIds=leafIds;
			
			String[] Ids = menuLeafIds.split(",");

			List rIds=new ArrayList();
			do{
				parents=sysMenuDao.getMenuParentId(Ids);
                rIds.addAll(parents);
                menuLeafIds=StringUtil.toString(MapUtil.collectProperty(parents,"id",false));
                Ids= menuLeafIds.split(",");
            }while(parents.size()>0);

			List rlist=MapUtil.collectProperty(rIds,"id",false);

			for (String id : leafIds.split(",")) {
				rlist.add(id);
			}

			return rlist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> fetchRoleMenus(String sysType, Long... roleids)
			throws ServiceException {
		try {
			String roleIds= StringUtil.toStringArray(roleids);
			
			List<Map> leafMenus= sysMenuDao.getRoleSysMenu(roleIds, sysType);

			String menuLeafIds=StringUtil.toString(MapUtil.collectProperty(leafMenus, "menu_id"));

			String ids=StringUtil.toString(queryTreeNodeIds(menuLeafIds));
			
			String[] idsArray = ids.split(",");
			
			List<Map<String,Object>> menuList= sysMenuDao.getMenuByParentIds(idsArray);

			menuList=ListUtil.list2Tree(menuList,"value","parentId");
			return menuList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询菜单失败",e);
		}
	}

	@Override
	public void saveOrUpdateMenuss(long roleId, List<Long> menuIds)
			throws ServiceException {
		sysRoleMenuDao.deleteByRoleId(roleId);
		for (Long menuId : menuIds) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setMenuId(menuId);
			rm.setRoleId(roleId);
			sysRoleMenuDao.insert(rm);
		}
	}

	@Override
	public Map getMenuInfoById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getAllMenuList(Map<String, Object> map)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @description 菜单页的查询
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	@Override
	public List<Map<String, Object>> fetchRoleMenuHas(Long roleId) throws ServiceException {
		return sysMenuDao.fetchRoleMenuHas(roleId);
	}

	@Override
	public List<Map<String, Object>> fetchAllMenu() throws ServiceException {
		return sysMenuDao.fetchAllMenu();
	}
	
	/**
	 * 查询菜单下,可用的按钮列表
	 * @param scriptId
	 * @return
	 */
	@Override
	public List<String> findValidMenuListByScriptId(int parentId,int roleId){
		List<SysMenu> list = findChildMenu(parentId, roleId);
		return  findChildMenuList(list,roleId);
	}
	
	private List<String> findChildMenuList(List<SysMenu> list,int roleId){
		List<String> strList = new ArrayList<>();
		for (SysMenu sysMenu : list) {
			strList.add(sysMenu.getScriptid());
			//查询是否存在子节点
			List<SysMenu> childList =  findChildMenu(sysMenu.getId().intValue(),roleId);
			if(childList.isEmpty()){
				continue;
			}else{
				//递归调用
				findChildMenuList(childList,roleId);
			}
		}
		return strList;
	}
	
	/**
	 * 查询子按钮
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> findChildMenu(int parentId,int roleId){
		Map<String,Object> map = new HashMap<>();
		map.put("parentId", parentId);
		map.put("roleId", roleId);
		return sysMenuDao.findValidMenuListByScriptId(map);
	}

}