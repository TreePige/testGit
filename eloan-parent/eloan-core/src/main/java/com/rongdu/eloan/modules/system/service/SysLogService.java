package com.rongdu.eloan.modules.system.service;

import java.util.Map;

import com.rongdu.eloan.common.domain.query.Pagination;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysLog;


/**
 * 
 * 系统操作日志Service
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午10:02:25
 */
public interface SysLogService {

    /**
     * 查询系统操作日志
     * 
     * @param id 主键ID
     * @return 系统操作日志
     * @throws ServiceException 
     */
    SysLog getLogById(long id) throws ServiceException;
    
    /**
     * 系统操作日志分页
     * @param map 查询参数
     * @param page 分页信息
     * @return 分页
     * @throws ServiceException 
     */
    Pagination getLogPageList(Pagination page, Map<String, Object> map) throws ServiceException;
    
    /**
     * 添加日志
     * @param log 日志信息
     * @throws ServiceException 
     */
    void addLog(SysLog log) throws ServiceException;
}
