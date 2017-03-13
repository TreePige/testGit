package com.rongdu.eloan.modules.system.service;

import java.util.List;
import java.util.Map;

import com.rongdu.eloan.modules.system.domain.SysBank;

/**
 * 公司银行账号表Service
 * @author wgc
 * @version 1.0
 * @since 2015-01-22
 */
public interface SysBankService {

    /**
     * 添加公司银行账号表
     * 
     * @param sysBank 公司银行账号表实体
     */
	long insert(SysBank sysBank) throws Exception;

    /**
     * 公司银行账号表修改
     * @param sysBank 公司银行账号表实体
     */
    long update(SysBank sysBank) throws Exception;
    
    /**
     * 公司银行账号表分页
     * @param data
     * @return
     * @throws Exception
     */
    List<SysBank> getPageListByMap(Map<String , Object> data) throws Exception;

    /**
     * 获取总记录数
     * @param data
     * @return
     * @throws Exception
     */
    int getPageCountByMap(Map<String, Object> data) throws Exception;
    
    int deleteById(long id) throws Exception;
}
