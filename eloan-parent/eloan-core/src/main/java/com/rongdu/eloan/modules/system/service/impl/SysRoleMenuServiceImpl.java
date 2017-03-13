package com.rongdu.eloan.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysRoleMenuDao;
import com.rongdu.eloan.modules.system.domain.SysRoleMenu;
import com.rongdu.eloan.modules.system.service.SysRoleMenuService;

@Service(value = "sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException {
		try {
			return this.sysRoleMenuDao.getRoleMenuList(roleId);
		} catch (PersistentDataException e) {
			throw new ServiceException(Constant.FAIL_CODE_VALUE,
					"get data fail");
		}
	}

	public SysRoleMenuDao getSysRoleMenuDao() {
		return sysRoleMenuDao;
	}

	public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
		this.sysRoleMenuDao = sysRoleMenuDao;
	}
     
	@Override
	public List<String> getRoleMenuHrefList(Long roleId) throws Exception {
		
		return sysRoleMenuDao.getRoleMenuHrefList(roleId);
	}



}
