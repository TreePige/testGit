package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.common.spring.security.authentication.encoding.PasswordEncoder;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysRoleDao;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.service.SysRoleService;

@SuppressWarnings("rawtypes")
@Service(value = "sysRoleServiceImpl")
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public List<SysRole> getRoleListByUserId(long userId) throws ServiceException{
		try {
			return this.sysRoleDao.getRoleListByUserId(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysRole getRoleById(long id) throws ServiceException {
		try {
			return this.sysRoleDao.getByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int deleteRole(long id) throws ServiceException {
		try {
			return this.sysRoleDao.deleteById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<? extends SysRole> getRolePageList(Map<String, Object> mapdata) throws ServiceException {
		try {
			return this.sysRoleDao.getRolePageList(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public long addRole(SysRole role) throws ServiceException {
		try {
			return this.sysRoleDao.insert(role);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public long insertByMap(Map<String, Object> data) throws ServiceException {
		try {
			return this.sysRoleDao.insertByMap(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int updateRole(Map<String, Object> arg) throws ServiceException {
		try {
			return this.sysRoleDao.updateByMap(arg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<SysRole> getListByMap(Map<String, Object> arg) throws ServiceException {
		try {
			return this.sysRoleDao.getListByMap(arg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public BaseDao getMapper() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getByUserPassRolesList(String username,
			String password) throws ServiceException {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("username", username);
		data.put("password", passwordEncoder.encodePassword(password, "rongdumlms"));

		try {
		  List<Map<String, Object>>  roles = sysRoleDao.getByUserPassRolesList(data);
		  
		  if(roles == null){
			  
			  throw new ServiceException(400,"获取角色数据失败");			  
		  }
		  return roles;
		  
		} catch (Exception e) {
			 throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int getRolecount(Map<String, Object> mapdata) throws ServiceException {
		try {
			return this.sysRoleDao.getRolecount(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
}
