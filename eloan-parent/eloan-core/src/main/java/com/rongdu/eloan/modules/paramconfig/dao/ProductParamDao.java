package com.rongdu.eloan.modules.paramconfig.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.paramconfig.domain.ProductParam;

/**
* User:    wangmc
* DateTime:2016-07-13 03:59:04
* details: 产品类型表,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface ProductParamDao extends BaseDao<ProductParam,Long> {


    /**
     * 产品类型表表,查询数据
     * @param map,pageBounds
     * @return List<ProductParam>
     * @throws PersistentDataException
     */
    List<ProductParam> select(Map<String, Object> map,PageBounds pageBounds);

    /**
     * 产品类型表表,总数
     * @param map
     * @return Integer
     * @throws PersistentDataException
     */
    Integer total(Map<String, Object> map);
    
    
    ProductParam getProductById(Long id);
    
    
    /**
     * 
    * @Description: 查询信托专户下拉框
    * @param @param map
    * @param @return    设定文件 
    * @author wangmc
    * @return List<Map<String,Object>>    返回类型 
    * @throws
     */
    List<Map<String, Object>> getOfficeCombo(Map<String, Object> map);
}
