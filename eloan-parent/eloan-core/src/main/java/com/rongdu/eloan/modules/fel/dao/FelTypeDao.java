package com.rongdu.eloan.modules.fel.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.eloan.modules.fel.domain.FelType;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 03:28:56
* details: 公式配置,类型模块,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface FelTypeDao extends BaseDao<FelType, Long> {

    /**
     * 公式配置,类型模块表,插入数据
     * @param feltype
     * @return Object
     */
    public Object Insert(FelType feltype);

    /**
     * 公式配置,类型模块表,修改数据
     * @param feltype
     * @return boolean
     */
    public boolean Update(FelType feltype);

    /**
     * 公式配置,类型模块表,查询数据
     * @param feltype
     * @return List<Feltype>
     */
    public List<FelType> Select(FelType feltype);

    /**
     * 公式配置,类型模块表,总数
     * @param feltype
     * @return Integer
     */
    public Integer Total(FelType feltype);
    /**
     * 
     * @description 公式配置,类型模块表,查询数据
     * @param feltype
     * @return
     * @author 孙凯伦
     * @return Feltype
     * @since  1.0.0
     */
    public FelType SelectFeltype(FelType feltype);
    
    /**
     * 
     * @description 根据英文名查找某个产品类型
     * @param enName
     * @return
     * @author liyc
     * @return Feltype
     * @since  1.0.0
     */
    public FelType selectByEnName(@Param("enName") String  enName);

	public List<FelType> getPageListByMap(Map<String, Object> map);

	public int getPageCountByMap(Map<String, Object> map);

	public List<FelType> getListByMap(Map<String, Object> map);
}
