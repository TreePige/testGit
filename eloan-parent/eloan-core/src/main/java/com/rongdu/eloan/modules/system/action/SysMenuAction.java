package com.rongdu.eloan.modules.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ListUtil;
import com.rongdu.eloan.common.utils.MessageUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.constant.SystemConstant;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.Tree;
import com.rongdu.eloan.modules.system.domain.SysMenu;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.model.SysMenuCheck;
import com.rongdu.eloan.modules.system.service.SysDictService;
import com.rongdu.eloan.modules.system.service.SysMenuService;
import com.rongdu.eloan.modules.system.service.SysRoleService;

@Controller
public class SysMenuAction extends BaseAction {

	private static final Logger logger = LoggerFactory
			.getLogger(SysMenuAction.class);
	private static final int CHOISETREE = 1;

	private static final int CHOISETREENOCHECKED = 2;

	private static final int CHOISOTTHER = 3;

	private List<Map<String, Object>> roleIdMenuList;

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 修改菜单时加载出原有菜单数据
	 * @param request
	 * @param response
	 * @author ccf
	 * @created 2015年12月30日
	 */
	@RequestMapping(value="/modules/system/getMenuInfoById.htm")
	public void getMenuInfoById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id",required=true) Long id)
		throws Exception {	
		Map<String,Object> responseMap = new HashMap<String, Object>();
		long parentId = sysMenuService.menuFind(id).getParentId();//父节点
		if(parentId == 0){//无父节点
			responseMap.put("data",sysMenuService.menuFind(id));
		}else{
			responseMap.put("data",sysMenuService.getMenuInfoById(id));
		}	
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);       		
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/system/findValidMenuListByScriptId.htm")
	public void findValidMenuListByScriptId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="scriptid",required=false) String scriptid,
			@RequestParam(value="roleId",required=false) Integer roleId)
		throws Exception {
		Map<String,Object> responseMap = new HashMap<String, Object>();
		
		if(StringUtil.isBlank(scriptid) && (id ==null || id.longValue() == 0)){
				throw new ServiceException("scriptid | id 参数错误");	
		}

		Map<String,Object> param = new HashMap<>();
		if(StringUtil.isNotBlank(scriptid)){
			param.put("scriptid", scriptid);
		}
		if(id != null && id.longValue()>0){
			param.put("id", id);
		}
		
		List<SysMenu> list = sysMenuService.getMenuList(param);
		if(list.isEmpty()){
			throw  new ServiceException("菜单不存在");
		}
		SysMenu sysMenu = list.get(0);
		List<String> scriptList = sysMenuService.findValidMenuListByScriptId(sysMenu.getId().intValue()
				,roleId);
		
		responseMap.put("data",scriptList);
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);       		
		ServletUtils.writeToResponse(response, responseMap);
	}

	@RequestMapping("/modules/system/sconfig/fetchRoleMenu.htm")
	public void fetchRoleMenu(
			HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="sysType", required=true) String sysType
			) throws Exception{
		Long roldId=getRoleForLoginUser(request).getId();
		if(sysType==null){
			throw new RuntimeException("请指定登录的系统类型");
		}else if(!sysType.matches("[\\d,]+")){
			throw new RuntimeException("参数错误");
		}
		List<Map<String, Object>> menus=sysMenuService.fetchRoleMenus(sysType,roldId);
		menus=ListUtil.treeForExt(menus,null,null,true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, menus);
		ServletUtils.writeToResponse(response, res);
	}


	@RequestMapping("/modules/system/sconfig/saveOrUpdateRoleMenus.htm")
	public void saveOrUpdateRoleMenus(
			HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value = "checkedkey") String checkedkey, 
			@RequestParam(value = "roleId") long roleId
			) throws Exception {
		List<Long> menuIds = JSONObject.parseArray(checkedkey,Long.class);
		sysMenuService.saveOrUpdateMenuss(roleId,menuIds);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/modules/system/sconfig/fetchRoleMenuHas.htm")
	public void fetchRoleMenuHas(
			HttpServletRequest request,HttpServletResponse response,
			long roleId
			) throws Exception{
		List<Map<String, Object>> list=sysMenuService.fetchRoleMenuHas(roleId);
		for (Map<String, Object> rec : list) {
			if(rec.get("checked").toString().equals("1")){
				rec.put("checked",true);
			}else{
				rec.put("checked",false);
			}
		}
		list=ListUtil.list2Tree(list,"value","parentId");
		list=ListUtil.treeForExt(list, null, null, true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
	}
    
	
	@RequestMapping("/modules/system/sconfig/fetchAllMenu.htm")
	public void fetchAllMenu(
			HttpServletRequest request,HttpServletResponse response
			) throws Exception{
		List<Map<String, Object>> list=sysMenuService.fetchAllMenu();
		list=ListUtil.list2Tree(list,"value","parentId");
		list=ListUtil.treeForExt(list,null,null,true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
	}




	/**
	 * 获取用户某个菜单面板项集合
	 * 
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/sconfig/westone.htm")
	public void getleftMenuPanelList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "parentId", required = false) String id)
			throws Exception {
		List<SysMenu> menuList = null;

		if (Integer.valueOf(id) == 0) {
			logger.info("------------------SysMenuAction-getleftMenuPanelList()---parentId="+id+" userName="+this.getLoginUserName(request)+" role="+this.getRole(request));
			
			menuList = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(
					this.getLoginUserName(request), id, CHOISETREENOCHECKED,
					this.getRole(request));
			//menuList = (List<SysMenu>) sysMenuService.getMenuPanelByParentId("kf_lsk","0",2,this.getRole(request));
		}

		Map<Object, Object> result = new HashMap<Object, Object>();

		if (menuList.size() > 0 && menuList != null) {
			result.put("result", true);

			result.put("datas", menuList);
		} else {
			result.put("result", false);

		}
		ServletUtils.writeToResponse(response, result);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/sconfig/tree.htm")
	public void getleftMenuTreeList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "parentId", required = false) String id,
			@RequestParam(value = "node", required = false) String node) {
        logger.info("###########################SysMenuAction---getleftMenuTreeList(): parentId="+id+"  node="+node);
		List<SysMenu> menuList = null;
		SysUser sysUser = this.getLoginUser(request);
		try {

			if (id != null && "root".equalsIgnoreCase(node)) {

				menuList = (List<SysMenu>) sysMenuService
						.getMenuPanelByParentId(
								sysUser.getUserName(), id,
								CHOISETREENOCHECKED, this.getRole(request));
			} else if (!"NaN".equalsIgnoreCase(node)) {
				menuList = (List<SysMenu>) sysMenuService
						.getMenuPanelByParentId(
								sysUser.getUserName(), node,
								CHOISETREENOCHECKED, this.getRole(request));

				setLevelVaule(menuList);
			}
			ServletUtils.writeToResponselist(response, menuList);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * 配置菜单
	 * 
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/sconfig/menutree.htm")
	public void getMenuTreeList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "parentId", required = false) String id,
			@RequestParam(value = "node", required = false) String node)
			throws Exception {
		List<SysMenu> menuLists = null;
		if ("root".equalsIgnoreCase(node)) {
			menuLists = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(
					this.getSysUser().getUserName(), id, CHOISETREENOCHECKED,
					this.getRole(request));

		} else {
			// menuLists = (List<SysMenu>)
			// sysMenuService.getMenuPanelByParentId(
			// this.getSysUser().getUserName(), node);
			menuLists = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(
					"system", node, CHOISETREENOCHECKED, this.getRole(request));
		}

		ServletUtils.writeToResponselist(response, menuLists);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/menu/update.htm")
	public void menu(HttpServletResponse response,
			@RequestParam(value = "menu", required = false) String data,
			@RequestParam(value = "status", required = false) String status)
			throws Exception {
		Map<String, Object> dataMap = JsonUtil.parse(data, Map.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if ("create".equalsIgnoreCase(status)) {
				int n = sysMenuService.addMenu(dataMap);
				if (n > 0) {
					responseMap.put(Constant.RESPONSE_CODE,
							Constant.SUCCEED_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
				} else {
					responseMap.put(Constant.RESPONSE_CODE,
							Constant.FAIL_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
				}
		} else if ("update".equals(status)) {
				int total = sysMenuService.updateMenu(dataMap);
				if (total > 0) {
					responseMap.put(Constant.RESPONSE_CODE,
							Constant.SUCCEED_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
				} else {
					responseMap.put(Constant.RESPONSE_CODE,
							Constant.FAIL_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
				}
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	@RequestMapping("/menu/delete.htm")
	public void delete(HttpServletResponse response,
			@RequestParam(value = "id", required = false) Long id
			) throws Exception {
		Map<String,Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("isDelete", 1);
		sysMenuService.updateMenu(dataMap);
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);       		
		ServletUtils.writeToResponse(response, responseMap);
	}

	@RequestMapping(value = "/modules/system/sconfig/rolemenu.htm")
	public void saveOrUpdateRoleMenu(HttpServletResponse response,
			@RequestParam(value = "form", required = false) String ids,
			@RequestParam(value = "roleId", required = false) String roleId,
			Map<String, Object> res) throws IOException {

		try {
			if (ids != null && roleId != null) {
				sysMenuService.saveOrUpdate(roleId, ids);
				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "角色 分配成功");

			} else {

				return;

			}

		} catch (ServiceException e) {

			res.put(Constant.RESPONSE_CODE, e.getCode());
			res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

			e.printStackTrace();

		}
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings("unchecked")
	public List<SysMenuCheck> getMenuList(String id, HttpServletRequest request)
			throws ServiceException {

		List<SysMenuCheck> menuLists = (List<SysMenuCheck>) sysMenuService
				.getMenuPanelByParentId("system", id, CHOISOTTHER,
						this.getRole(request));

		for (SysMenuCheck sysMenu : menuLists) {

			sysMenu.setChildren(this.getMenuList(
					String.valueOf(sysMenu.getId()), request));
			// 重构標記
			for (Map<String, Object> sysMenuCheck : roleIdMenuList) {

				if (sysMenuCheck.containsValue(Integer.parseInt(String
						.valueOf(sysMenu.getId())))) {
					sysMenu.setChecked(true);

				}

			}
		}
		return menuLists;

	}

	public List<Map<String, Object>> getRoleIds(String roleId) {
		if (roleId != null) {
			return sysMenuService.getRoleIdMenuList(Integer.valueOf(roleId));

		}

		return null;
	}

	/**
	 * 菜单下拉框实现
	 * 
	 * @param request
	 * @param response
	 * @version 1.0
	 * @author 吴国成
	 * @throws ServiceException 
	 * @created 2014年12月19日
	 */
	@RequestMapping(value = "/modules/system/sconfig/menucombo.htm")
	public void getMenuCombo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("isDelete", 0);
			List<SysMenu> sysMenus = sysMenuService.getMenuList(param);
			Map<String, Object> res = new HashMap<String, Object>();
			
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_DATA, Tree.TreeList(sysMenus, "id", "text", "parentId"));
			// 将树返回到页面
			ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings({ "unchecked" })
	private List<? extends SysMenu> getMenuList(String id, String node,
			HttpServletRequest request) throws ServiceException {
		List<? extends SysMenu> menuLists = null;
		if ("root".equalsIgnoreCase(node)) {
			menuLists = (List<? extends SysMenu>) sysMenuService
					.getMenuPanelByParentId(this.getSysUser().getUserName(),
							id, CHOISETREE, this.getRole(request));

		} else {

			menuLists = (List<? extends SysMenu>) sysMenuService
					.getMenuPanelByParentId("system", node, CHOISETREE,
							this.getRole(request));
		}

		return menuLists;
	}

	private void setChecked(List<SysMenuCheck> menuList,
			List<Map<String, Object>> roleIds) {

		if (menuList != null) {

			Iterator<SysMenuCheck> sysMenus = menuList.iterator();

			while (sysMenus.hasNext()) {
				SysMenuCheck sysMenu = sysMenus.next();

				for (int i = 0; i < roleIds.size(); i++) {
					Map<String, Object> s = roleIds.get(i);

					if (s.containsValue(Integer.parseInt(String.valueOf(sysMenu
							.getId())))) {
						sysMenu.setChecked(true);

					}
				}

			}

		}

	}

	private void setLevelVaule(List<? extends SysMenu> menuList) {

		if (menuList != null) {

			Iterator<? extends SysMenu> sysMenus = menuList.iterator();

			while (sysMenus.hasNext()) {
				SysMenu sysMenu = sysMenus.next();

				if (sysMenu.getLevel() == 3) {
					sysMenu.setLeaf(true);
				}

			}

		}

	}

}
