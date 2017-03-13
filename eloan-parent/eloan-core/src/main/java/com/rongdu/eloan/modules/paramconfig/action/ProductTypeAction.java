package com.rongdu.eloan.modules.paramconfig.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
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
import com.rongdu.eloan.common.utils.validator.ValidatorUtil;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.paramconfig.domain.ProductType;
import com.rongdu.eloan.modules.paramconfig.service.ProductTypeService;
import com.rongdu.eloan.modules.system.domain.SysUser;

/**
 * User:    wangmc
 * DateTime:2016-07-12 03:22:37
 * details: 产品类型表,Action请求层
 * source:  代码生成器
 */
@Controller
@RequestMapping("/modules/paramconfig/ProductTypeAction")
public class ProductTypeAction extends BaseAction {

    /**
     * 产品类型表的Service
     */
	@Autowired
	private ProductTypeService productTypeService;

    /**
     * 产品类型表表,插入数据
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
    @RequestMapping("/saveOrUpdateProductType.htm")
    public void saveOrUpdateProductType(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "json" ,required = false)String json,
    	@RequestParam(value = "act" ,required = false)String act  //执行的动作
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag=0;
	     SysUser sysUser = this.getLoginUser(request);
        ProductType productType = new ProductType();
        //对json对象进行转换
        if (!StringUtils.isEmpty(json))
            productType = JsonUtil.parse(json, ProductType.class);
   //     ValidatorUtil.validateDomain(obj);
		if(act.equals("create")){    //新增
		//动态插入数据
			productType.setCreateName(sysUser.getName());
			productType.setCreateDate(new Date());
			flag=productTypeService.insert(productType);
		} else if(act.equals("copy")){  
			productType.setProductType(productType.getProductType()+"_copy");//名称识别
			productType.setCreateName(sysUser.getName());
			productType.setCreateDate(new Date());
			flag=productTypeService.insert(productType);
			
		} 
		else{
		 //修改数据
			productType.setModifyName(sysUser.getName());
			productType.setModifyDate(new Date());
			flag=productTypeService.update(productType);
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
     * 产品类型表,查询数据
     * @param response      页面的response
     * @param currentPage   当前页数
     * @param pageSize      每页限制
     * @param searchParam   查询条件
     * @throws ServiceException
     */
    @RequestMapping("/getProductTypeList.htm")
    public void getProductTypeList ( HttpServletRequest request,HttpServletResponse response,
                        @RequestParam(value = "currentPage") Integer currentPage,
                        @RequestParam(value = "pageSize") Integer pageSize,
                        @RequestParam(value = "searchParam",required = false)String searchParam)
    throws Exception{
    Map<String, Object> paramap=new HashMap<String, Object>();
        //对json对象进行转换
        if (!StringUtils.isEmpty(searchParam))
         paramap = JsonUtil.parse(searchParam, Map.class);
         paramap.put("deleteState","0");
        
        PageBounds pageBounds = new PageBounds(currentPage, pageSize);
        //返回页面的json参数
      List<ProductType> pageList=productTypeService.getProductTypeList(paramap,pageBounds);
      int totalCount=productTypeService.getTotalCount(paramap);
      Map<String,Object> returnMap=new HashMap<String,Object>();
      
        //返回给页面
        returnMap.put("data", pageList);
		returnMap.put("totalCount", totalCount);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 产品类型下拉框
     * @throws ServiceException
     */
    @RequestMapping("/getProductTypeCombo.htm")
    public void getProductTypeCombo ( HttpServletRequest request,HttpServletResponse response)
    throws Exception{
    Map<String, Object> paramap=new HashMap<String, Object>();
    
         paramap.put("deleteState","0");
        //返回页面的json参数
      List<Map<String, Object>> comboList=productTypeService.getProductTypeCombo(paramap);
      Map<String,Object> returnMap=new HashMap<String,Object>();
      
        //返回给页面
        returnMap.put("data", comboList);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 资产渠道下拉框
     * @throws ServiceException
     */
    @RequestMapping("/getFaChannelCombo.htm")
    public void getFaChannelCombo ( HttpServletRequest request,HttpServletResponse response)
    throws Exception{
    Map<String, Object> paramap=new HashMap<String, Object>();
    
        //返回页面的json参数
      List<Map<String, Object>> comboList=productTypeService.getFaChannelCombo(paramap);
      Map<String,Object> returnMap=new HashMap<String,Object>();
      
        //返回给页面
        returnMap.put("data", comboList);
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		
        ServletUtils.writeToResponse(response, returnMap);
    }
}
