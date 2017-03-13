package com.rongdu.eloan.modules.system.service;

import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysOfficeUserCount;


/**
 * 
 * 机构人数Service
 * @version 1.0
 * @author yaoyy
 * @created 2015-12-24
 */
public interface SysOfficeUserCountService {

   /**
    * 查询人数
    * @param id
    * @return
    * @throws ServiceException
    */
    SysOfficeUserCount getUserCountById(Long id) throws ServiceException;
    
    /**
     * 查询By机构ID
     * @param id
     * @return
     * @throws ServiceException
     */
    SysOfficeUserCount getNewUserCount() throws ServiceException;
    
    
    SysOfficeUserCount addUserCount(SysOfficeUserCount userCount) throws ServiceException;
    
    SysOfficeUserCount updateUserCount(SysOfficeUserCount userCount) throws ServiceException;
}
