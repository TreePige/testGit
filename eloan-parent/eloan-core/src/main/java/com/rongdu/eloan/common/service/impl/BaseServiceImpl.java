package com.rongdu.eloan.common.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.BaseService;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements
		BaseService<T, ID> {
	public int deleteByID(ID id) {
		return getMapper().deleteByPrimary(id);
	}

	public long insert(T record) {
		return getMapper().insert(record);
	}

	public T getById(ID id) {
		return getMapper().getByPrimary(id);
	}

	public int updateById(T record) {
		return getMapper().update(record);
	}

	public abstract BaseDao<T, ID> getMapper();

	@Autowired
	private BaseDao<T, ID> baseDao;
	
	protected String getLoginName() {
		// TODO; 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return user.getUsername();
	}
	

}
