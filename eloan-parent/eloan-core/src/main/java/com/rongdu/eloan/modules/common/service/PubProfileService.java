package com.rongdu.eloan.modules.common.service;

import java.util.Map;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import  com.rongdu.eloan.modules.common.domain.PubProfile;

/**
 * 
 *	@Description 附件类型管理,Service接口层
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月15日 上午10:45:36
 *  @since version 1.0.0
 */
public interface PubProfileService {

    /**
     * 附件类型管理表,插入数据
     * @param pubProfile 附件类型管理类
     * @return           返回页面map
     * @throws Exception
     */
    public long insert(PubProfile pubProfile) throws Exception;

    /**
     * 附件类型管理表,修改数据
     * @param pubProfile 附件类型管理类
     * @return           返回页面map
     * @throws Exception
     */
    public long update(PubProfile pubProfile) throws Exception;

	/**
     * 附件类型管理表,分页查询数据
     * @param data
     * @param pageBounds
     * @return
     * @throws Exception
     */
    public List<PubProfile> getPageListByMap(Map<String , Object> data, PageBounds pageBounds) throws Exception;

    /**
     * 附件类型管理表,查询数据记录数
     * @param data
     * @return
     * @throws Exception
     */
    public int getPageCountByMap(Map<String, Object> data) throws Exception;

    /**
    * 附件类型管理表,删除数据
    * @param id 主键
    * @return   返回页面map
    * @throws Exception
    */
    public int deleteById(long id) throws Exception;
    
    /**
     * 
     * @description
     * @return
     * @throws Exception
     * @author liyc
     * @return List<Map<String,Object>>
     * @since  1.0.0
     */
    public List<Map<String, Object>> fetchAllAttach() throws Exception;

	/**
	 * @description
	 * @param id
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	*/
	public PubProfile getProfileById(Long id) throws Exception;

	/**
	 * @description
	 * @param rec
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	*/
	public void saveOrUpdate(PubProfile profile) throws Exception;
	
	/**
	 * 
	 * @description
	 * @param taskId
	 * @return
	 * @throws Exception
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> queryAttachTreeByTaskId(Long taskId) throws Exception;

	/**
	 * @description
	 * @param taskId
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> queryAttachTreeByProjectId(Long taskId) throws Exception;

	/**
	 * @description
	 * @param taskId
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	public List<Map<String, Object>> queryAttachTreeByConsultId(Long taskId) throws Exception;
}
