package com.rongdu.eloan.modules.system.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.domain.PageBean;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysDict;
import com.rongdu.eloan.modules.system.domain.SysDictDetail;
import com.rongdu.eloan.modules.system.service.SysDictDetailService;
import com.rongdu.eloan.modules.system.service.SysDictService;

/**
 * 
 * 数据字典管理
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午2:13:03
 */
@Controller
public class SysDictAction extends BaseAction {

	private Map<String, Object> map = new HashMap<String, Object>();

	@Autowired
	private SysDictService sysDictService;

	@Autowired
	private SysDictDetailService sysDictDetailService;

	/**
	 * @description       获取字典列表                     done
	 * @param response
	 * @param request
	 * @param start
	 * @param limit
	 * @param search
	 * @throws IOException
	 * @author fzc
	 * @return void
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/system/dictList.htm")
	public void getDictList(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "search", required = false) String search) throws Exception {
		Map<String,Object> searchMap = new HashMap<String,Object>();
		if (search != null) {
			searchMap = JsonUtil.parse(search, Map.class);
		}
		PageBean pgBean = sysDictService.getDictListAndCount(searchMap,currentPage,pageSize);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, pgBean.getData());
		result.put(Constant.RESPONSE_DATA_TOTALCOUNT, pgBean.getCount());
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);

	}

	/**
	 * @description   查询详情     done  
	 * @param response
	 * @param start
	 * @param limit
	 * @param id
	 * @throws Exception
	 * @author fzc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/modules/system/getDictDetail.htm")
	public void getDicDetailtList(HttpServletResponse response,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "id", required = false) String id)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		PageBean pageBean =null;
		Map<String,Object> result = new HashMap<String,Object>();
		if (id != null) {
			data.put("parentId", id);
			pageBean = sysDictService.getDictDetailList(data,currentPage,pageSize);
			result.put(Constant.RESPONSE_DATA, pageBean.getData());
			result.put(Constant.RESPONSE_DATA_TOTALCOUNT, pageBean.getCount());
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
		}

		ServletUtils.writeToResponse(response, result);

	}

	/**
	 * @description    新增或者更新字典   done 
	 * @param response
	 * @param data
	 * @param status
	 * @throws Exception
	 * @author fzc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping("/modules/system/addOrUpdateDict.htm")
	public void addOrModify(HttpServletResponse response,
			@RequestParam(value = "form", required = false) String form,
			@RequestParam(value = "status", required = false) String status)
			throws Exception {
		SysDict sysDict = JsonUtil.parse(form, SysDict.class);
		Map<String, Object> res = new HashMap<String, Object>();

		boolean backstauts = sysDictService.addOrModify(sysDict, status);
		if (backstauts) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			if(Constant.INSERT.equals(status)){
				res.put(Constant.RESPONSE_CODE_MSG, "插入成功");
			}else{
				res.put(Constant.RESPONSE_CODE_MSG, "更新成功");
			}
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/modules/system/addOrUpdateDictDetail.htm")
	public void addOrModifyDictDetail(HttpServletResponse response,
			@RequestParam(value = "form", required = false) String data,
			@RequestParam(value = "status", required = false) String status)
			throws Exception {
		SysDictDetail dictDetail = JsonUtil.parse(data, SysDictDetail.class);
		Map<String, Object> res = new HashMap<String, Object>();

		sysDictDetailService.addOrModify(dictDetail, status);

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		if(Constant.INSERT.equals(status)){
			res.put(Constant.RESPONSE_CODE_MSG, "插入成功");
		}else{
			res.put(Constant.RESPONSE_CODE_MSG, "更新成功");
		}
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 删除字典         done
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/modules/system/deleteDict.htm")
	public void deletDictById(HttpServletResponse response,
			@RequestParam(value = "id") String id) throws ServiceException {
		Map<String, Object> req = new HashMap<String, Object>();

		Map<String, Object> res = new HashMap<String, Object>();

		if (id != null) {
			req.put("parentId", id);
		} else {
			return;
		}
		try {
			if (sysDictDetailService.getItemCountMap(req) > 0) {
				throw new ServiceException(Constant.FAIL_CODE_VALUE, "有字典子项，删除失败");
			} else {
				sysDictService.deleteDict(Long.valueOf(id));
				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping(value = "/modules/system/deleteDictDetail.htm")
	public void deletDicDetailtById(HttpServletResponse response,
			@RequestParam(value = "id") String id) throws Exception {

		Map<String, Object> res = new HashMap<String, Object>();

		if (id != null) {
			sysDictDetailService.deleteSysDictDetail(Long.valueOf(id));
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 缓存字典使用               
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/getDicts.htm")
	public void getDictDetailCache(
			HttpServletResponse response,
			@RequestParam(value = "type") String type) throws Exception {
		List<Map<String, Object>> dicList = null;
		Map<String, Object> res = new HashMap<String, Object>();
			if (type != null) {
				dicList = sysDictService.getDictsCache(type);
			}
			if (dicList != null){
				res.put(Constant.RESPONSE_DATA, dicList);
				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "获取成功");
			}else{
				res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "获取失败");
			}
		ServletUtils.writeToResponse(response, res);
	}
	
	
	/**
	 * 
	 * @description 查询所有字典
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/dicAction/queryAllDic")
	public void queryAllDic(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Map<String,Object>> list=sysDictDetailService.queryAllDic();

		Map<String,Object> rec=new LinkedHashMap<String, Object>();
		for (Map<String, Object> o : list) {
			Map dic=(Map)rec.get(o.get("type_code"));
			if(dic==null){
				dic=new LinkedHashMap();
				rec.put(o.get("type_code").toString(),dic);
			}
			((Map)dic).put(o.get("itemCode"),o.get("itemValue"));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, rec);
		ServletUtils.writeToResponse(response, result);
	}
}
