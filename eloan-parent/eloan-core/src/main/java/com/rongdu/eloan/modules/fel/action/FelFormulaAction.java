package com.rongdu.eloan.modules.fel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
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
import com.rongdu.eloan.modules.fel.domain.FelFormula;
import com.rongdu.eloan.modules.fel.service.FelService;
import com.rongdu.eloan.modules.fel.service.FelFormulaService;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 03:17:39 details:
 * 公式配置,公式模块,Action请求层 source: 代码生成器
 */
@RequestMapping("/modules/fel/FelFormulaAction")
@Controller
public class FelFormulaAction extends BaseAction {

	/**
	 * 公式配置,公式模块的Service
	 */
	@Autowired
	private FelFormulaService felFormulaService;
	/**
	 * 公式配置,计算
	 */
	@Autowired
	private FelService felService;

	/**
	 * 公式配置,公式模块表,插入数据
	 * 
	 * @param response
	 * @param json
	 * @param tags
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/Insert")
	public void Insert(HttpServletResponse response, @RequestParam(value = "json", required = false) String json,
			@RequestParam(value = "tags", required = false) String tags) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelFormula felformula = new FelFormula();
		List<Map<String, Object>> list = null;
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felformula = JsonUtil.parse(json, FelFormula.class);
		}
		felformula.setFormulaJson(tags);
		// 公式的json
		if (!StringUtils.isEmpty(tags)) {
			list = JsonUtil.parse(tags, ArrayList.class);
		}
		long influence = felFormulaService.Insert(felformula, list);
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
	 * 公式配置,公式模块表,修改数据
	 * 
	 * @param response
	 * @param json
	 * @param tags
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/Update")
	public void Update(HttpServletResponse response, @RequestParam(value = "json") String json,
			@RequestParam(value = "tags", required = false) String tags) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		FelFormula felformula = new FelFormula();
		List<Map<String, Object>> list = null;
		// 对json对象进行转换
		if (!StringUtils.isEmpty(json)) {
			felformula = JsonUtil.parse(json, FelFormula.class);
		}
		felformula.setFormulaJson(tags);
		// 公式的json
		if (!StringUtils.isEmpty(tags)) {
			list = JsonUtil.parse(tags, ArrayList.class);
		}
		// 动态修改的方法
		long influence = felFormulaService.Update(felformula, list);
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
		if(paramap.get("id") != null){
			String ids = String.valueOf(paramap.get("id"));
			String id[] = ids.split(",");
			paramap.remove("id");
			paramap.put("id", id);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<FelFormula> farcAsChannels = felFormulaService.getPageListByMap(paramap);
		PageInfo<FelFormula> page = new PageInfo<FelFormula>(farcAsChannels);
		returnMap.put(Constant.RESPONSE_DATA, page.getList());
		returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, page.getTotal());
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 公式配置,公式模块表,删除数据
	 * 
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/Delete")
	public void Delete(HttpServletResponse response, @RequestParam(value = "id") Integer id) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		long influence = felFormulaService.Delete(id);
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
	 * 公式配置,公式模块表,公式验证
	 * 
	 * @param response
	 * @param request
	 * @param json
	 * @param id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/calculation")
	public void calculation(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "json") String json, @RequestParam(value = "id") String id) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		// 对json对象进行转换
//		if (!StringUtils.isEmpty(json)) {
//			param = JsonUtil.parse(json, Map.class);
//		}
//		// 返回给页面
//		ServletUtils.writeToResponse(response, felService.calculate(id, param));
	}

	/**
	 * 
	 * @description 检验公式结果
	 * @param response
	 * @param request
	 * @param product
	 * @param id
	 * @throws Exception
	 * @author liyc
	 * @return void
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/inspect")
	public void inspect(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "json", required = true) String product) throws Exception {
		Map<String, Object> param = JsonUtil.parse(product, Map.class);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> inspectDate = felService.inspect(param);
		returnMap.put(Constant.RESPONSE_DATA, inspectDate);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
	}
}
