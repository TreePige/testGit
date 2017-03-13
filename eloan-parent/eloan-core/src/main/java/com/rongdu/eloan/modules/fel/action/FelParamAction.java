package com.rongdu.eloan.modules.fel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.fel.context.FelConstant;
import com.rongdu.eloan.modules.fel.domain.FelParam;
import com.rongdu.eloan.modules.fel.service.FelParamService;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 02:14:50 details:
 * 公式配置-------参数模块,Action请求层 source: 代码生成器
 */
@RequestMapping("/modules/fel/FelParamAction")
@Controller
public class FelParamAction extends BaseAction {

	/**
	 * 公式配置-------参数模块的Service
	 */
	@Autowired
	private FelParamService felParamService;

	/**
	 * 公式配置-------参数模块表,插入数据
	 * 
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping("/Insert")
	public void Insert(HttpServletResponse response, @RequestParam(value = "json", required = false) String json)
			throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelParam felparam = new FelParam();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felparam = JsonUtil.parse(json, FelParam.class);
		}
		// 动态插入的方面
		long influence = felParamService.Insert(felparam);
		// 影响行数>0则操作成功
		if (influence > 0) {
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 公式配置-------参数模块表,修改数据
	 * 
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping("/Update")
	public void Update(HttpServletResponse response, @RequestParam(value = "json") String json) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelParam felparam = new FelParam();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felparam = JsonUtil.parse(json, FelParam.class);
		}
		// 动态修改的方法
		long influence = felParamService.Update(felparam);
		// 影响行数>0则操作成功
		if (influence > 0) {
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 查询数据
	 * 
	 * @param response
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @param searchParam
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/Select")
	public void Select(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "currentPage") Integer currentPage,
			@RequestParam(value = "pageSize") Integer pageSize,
			@RequestParam(value = "searchParams", required = false) String searchParams) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramap = new HashMap<>();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(searchParams)) {
			paramap = JsonUtil.parse(searchParams, Map.class);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<FelParam> felparams = felParamService.getPageListByMap(paramap);
		PageInfo<FelParam> page = new PageInfo<>(felparams);
		returnMap.put(Constant.RESPONSE_DATA, page.getList());
		returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, page.getTotal());
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 变量全部获取
	 * 
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/formulaQuery")
	public void formulaQuery(HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String, Object>> felParams = felParamService.formulaQuery();
		returnMap.put(Constant.RESPONSE_DATA, felParams);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 
	 * @description 条件查询
	 * @param response
	 * @param request
	 * @throws Exception
	 * @author 孙凯伦
	 * @return void
	 * @since 1.0.0
	 */
	@RequestMapping("/conditionsQuery")
	public void conditionsQuery(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "json", required = false) String json) throws Exception {
		FelParam felparam = new FelParam();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felparam = JsonUtil.parse(json, FelParam.class);
		}
		// 返回页面的json参数
		List<Map<String, Object>> returnMap = felParamService.conditionsQuery(felparam);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	 /**
     * 
     * @description 查询所有的需要系统录入的参数
     * @param response
     * @param request
     * @throws Exception
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @RequestMapping ("/from/sys")
    public void getSysParam (
    		HttpServletResponse response,HttpServletRequest request
    		)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    Map<String, Object> map=new HashMap<String, Object>();
	map.put("dataSource", FelConstant.DATA_SOURCE_SYS);
	map.put("deleteState",FelConstant.UNDELETE);
    List<FelParam> felparams= felParamService.getPageListByMap(map);
    int totalCount = felParamService.getPageCountByMap(map);
	returnMap.put(Constant.RESPONSE_DATA, felparams);
	returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, totalCount);
	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
	// 返回给页面
	ServletUtils.writeToResponse(response, returnMap);
    }
}
