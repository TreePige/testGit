package com.rongdu.eloan.modules.fel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.common.utils.ObjectTool;
import com.rongdu.eloan.modules.common.dao.PlProductDao;
import com.rongdu.eloan.modules.common.domain.PlProduct;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.dao.FelFormulaDao;
import com.rongdu.eloan.modules.fel.dao.FelproductDao;
import com.rongdu.eloan.modules.fel.domain.FelFormula;
import com.rongdu.eloan.modules.fel.domain.Felproduct;
import com.rongdu.eloan.modules.fel.service.FelFormulaService;

/**
 * Created with IntelliJ IDEA. User: 孙凯伦 DateTime:2016-02-22 03:17:39 details:
 * 公式配置,公式模块,Service实现层 source: 代码生成器
 */
@SuppressWarnings("rawtypes")
@Service(value = "felFormulaServiceImpl")
public class FelFormulaServiceImpl extends BaseServiceImpl implements FelFormulaService {
	@Autowired
	private FelFormulaDao felFormulaDao;

//	@Autowired
	private FelproductDao felproductDao;

//	@Autowired
	 private PlProductDao plProductDao;

	// private final String productNameSpace = "PlProduct";

	/**
	 * 公式配置,公式模块表,插入数据
	 */
	@Override
	public long Insert(FelFormula felformula, List<Map<String, Object>> list) throws Exception {
		try {
			felformula.setParamId(ParamId(list));
			felformula.setFormula(formula(list));
			felformula.setFormulaChinese(formula(list, "label"));
			return felFormulaDao.insert(felformula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置,公式模块表,修改数据
	 * 
	 * @param collateral
	 *            公式配置,公式模块类
	 * @return 返回页面map
	 * @throws ServiceException
	 */
	@Override
	public long Update(FelFormula felformula, List<Map<String, Object>> list) throws Exception {
		try {
			felformula.setParamId(ParamId(list));
			felformula.setFormula(formula(list));
			felformula.setFormulaChinese(formula(list, "label"));
			// 传回修改是否成功
			return felFormulaDao.update(felformula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置,查询数据
	 */
	@Override
	public List<FelFormula> getPageListByMap(Map<String, Object> map) throws Exception {
		try {
			// 根据条件查询数据
			return felFormulaDao.getPageListByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 公式配置,查询数据总数
	 */
	@Override
	public int getPageCountByMap(Map<String, Object> map) throws Exception {
		try {
			// 根据条件查询数据总数
			return felFormulaDao.getPageCountByMap(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 
	 * @description 公式配置,公式模块表,删除数据
	 * @param id
	 * @return
	 * @author 孙凯伦
	 * @return Map<String,Object>
	 * @since 1.0.0
	 */
	@Override
	public long Delete(Integer id) throws Exception {
		try {
			// 根据条件查询数据总数
			return felFormulaDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	/**
	 * 
	 * @description 分拣出常用参数的id
	 * @param list
	 * @return
	 * @author 孙凯伦
	 * @return String
	 * @since 1.0.0
	 */
	private String ParamId(List<Map<String, Object>> list) {
		String tags = "";
		for (Map<String, Object> map : list) {
			if (ObjectTool.Integer((map.get("isOperator"))) == 0) {
				if (tags == "") {
					tags += ObjectTool.String((map.get("value")));
				} else {
					tags += "," + ObjectTool.String((map.get("value")));
				}
			}
		}
		return tags;
	}

	/**
	 * 
	 * @description 拼接处公式
	 * @param list
	 * @return
	 * @author 孙凯伦
	 * @return String
	 * @since 1.0.0
	 */
	private String formula(List<Map<String, Object>> list) {
		String formula = "";
		for (Map<String, Object> map : list) {
			formula += ObjectTool.String((map.get("en")));
		}
		return formula;
	}

	/**
	 * 
	 * @description 可选字段
	 * @param list
	 * @param name
	 * @return
	 * @author liyc
	 * @return String
	 * @since 1.0.0
	 */
	private String formula(List<Map<String, Object>> list, String name) {
		String formula = "";
		for (Map<String, Object> map : list) {
			formula += ObjectTool.String((map.get(name)));
		}
		return formula;
	}

	@Override
	public BaseDao getMapper() {
		return felFormulaDao;
	}

	 /**
	 * 查询某个产品相关的所有公式
	 */
	 @Override
	 public List<FelFormula> queryByProductId(Long id) throws ServiceException{
		 try {
		 PlProduct product = plProductDao.getItemInfoById(id);
		
		 Felproduct type = new Felproduct();
		 type.setId(product.getProductType().toString());
		 type = felproductDao.SelectFelproduct(type);
		
		 FelFormula fel = new FelFormula();
		 fel.setId(Long.valueOf(type.getFormulaId()));
		 List<FelFormula> fels = felFormulaDao.Select(fel);
		
		 return fels;
		 } catch (PersistentDataException e) {
		 e.printStackTrace();
		 throw new ServiceException(e);
		 }
	 }
}