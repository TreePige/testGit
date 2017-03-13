package com.rongdu.eloan.modules.system.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ListUtil;
import com.rongdu.eloan.common.utils.MessageUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.CheckBoxTree;
import com.rongdu.eloan.modules.common.utils.QueryPameterUtils;
import com.rongdu.eloan.modules.common.utils.Tree;
import com.rongdu.eloan.modules.system.domain.SysDict;
import com.rongdu.eloan.modules.system.domain.SysOffice;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysDictService;
import com.rongdu.eloan.modules.system.service.SysOfficeService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 
 * 组织机构action
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午2:06:32
 */
@Controller
public class SysOfficeAction extends BaseAction {
	private static final int DEP_2 = 2; //营业部

	private static final Logger logger = LoggerFactory.getLogger(SysOfficeAction.class);

	/** 参数封装map */
	private Map<String, Object> map = new HashMap<String, Object>();
	/** 组织结构service */
	@Autowired
	private SysOfficeService sysOfficeService;
	/** 数据字典service */
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private SysUserService sysUserService;

	
	/**
	 * 查询所有机构                
	 * @description
	 * @param request
	 * @param response
	 * @author fzc
	 * @return void
	 * @throws ServiceException 
	 * @since  1.0.0
	 */
	@RequestMapping("/modules/system/fetchAllOffice.htm")
	public void fetchAllOffice(final HttpServletRequest request,HttpServletResponse response) throws ServiceException{
		Map<String,Object> responseMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = sysOfficeService.getOfficeTreeList();
		if(list.size()>0){
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			responseMap.put(Constant.RESPONSE_DATA, list);
		}else{
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG,Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 增加机构、修改机构        
	 * @param request
	 * @param response
	 * @param office
	 * @param status 
	 * @version 1.0
	 * @author 吴国成
	 * @throws ServiceException 
	 * @created 2014年10月23日
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/system/addoffice.htm")
	public void addOffice(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value="office",required=false) String office,
			@RequestParam(value="status",required=false) String status) throws ServiceException{
		Map<String,Object> responseMap = new HashMap<String, Object>();
		Map<String,Object> paramap = JsonUtil.parse(office, Map.class);
		//获取当前登录用户信息 
		String  userName = this.getLoginUser(request).getUserName();
		Date d = new Date();
		String id = "";
		String parentId = "0";
		if(null ==paramap.get("parentId")){
			id = "11";
		}else{
			parentId = String.valueOf(paramap.get("parentId"));
		}
					
		if("create".equals(status)){//增加机构
			//下一级ID处理
			StringBuilder sb = new StringBuilder();
			/*Map<String, Object> m = new HashMap<String, Object>();
			m.put("parentId", parentId);*/
			String officeId = sysOfficeService.getIdByParentId(parentId);
			if(StringUtils.isEmpty(officeId)){
				id=String.valueOf(sb.append(parentId).append("01"));
			}else{
				id = String.valueOf(sb.append(officeId));
			}					
			
			SysOffice sysOffice = new SysOffice();
			sysOffice.setName(String.valueOf(paramap.get("name")));
			sysOffice.setSort(Integer.valueOf(String.valueOf(paramap.get("sort"))));
			if(paramap.get("remark") != null){
				sysOffice.setRemark(String.valueOf(paramap.get("remark")));
			}
			sysOffice.setAddTime(d);
			sysOffice.setAddUser(userName);
			sysOffice.setParentId(parentId);//上级
			sysOffice.setId(id);//主键，这个自定义			
			//sysOffice.setIsDelete((byte)0);
			sysOffice.setType(Byte.valueOf(String.valueOf(paramap.get("type"))));
			sysOffice.setIsDelete(Byte.valueOf(String.valueOf(paramap.get("isDelete"))));
			if(paramap.get("address") != null){
				sysOffice.setAddress(String.valueOf(paramap.get("address")));
			}
			sysOffice.setArea(String.valueOf(paramap.get("area")));
			sysOffice.setOfficeBank(String.valueOf(paramap.get("officeBank")));
			sysOffice.setOfficeBankCard(String.valueOf(paramap.get("officeBankCard")));
			if(paramap.get("phone") != null){
				sysOffice.setPhone(String.valueOf(paramap.get("phone")));
			}
			sysOffice.setOfficeNumber(String.valueOf(paramap.get("officeNumber")));
			sysOffice.setOfficeCard(String.valueOf(paramap.get("officeCard")));
			sysOffice.setCity(String.valueOf(paramap.get("city")));
			sysOfficeService.officeAdd(sysOffice);
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}else if("update".equals(status)){//修改机构
			paramap.remove("parentId");
			paramap.put("updateTime", new Date());
			paramap.put("updateUser", userName);
			Boolean isTrue = sysOfficeService.updateSysOfficeById(paramap);
			if(isTrue){
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			}			
		}else{//返回信息
			responseMap.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG,Constant.OPERATION_FAIL);
		}
		
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 编辑机构时加载出原有机构数据          
	 * @param request
	 * @param response
	 * @author ccf
	 * @throws Exception 
	 * @created 2015年12月30日
	 */
	@RequestMapping(value="/modules/system/getOfficeInfoById.htm")
	public void getOfficeInfoById(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="id",required=true) Long id) throws Exception{	
		Map<String,Object> responseMap = new HashMap<String, Object>();
		if (id == 11) {// 根节点
			responseMap.put("data", sysOfficeService.getOfficeById(id));
		} else {
			responseMap.put("data", sysOfficeService.getOfficeInfoById(id));
		}
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG,Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 删除组织机构
	 * 
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "/modules/system/general/officeDelete.htm")
	public void officeDelete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Map<String,Object> responseMap = new HashMap<String, Object>();
		try {
			sysOfficeService.deleteOfficeAll(id);
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} catch (ServiceException e) {
			e.printStackTrace();
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "请稍后在试");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 获取带checkbox组织树
	 * @param request
	 * @param response 
	 * @version 1.0
	 * @author 吴国成
	 * @throws ServiceException 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @created 2014年12月31日
	 */
	@RequestMapping(value="/modules/system/checkboxoffices.htm")
	public void getCheckBoxOffices(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		Map<String,Object> responseMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		//String id = null;
		String[] officeOverIds = null;
	    if(StringUtil.isNoneBlank(id)){//如果ID不为空，查询出该用户的管辖区域。
			SysUser sysUser = sysUserService.getUserById(Long.parseLong(id));
			String officeOver = sysUser.getOfficeOver();
			if(null != officeOver){
				officeOverIds = officeOver.split(";");
		    }		
	    }
	    
		String officeId = this.getLoginUser(request).getOfficeId();
		//查询所有的组织
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("isDelete", 0);
		param.put("id", officeId);
		List<SysOffice> sysOfficesList = sysOfficeService.getListByOfficeId(param);
		
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_DATA, CheckBoxTree.TreeList(sysOfficesList, "id", "name", "parentId",null == officeOverIds?"":officeOverIds[0]));
		//将树返回到页面
		ServletUtils.writeToResponse(response,responseMap);
	}
	
    @RequestMapping("/modules/system/getProjectBelongInfo.htm")
    public void getProjectBelongInfo(
    		HttpServletResponse response, 
    		HttpServletRequest request) throws Exception{
    	SysRole role = getRoleForLoginUser(request);
    	SysUser user = getSysUser();
    	Map<String,Object> projectBelongInfo = sysOfficeService.getProjectBelongInfo(role.getNid(),user.getId());
    	
    	Map<String,Object> responseMap = new HashMap<String, Object>();
    	responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
    	responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		responseMap.put(Constant.RESPONSE_DATA, projectBelongInfo);
		//返回到页面
		ServletUtils.writeToResponse(response,responseMap);
    }
}
