package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.common.domain.query.Pagination;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import  com.rongdu.eloan.modules.system.domain.SysDictDetail;

/**
 * Service
 * @author wgc
 * @version 1.0
 * @since 2014-11-03
 */
public interface SysDictDetailService {

    /**
     * 字典详情信息表删除
     * 
     * @param id 主键ID
     */
    Boolean deleteSysDictDetail(Long id) throws ServiceException ;
    
    Long getItemCountMap(Map<String, Object> arg) throws ServiceException;
    
    void addOrModify(SysDictDetail dictDetail,String stauts) throws ServiceException;
    
    List<Map<String,Object>> queryAllDic() throws ServiceException;
}
