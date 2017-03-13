package com.rongdu.eloan.modules.paramconfig.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.paramconfig.domain.ProductType;

/**
* User:    wangmc
* DateTime:2016-07-12 03:22:37
* details: 产品类型表,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface ProductTypeDao extends BaseDao<ProductType,Long> {


    /**
     * 产品类型表表,查询数据
     * @param map,pageBounds
     * @return List<FelProduct>
     * @throws PersistentDataException
     */
    List<ProductType> select(Map<String, Object> map,PageBounds pageBounds);

    /**
     * 产品类型表表,总数
     * @param map
     * @return Integer
     * @throws PersistentDataException
     */
    Integer total(Map<String, Object> map);
    
    /**
    * @Description: 查询单个实体
    * @param @param productType
    * @param @return    设定文件 
    * @author wangmc
    * @return ProductType    返回类型 
    * @throws
     */
    ProductType getProductTypeItem(ProductType productType);
    
    
    List<Map<String, Object>> getProductTypeCombo(Map<String, Object> map) ;
    
    
    List<Map<String, Object>> getFaChannelCombo(Map<String, Object> map) ;
    
}

