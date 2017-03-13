package com.rongdu.eloan.modules.system.action;

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
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysConfig;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysConfigService;

/**
 * User:    mcwang
 * DateTime:2016-08-04 03:26:22
 * details: 系统参数表,Action请求层
 * source:  代码生成器
 */
@Controller
@RequestMapping("/modules/system/SysConfigAction")
public class SysConfigAction extends BaseAction {

    /**
     * 系统参数表的Service
     */
	@Autowired
	private SysConfigService sysConfigService;

    /**
     * 系统参数表表,插入数据
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
    @RequestMapping("/saveOrUpdateSysConfig.htm")
    public void saveOrUpdateSysConfig(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "json" ,required = false)String json,
    	@RequestParam(value = "status" ,required = false)String status  //执行的动作
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag=0;
     
        SysConfig sysConfig = new SysConfig();
        //对json对象进行转换
        if (!StringUtils.isEmpty(json))
            sysConfig = JsonUtil.parse(json, SysConfig.class);
		if(status.equals("create")){
			  SysUser sysUser = this.getLoginUser(request);
			  sysConfig.setCreator(sysUser.getId());
		//动态插入数据
			flag=sysConfigService.insertSysConfig(sysConfig);
		}else{
		 //修改数据
			flag=sysConfigService.updateSysConfig(sysConfig);
		}
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }

    

    /**
     * 系统参数表,查询数据
     * @param response      页面的response
     * @param currentPage   当前页数
     * @param pageSize      每页限制
     * @param searchParam   查询条件
     * @param whereSql      直接的sql
     * @param fields        排序字段
     * @param rule          排序方式
     * @throws ServiceException
     */
    @RequestMapping("/getSysConfigList.htm")
    public void getSysConfigList ( HttpServletRequest request,HttpServletResponse response,
                        @RequestParam(value = "currentPage") Integer currentPage,
                        @RequestParam(value = "pageSize") Integer pageSize,
                        @RequestParam(value = "searchParam",required = false)String searchParam)
    throws Exception{
    Map<String, Object> paramap=new HashMap<String, Object>();
        //对json对象进行转换
        if (!StringUtils.isEmpty(searchParam))
         paramap = JsonUtil.parse(searchParam, Map.class);
         paramap.put("status", "0");
        
        PageBounds pageBounds = new PageBounds(currentPage, pageSize);
        //返回页面的json参数
      List<SysConfig> pageList=sysConfigService.getSysConfigList(paramap,pageBounds);
      int totalCount=sysConfigService.getTotalCount(paramap);
      Map<String,Object> returnMap=new HashMap<String,Object>();
      
        //返回给页面
        returnMap.put("data", pageList);
		returnMap.put("totalCount", totalCount);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 系统参数表表,逻辑删除
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
    @RequestMapping("/deleteSysConfig.htm")
    public void deleteSysConfig(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "id" ,required = false)String id
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag=0;
        SysConfig sysConfig = new SysConfig();
		 //修改数据
        	sysConfig.setId(Long.valueOf(id));
        	sysConfig.setStatus(1);
			flag=sysConfigService.updateSysConfig(sysConfig);
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
}
