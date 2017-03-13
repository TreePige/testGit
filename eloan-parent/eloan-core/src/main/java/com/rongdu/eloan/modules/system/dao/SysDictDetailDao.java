package com.rongdu.eloan.modules.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysDictDetail;

/**
 * DAO接口
 * @author wgc
 * @version 1.0
 * @since 2014-11-03
 */
@RDBatisDao
public interface SysDictDetailDao extends BaseDao<SysDictDetail,Long> {

    /**
     *根据ID更新  通用更新
     *@param map
     *@return Boolean
     */ 
    Boolean updateSysDictDetailById(Map<String , Object> map);
    
    /**
     * 分页查询出的列表
     * @param mapdata
     * @return
     */
    List<Map<String, Object>> getSysDictDetailPageList(Map<String, Object> mapdata);
    
    /**
     * 获取总记录数
     * @return 记录数
     */
    int getSysDictDetailCount();
    
    
  
    public void saveOrUpdate(String nameSpace, Map<String, Object> agr);

	//List<Map<String, Object>> getPageListMap(Map<String, Object> data);

	Long getCount(Map<String, Object> data);

	/**
	 * @description
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	List<Map<String, Object>> queryAllDic();

	List<Map<String, Object>> getPageListMap(Map<String, Object> data);

}
