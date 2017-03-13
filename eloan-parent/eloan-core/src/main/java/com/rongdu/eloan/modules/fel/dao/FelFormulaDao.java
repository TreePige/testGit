package com.rongdu.eloan.modules.fel.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.eloan.modules.fel.domain.FelFormula;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 03:17:39
* details: 公式配置,公式模块,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface FelFormulaDao extends BaseDao<FelFormula, Long> {

    /**
     * 公式配置,公式模块表,查询数据
     * @param felformula
     * @return List<Felformula>
     */
    public List<FelFormula> Select(FelFormula felformula);

    /**
     * 公式配置,公式模块表,总数
     * @param felformula
     * @return Integer
     */
    public Integer Total(FelFormula felformula);
    /**
     * 
     * @description 公式配置,公式模块表,查询数据
     * @param felformula
     * @return
     * @author 孙凯伦
     * @return Felformula
     * @since  1.0.0
     */
    public FelFormula SelectFelformula(FelFormula felformula);
    /**
     * 
     * @description 公式配置,类型模块表,删除数据
     * @param id
     * @author 孙凯伦
     * @return void
     * @since  1.0.0
     */
    public void Delete(Integer id);
    
    /**
     * 
     * @description 根据公式类型和id查找公式
     * @param id 公式id
     * @param typeId 公式类型Id
     * @return
     * @author liyc
     * @return Felformula
     * @since  1.0.0
     */
    public FelFormula SelectFelformulaByTypeAndId(String id, Long typeId);

	public List<FelFormula> getPageListByMap(Map<String, Object> map);

	public int getPageCountByMap(Map<String, Object> map);

	public long delete(Integer id);
	
	/**
	 * 
	* @Description: 根据id查询公式
	* @param @return    设定文件 
	* @author wangmc
	* @return List<FelFormula>    返回类型 
	* @throws
	 */
	
    public List<FelFormula> selectFormulaById(@Param("id") String id);

	public FelFormula SelectFelformulaByTypeAndId(Map<String, Object> map);

}
