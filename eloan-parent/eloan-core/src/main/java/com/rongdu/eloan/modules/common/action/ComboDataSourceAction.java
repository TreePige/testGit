package com.rongdu.eloan.modules.common.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.service.ComboDataSourceService;
import com.rongdu.eloan.modules.system.domain.SysUser;

/**
 * 所有的combo数据源
 */
@Controller
@RequestMapping("/modules/common/action/ComboAction")
public class ComboDataSourceAction extends BaseAction {
	@Autowired
	private ComboDataSourceService comboDataSourceService;
	
	/**
	 * 根据typeCode获取字典信息
	 * @param typeCode 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    @RequestMapping("/queryDic")
    public void queryDic(@RequestParam(required = true) String typeCode, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> returnMap = new HashMap<>();
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("typeCode", typeCode);
    	List<Map<String, Object>> result = comboDataSourceService.queryDic(params);
    	returnMap.put(Constant.RESPONSE_DATA, result);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
    }
	
    /**
     * 获取借款需求产品下拉信息
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping("/getAllProductSimpleInfos.htm")
	public void getAllProductSimpleInfos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		SysUser user = getLoginUser(request);
		String officeId = user.getOfficeId();
		Map<String, Object> params = new HashMap<>();
		params.put("officeId", officeId);
		List<Map<String, Object>> list = comboDataSourceService.getAllProductSimpleInfos(params);
		if (list.size() == 0) {
			params.remove("officeId");
			list = comboDataSourceService.getAllProductSimpleInfos(params);
		}
		returnMap.put(Constant.RESPONSE_DATA, list);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
	}
	
    /**
     * 根据产品id查询 产品信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/queryProductAbout.htm")
    public void queryProductAbout(@RequestParam(required = true) long productId, HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Map<String, Object> returnMap = new HashMap<>();
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("productId", productId);
    	Map<String, Object> result = comboDataSourceService.queryProductAbout(params);
    	returnMap.put(Constant.RESPONSE_DATA, result);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
    }
}
