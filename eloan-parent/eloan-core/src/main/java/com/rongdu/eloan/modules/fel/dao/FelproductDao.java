package com.rongdu.eloan.modules.fel.dao;

import java.util.List;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.fel.domain.Felproduct;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 03:33:41
* details: 公式配置,产品模块,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface FelproductDao extends BaseDao<Felproduct,Long> {

    /**
     * 公式配置,产品模块表,插入数据
     * @param felproduct
     * @return Object
     * @throws PersistentDataException
     */
    public Object Insert(Felproduct felproduct)throws PersistentDataException;

    /**
     * 公式配置,产品模块表,修改数据
     * @param felproduct
     * @return boolean
     * @throws PersistentDataException
     */
    public boolean Update(Felproduct felproduct)throws PersistentDataException;

    /**
     * 公式配置,产品模块表,查询数据
     * @param felproduct
     * @return List<Felproduct>
     * @throws PersistentDataException
     */
    public List<Felproduct> Select(Felproduct felproduct)throws PersistentDataException;

    /**
     * 公式配置,产品模块表,总数
     * @param felproduct
     * @return Integer
     * @throws PersistentDataException
     */
    public Integer Total(Felproduct felproduct)throws PersistentDataException;
    /**
     * 公式配置,产品模块表,查询数据
     * @param felproduct
     * @return Felproduct
     * @throws PersistentDataException
     */
    public Felproduct SelectFelproduct(Felproduct felproduct) throws PersistentDataException;
    /**
     * 
     * @description 公式配置,产品模块表,删除数据
     * @param id
     * @throws PersistentDataException
     * @author 孙凯伦
     * @return void
     * @since  1.0.0
     */
    public void Delete(Integer id) throws PersistentDataException;

}
