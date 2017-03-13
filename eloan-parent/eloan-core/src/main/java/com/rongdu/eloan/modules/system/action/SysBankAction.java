package com.rongdu.eloan.modules.system.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.StringUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.pagehelper.PageHelper;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.system.domain.SysBank;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysBankService;

/**
 * 
 * 银行卡管理Action
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午2:14:22
 */
@Controller
public class SysBankAction extends BaseAction {
	Logger logger = Logger.getLogger(SysBankAction.class);
	@Autowired
	private SysBankService sysBankService;

	@RequestMapping("/modules/system/sysBankAction/saveOrUpdate.htm")
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "json", required = false) String json,
			@RequestParam(value = "status", required = false) String status) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		SysUser user = this.getLoginUser(request);
		long influence = 0;
		SysBank sysBank = new SysBank();
		if (!StringUtils.isEmpty(json)) {
			sysBank = JsonUtil.parse(json, SysBank.class);
		}
		if (status.equals("create")) {
			sysBank.setCreator(user.getName());
			sysBank.setCreateTime(new Date());
			influence = sysBankService.insert(sysBank);
		} else {
			sysBank.setModifier(user.getName());
			sysBank.setUpdateTime(new Date());
			influence = sysBankService.update(sysBank);
		}
		if (influence > 0) {
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, returnMap);
	}

	@RequestMapping("/modules/system/sysBankAction/query.htm")
	public void query(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "currentPage") int currentPage, @RequestParam(value = "pageSize") int pageSize)
			throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramap = new HashMap<>();
		PageHelper.startPage(currentPage, pageSize);
		List<SysBank> sysBanks = sysBankService.getPageListByMap(paramap);
		int totalCount = sysBankService.getPageCountByMap(paramap);
		returnMap.put(Constant.RESPONSE_DATA, sysBanks);
		returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, totalCount);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	@RequestMapping("/modules/system/sysBankAction/deleteById.htm")
	public void deleteById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) long id) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		long influence = sysBankService.deleteById(id);
		if(influence > 0){
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}else{
			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, returnMap);
	}
}
