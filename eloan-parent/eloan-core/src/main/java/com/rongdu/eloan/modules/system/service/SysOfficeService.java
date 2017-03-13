package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.common.domain.query.Pagination;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysOffice;

/**
 * 
 * 组织机构服务类
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:55:52
 */
public interface SysOfficeService {
	/**
	 * 添加组织机构
	 * 
	 * @param office 组织机构对象
	 * @throws ServiceException 
	 */
	long officeAdd(SysOffice office) throws ServiceException;

	/**
	 * 查询组织机构
	 * 
	 * @param id 查询id
	 * @return 组织机构对象
	 */
	SysOffice getOfficeById(Long id)throws ServiceException;

	/**
	 * 获取未删除的组织机构
	 * 
	 * @return 返回未删除的组织机构
	 */
	List<SysOffice> getOfficeUseList()throws ServiceException;
	
	/**
	 * 删除组织机构信息，将下面所有的子信息都删除
	 * @param id
	 */
	public void deleteOfficeAll(Long id)throws ServiceException;
	
    /**
     *根据ID更新  通用更新
     *@param map
     *@return Boolean
     */ 
    Boolean updateSysOfficeById(Map<String , Object> map)throws ServiceException;

    /**
     * 根据父ID获得子类记录的下一个ID
     * @param param
     * @return 
     * @version 1.0
     * @author 吴国成
     * @created 2014年12月25日
     */
    String getIdByParentId(String parentId) throws ServiceException;
    
    /**
     * 根据office获取子列表
     * @param param
     * @return 
     * @version 1.0
     * @author 吴国成
     * @created 2014年12月25日
     */
    List<SysOffice> getListByMap(Map<String, Object> param) throws ServiceException;

	/**
	 * 根据id获取sysOffice实体类对应bean属性和parent_id对应机构名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Map<String,Object> getOfficeInfoById(Long id) throws ServiceException;
	
	List<Map<String,Object>>  getOfficeTreeList() throws ServiceException;
	/**
     * 根据office获取子列表
     * @param param
     * @return 
     * @version 1.0
     * @author 吴国成
     * @created 2014年12月25日
     */
    List<SysOffice> getListByOfficeId(Map<String, Object> param) throws ServiceException;

	SysOffice getBusinessHallByOrganizationId(String officeId)throws ServiceException;

	Map<String, Object> getProjectBelongInfo(String string, Long userId);
}
