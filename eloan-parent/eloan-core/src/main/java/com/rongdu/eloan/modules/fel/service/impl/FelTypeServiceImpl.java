package com.rongdu.eloan.modules.fel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.fel.dao.FelTypeDao;
import com.rongdu.eloan.modules.fel.domain.FelType;
import com.rongdu.eloan.modules.fel.service.FelTypeService;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 03:28:56
* details: 公式配置,类型模块,Service实现层
* source:  代码生成器
*/
@SuppressWarnings("rawtypes")
@Service(value = "felTypeServiceImpl")
public class FelTypeServiceImpl extends BaseServiceImpl implements FelTypeService {
    /**
	 * 公式配置,类型模块dao层
	 */
    @Autowired
    private FelTypeDao felTypeDao;

	/**
	 * 公式配置,类型模块表,插入数据
	 */
	public long Insert(FelType feltype) throws Exception {
		try {
			return felTypeDao.insert(feltype);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置,类型模块表,修改数据
	 */
	public long Update(FelType feltype) throws Exception {
		try {
			return felTypeDao.update(feltype);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置,查询数据
	 */
	public List<FelType> getPageListByMap(Map<String, Object> map) throws Exception {
		try {
			//根据条件查询数据
			return felTypeDao.getPageListByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 公式配置,查询数据总数
	 */
	public int getPageCountByMap(Map<String, Object> map) throws Exception{
		try {
			//根据条件查询数据总数
			return felTypeDao.getPageCountByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 公式配置,类型模块表,全部数据
	 */
	public List<Map<String, Object>> getListByMap(Map<String, Object> map) throws Exception{
		try {
			//根据条件查询数据
			return sorting(felTypeDao.getListByMap(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	/**
	 * 
	 * @description 分拣内容
	 * @param f
	 * @return
	 * @author 孙凯伦
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	private List<Map<String, Object>> sorting(List<FelType> f){
		//返回的list
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//分拣内容
		for (FelType t : f) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", t.getChineseName());
			map.put("value", t.getId());
			list.add(map);
		}
		return list;
	}

	@Override
	public BaseDao getMapper() {
		return felTypeDao;
	}
}