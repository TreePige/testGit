package com.rongdu.eloan.modules.paramconfig.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.paramconfig.domain.ProductType;

/**
* User:    wangmc
* DateTime:2016-07-12 03:22:37
* details: 产品类型表,Service接口层
* source:  代码生成器
*/
public interface ProductTypeService {

    /**
     * 产品类型表表,插入数据
     * @param productType 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
    long insert(ProductType productType)throws Exception;

    /**
     * 产品类型表表,修改数据
     * @param productType 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
  	long update(ProductType productType)throws Exception;


    /**
     * 产品类型表表,查询数据
     * @param productType 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
  	 List<ProductType> getProductTypeList (Map<String, Object> map, PageBounds pageBounds)throws Exception;
    
    
    /**
    * @Description: 获取总记录数 
    * @param @param map
    * @param @return
    * @param @throws Exception    设定文件 
    * @author wangmc
    * @return int    返回类型 
    * @throws
     */
   	 int getTotalCount(Map<String,Object> map)throws Exception;
   	 
   	 
   	 List<Map<String, Object>> getProductTypeCombo(Map<String,Object> map) throws Exception;
   	 
   	 List<Map<String, Object>> getFaChannelCombo(Map<String,Object> map) throws Exception;
}
