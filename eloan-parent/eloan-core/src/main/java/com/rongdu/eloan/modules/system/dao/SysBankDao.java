package com.rongdu.eloan.modules.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.utils.annotation.RDBatisDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.system.domain.SysBank;

/**
 * 公司银行账号表DAO接口
 * @author wgc
 * @version 1.0
 * @since 2015-01-22
 */
@RDBatisDao
@Repository("sysBankDao") 
public interface SysBankDao extends BaseDao<SysBank, Long> {
        
    /**
     * 获取总记录数
     * @param param
     * @return 记录数
     * @throws PersistentDataException
     */
    int getPageCountByMap(Map<String, Object> data);

	List<SysBank> getPageListByMap(Map<String, Object> map);

	int deleteById(long id);
}
