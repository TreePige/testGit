package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.domain.PageBean;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysDictDao;
import com.rongdu.eloan.modules.system.dao.SysDictDetailDao;
import com.rongdu.eloan.modules.system.domain.SysDict;
import com.rongdu.eloan.modules.system.service.SysDictService;

@Service(value = "sysDictService")
public class SysDictServiceImpl implements SysDictService {
	@Autowired
	private SysDictDao sysDictDao;
	@Autowired
	private SysDictDetailDao sysDictDetailDao;
	
	@Override
	public List<SysDict> getDictByTypeArr(String... typeArr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeArr", typeArr);
		return this.sysDictDao.getDictByTypeArr(map);
	}
	
/*	@Override
	public List<SysDict> getDictList(Map<String, Object> map) throws ServiceException {
		List<SysDict> list = null;
		try {
			int page = 2; //页号
			int pageSize = 2; //每页数据条数
			String sortString = "sort.desc";//排序
			PageBounds pageBounds = new PageBounds(page, pageSize,Order.formString(sortString));
			list = sysDictDao.getItemListByMap(map,pageBounds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}*/
	
	@Override
	public PageBean getDictListAndCount(Map<String, Object> searchMap,
			int currentPage, int pageSize) throws ServiceException {
		PageBean pageBean = new PageBean();
		try {
			//String sortString = "sort.desc";//排序
			PageHelper.startPage(currentPage, pageSize);
			List<SysDict> list = sysDictDao.getItemListByMap(searchMap);
			//获得结果集条总数  
			PageInfo<SysDict> page = new PageInfo<SysDict>(list);
			long count = page.getTotal();
			
			pageBean.setData(list);
			pageBean.setCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return pageBean;
	}

	@Override
	public Long getDictCount(Map<String, Object> arg) throws ServiceException {
		Long count = null;
		try {
			count = sysDictDao.getCount(arg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	
	@Override
	public long getDictDetailCount(Map<String, Object> data)
			throws ServiceException {
		Long count = null;
		try {
			count = sysDictDetailDao.getCount(data);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return count;
	}


	@Override
	public boolean addOrModify(SysDict sysDict, String status) throws ServiceException {
		long num = 0;
		boolean isTrue =false;
		try {
			if (status != null && Constant.INSERT.equals(status)) {
				num = sysDictDao.insert(sysDict);
			} else if (status != null && Constant.UPDATE.equals(status)) {
				num = sysDictDao.update(sysDict);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
        isTrue= num > 0L ?true:false;
		return isTrue;
	}

	@Override
	public boolean deleteDict(Long id) throws ServiceException {
		boolean flag =false;
		try {
			long num = sysDictDao.deleteById(id);
			if(num>0 ){
				flag=true;
			}
		} catch (Exception e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return flag;
	}

	@Override
	public PageBean getDictDetailList(Map<String, Object> data,
			int currentPage, int pageSize) throws ServiceException {
		PageBean pageBean = new PageBean();
		try {
			PageHelper.startPage(currentPage, pageSize);
			List<Map<String, Object>> listMap =  sysDictDetailDao.getPageListMap(data);
			
			//获得结果集条总数  
			PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(listMap);
			long count = page.getTotal();
			
			pageBean.setData(listMap);
			pageBean.setCount(count);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return pageBean;
	}
	
	@Override
	public List<Map<String, Object>> getDictsCache(String typeDict)
			throws ServiceException {
		try {
			return sysDictDao.getDictsCache(typeDict);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
}