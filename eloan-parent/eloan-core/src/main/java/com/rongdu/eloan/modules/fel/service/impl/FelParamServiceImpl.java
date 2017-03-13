package com.rongdu.eloan.modules.fel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.dao.FelParamDao;
import com.rongdu.eloan.modules.fel.domain.FelParam;
import com.rongdu.eloan.modules.fel.service.FelParamService;

/**
* Created with IntelliJ IDEA.
* User:    孙凯伦
* DateTime:2016-02-22 02:14:50
* details: 公式配置-------参数模块,Service实现层
* source:  代码生成器
*/
@SuppressWarnings("rawtypes")
@Service(value = "felParamServiceImpl")
public class FelParamServiceImpl extends BaseServiceImpl implements FelParamService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(FelParamServiceImpl.class);
    /**
	 * 公式配置-------参数模块dao层
	 */
    @Autowired
    private FelParamDao felparamDao;

	/**
	 * 公式配置-------参数模块表,插入数据
	 */
    @Override
	public long Insert(FelParam felparam) throws Exception {
		try {
			return felparamDao.insert(felparam);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置-------参数模块表,修改数据
	 */
    @Override
	public long Update(FelParam felparam) throws Exception {
		try {
			return felparamDao.update(felparam);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 公式配置,查询数据
	 */
    @Override
	public List<FelParam> getPageListByMap(Map<String, Object> map) throws Exception {
		try {
			//根据条件查询数据
			return felparamDao.getPageListByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	/**
	 * 公式配置,查询数据总数
	 */
    @Override
	public int getPageCountByMap(Map<String, Object> map)throws Exception{
		try {
			//根据条件查询数据总数
			return felparamDao.getPageCountByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
    /**
     * 
     * @description 子参数,公式查询
     * @return
     * @throws Exception
     * @author 孙凯伦
     * @return Map<String,Object>
     * @since  1.0.0
     */
    @Override
	public List<Map<String, Object>> formulaQuery() throws Exception {
		try {
			return commonly();
		} catch (Exception e) {
			//日志的异常打印处理
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
    
	/**
	 * 最外层类型分拣
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> commonly() throws Exception{
		//查询常用变量,条件
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("state", "0");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("state", "1");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("state", "2");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("state", "3");
		//给予前台页面的map参数
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		//存放参数的map
		for (int i = 0; i < 4; i++) {
			if(i == 0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", 0);
				map.put("label", "常用变量");
				map.put("children", sorting(felparamDao.getListByMap(map1)));
				res.add(map);
			}else if (i == 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", 1);
				map.put("label", "运算符");
				map.put("children", sorting(felparamDao.getListByMap(map2)));
				res.add(map);
			} else if (i == 2) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", 2);
				map.put("label", "数字");
				map.put("children", sorting(felparamDao.getListByMap(map3)));
				res.add(map);
			} else if (i == 3) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", 3);
				map.put("label", "类别");
				map.put("children", sorting(felparamDao.getListByMap(map4)));
				res.add(map);
			}
		}
		return res;
	}
	
    /**
     * 
     * @description 内层的参数,详细分拣
     * @param f
     * @return
     * @author 孙凯伦
     * @return List<Map<String,Object>>
     * @since  1.0.0
     */
	private List<Map<String, Object>> sorting (List<FelParam> f){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (FelParam p : f) {
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("label", p.getChineseName());
			map.put("value", p.getId());
			map.put("en", p.getEnglishName());
			map.put("state", p.getState());
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 
	 * @description 条件查询
	 * @return
	 * @throws ServiceException
	 * @author 孙凯伦
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	@Override
	public List<Map<String, Object>> conditionsQuery(FelParam felparam) throws ServiceException {
		//给予前台页面的map参数
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			res = conditions(felparam);
		} catch (PersistentDataException e) {
			//日志的异常打印处理
			log.error(e.getMessage(),e);
			throw new ServiceException(e);
		}
		return res;
	}
	/**
	 * 
	 * @description 分拣出的参数
	 * @param felparam
	 * @return
	 * @throws PersistentDataException
	 * @author 孙凯伦
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	private List<Map<String, Object>> conditions(FelParam felparam) throws PersistentDataException{
		//返回的list
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//默认只找参数
		felparam.setState(0);
		//查询出的结果
		List<FelParam> felparams = felparamDao.Select(felparam);
		//循环
		for (FelParam f : felparams) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", f.getId());
			map.put("text", f.getChineseName());
			list.add(map);
		}
		return list;
	}

	@Override
	public BaseDao getMapper() {
		return felparamDao;
	}
	
}