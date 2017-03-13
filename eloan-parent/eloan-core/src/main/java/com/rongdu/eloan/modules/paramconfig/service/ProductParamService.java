package com.rongdu.eloan.modules.paramconfig.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.domain.FelFormula;
import  com.rongdu.eloan.modules.paramconfig.domain.ProductParam;

/**
* User:    wangmc
* DateTime:2016-07-13 03:59:04
* details: 产品类型表,Service接口层
* source:  代码生成器
*/
public interface ProductParamService {

    /**
     * 产品类型表表,插入数据
     * @param productParam 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
    long insert(ProductParam productParam)throws Exception;

    /**
     * 产品类型表表,修改数据
     * @param productParam 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
  	long update(ProductParam productParam)throws Exception;


    /**
     * 产品类型表表,查询数据
     * @param productParam 产品类型表类
     * @return           返回页面map
     * @throws Exception
     */
  	 List<ProductParam> getProductParamList (Map<String, Object> map, PageBounds pageBounds)throws Exception;
    
    
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
   	 
   	 
   	 
   	 
   	/**
 	 * 
 	 * @description 计算产品所有包含公式的值
 	 * @param param
 	 * @param id 产品ID
 	 * @return
 	 * @throws ServiceException
 	 * @author liyc
 	 * @return Map<String,Object>
 	 * @since  1.0.0
 	 */
 	public Map<String, Object> inspect(Map<String, Object> param) throws ServiceException; 
 	
 	
 	/**
	 * 
	 * @description
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return List<Felformula>
	 * @since 1.0.0
	 */
  public List<FelFormula> queryByProductId(Long id) throws
 	 ServiceException;
  
  /**
   * 
  * @Description: 获取信托专户下拉框
  * @param @param map
  * @param @return
  * @param @throws Exception    设定文件 
  * @author wangmc
  * @return List<Map<String,Object>>    返回类型 
  * @throws
   */
  List<Map<String, Object>> getOfficeCombo(Map<String,Object> map) throws Exception;
}
