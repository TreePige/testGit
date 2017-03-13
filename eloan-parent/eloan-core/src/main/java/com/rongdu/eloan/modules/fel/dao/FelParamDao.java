package com.rongdu.eloan.modules.fel.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.eloan.modules.fel.domain.FelParam;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 02:14:50
* details: 公式配置-------参数模块,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface FelParamDao extends BaseDao<FelParam, Long> {

    /**
     * 公式配置-------参数模块表,查询数据
     * @param felparam
     * @return List<Felparam>
     */
    public List<FelParam> Select(FelParam felparam);

    /**
     * 公式配置-------参数模块表,总数
     * @param felparam
     * @return Integer
     */
    public Integer Total(FelParam felparam);
    /**
     * 
     * @description 公式配置-------参数模块表,查询数据
     * @param felparam
     * @return
     * @author 孙凯伦
     * @return Felparam
     * @since  1.0.0
     */
    public FelParam SelectFelparam(FelParam felparam);

	public List<FelParam> getPageListByMap(Map<String, Object> map);

	public int getPageCountByMap(Map<String, Object> map);

	public List<FelParam> getListByMap(Map<String, Object> map1);
	
	/**
	 * 
	* @Description: 公式配置查询公式参数
	* @param @param felparam
	* @param @return    设定文件 
	* @author wangmc
	* @return List<FelParam>    返回类型 
	* @throws
	 */
	 public List<FelParam> selectParamById(@Param("id")String id);
}
