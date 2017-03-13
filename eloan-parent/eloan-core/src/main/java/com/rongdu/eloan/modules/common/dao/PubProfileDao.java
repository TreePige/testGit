package com.rongdu.eloan.modules.common.dao;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.domain.PubProfile;

/**
* User:    lyc
* DateTime:2016-07-15 10:18:51
* details: 附件类型管理,Dao接口层
* source:  代码生成器
*/
@RDBatisDao
public interface PubProfileDao extends BaseDao<PubProfile,Long> {

    /**
     * 附件类型管理表,分页查询数据
     * @param pubProfile
     * @return List<PubProfile>
     */
    public List<PubProfile> getPageListByMap(Map<String, Object> map, PageBounds pageBounds);

    /**
     * 附件类型管理表,查询数据记录数
     * @param pubProfile
     * @return int
     */
    public int getPageCountByMap(Map<String, Object> data);
    
    /**
    * 附件类型管理表,删除数据
    * @param id 主键
    * @return
    */
    public int deleteById(long id);

	/**
	 * @description 查询所有类型
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> fetchAllAttach();

	/**
	 * @description
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> queryAttachTreeByTaskId(Long taskId);

	/**
	 * @description
	 * @param taskId
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> queryAttachTreeByProjectId(Long taskId);

	/**
	 * @description
	 * @param taskId
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> queryAttachTreeByConsultId(Long taskId);
}
