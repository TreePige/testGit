package com.rongdu.eloan.modules.fel.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.rongdu.eloan.modules.fel.domain.FelType;
import com.rongdu.eloan.modules.fel.service.FelTypeService;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 03:28:56 details:
 * 公式配置,类型模块,Action请求层 source: 代码生成器
 */
@RequestMapping("/modules/fel/FeltypeAction")
@Controller
public class FelTypeAction extends BaseAction {
	@Autowired
	private FelTypeService felTypeService;

	/**
	 * 公式配置,类型模块表,插入数据
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping("/Insert")
	public void Insert(HttpServletResponse response, @RequestParam(value = "json", required = false) String json)
			throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelType feltype = new FelType();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			feltype = JsonUtil.parse(json, FelType.class);
		}
		// 动态插入的方面
		long influence = felTypeService.Insert(feltype);
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
	 * 公式配置,类型模块表,修改数据
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping("/Update")
	public void Update(HttpServletResponse response, @RequestParam(value = "json") String json)
			throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelType felType = new FelType();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felType = JsonUtil.parse(json, FelType.class);
		}
		// 动态修改的方法
		long influence = felTypeService.Update(felType);
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
			@RequestParam(value = "currentPage") Integer currentPage, @RequestParam(value = "pageSize") Integer pageSize,
			@RequestParam(value = "searchParams", required = false) String searchParams)
			throws Exception {
		// 返回给页面的Map
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramap = new HashMap<>();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(searchParams)) {
			paramap = JsonUtil.parse(searchParams, Map.class);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<FelType> felTypes = felTypeService.getPageListByMap(paramap);
		PageInfo<FelType> page = new PageInfo<>(felTypes);
		returnMap.put(Constant.RESPONSE_DATA, page.getList());
		returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, page.getTotal());
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 公式配置,类型模块表,查询全部数据
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/SelectAll")
	public void SelectAll(HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 返回给页面的Map
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramap = new HashMap<>();
		List<Map<String, Object>> felTypes = felTypeService.getListByMap(paramap);
		returnMap.put(Constant.RESPONSE_DATA, felTypes);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}
}
