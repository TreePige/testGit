package com.rongdu.eloan.modules.paramconfig.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.context.FelConstant;
import com.rongdu.eloan.modules.fel.context.FelTypeEnum;
import com.rongdu.eloan.modules.fel.dao.FelFormulaDao;
import com.rongdu.eloan.modules.fel.dao.FelParamDao;
import com.rongdu.eloan.modules.fel.dao.FelTypeDao;
import com.rongdu.eloan.modules.fel.domain.FelFormula;
import com.rongdu.eloan.modules.fel.domain.FelParam;
import com.rongdu.eloan.modules.fel.domain.FelType;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.paramconfig.dao.ProductParamDao;
import com.rongdu.eloan.modules.paramconfig.dao.ProductTypeDao;
import com.rongdu.eloan.modules.paramconfig.domain.ProductParam;
import com.rongdu.eloan.modules.paramconfig.domain.ProductType;
import com.rongdu.eloan.modules.paramconfig.service.ProductParamService;

/**
* User:    wangmc
* DateTime:2016-07-13 03:59:04
* details: 产品类型表,Service实现层
* source:  代码生成器
*/
@Service(value = "productParamService")
public class ProductParamServiceImpl extends BaseServiceImpl implements ProductParamService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(ProductParamServiceImpl.class);
    /**
	 * 产品类型表dao层
	 */
    @Autowired
    private ProductParamDao productParamDao;
	
    @Autowired
    private ProductTypeDao productTypeDao;
    
    @Autowired 
    private FelFormulaDao  felFormulaDao;
    
    @Autowired
    private FelTypeDao felTypeDao;
    
    
   @Autowired
   private FelParamDao felParamDao;
	/**
	*
	*继承关系
	*/
	@Override
	public BaseDao getMapper() {
		// TODO Auto-generated method stub
		return productParamDao;
	}
	
	/**
	 * 产品类型表表,插入数据
	 * @param collateral 产品类型表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public long insert(ProductParam productParam) throws Exception {
		try {
			return	productParamDao.insert(productParam);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	
	
	/**
	* 产品类型表表,修改数据
	* @param collateral 产品类型表类
	* @return           返回页面map
	* @throws ServiceException
	*/
	public long update(ProductParam productParam) throws Exception {
		try {
			return	productParamDao.update(productParam);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	/**
	 * 产品类型表表,查询数据
	 * @param productParam 产品类型表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public List<ProductParam> getProductParamList(Map<String, Object> map,PageBounds pageBounds) throws Exception {
	
		try {
		
			return	productParamDao.select(map,pageBounds);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
		//返回已经查询完毕的参数
	}
	
	/**
	 * 产品类型表表,查询数据
	 * @param map类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			return productParamDao.total(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	
	
	/**
	 * 验证公式的方法
	 */
	@Override
	public Map<String, Object> inspect(Map<String, Object> param) throws ServiceException {
		try {
			Map<String, Object> res = new HashMap<String, Object>();
			
			if (param.get("id") != null) {
				Long id = Long.valueOf(param.get("id").toString());
				List<FelFormula> fels = queryByProductId(id);//获取产品对应的公式列表
				
				for (FelFormula felformula : fels) {
					FelType type = new FelType();
					type.setId(felformula.getTypeId());
					type = felTypeDao.SelectFeltype(type); //获取公式类型
					
					if (FelTypeEnum.OVERDUE_PENALTY.value().equals(type.getEnglishName())) {
						if (param.get("overdueDate")==null || "0".equals((String)param.get("overdueDate"))) {
							res.put(type.getChineseName(), 0);
							continue;
						}
					}
					res.put(type.getChineseName(), this.calculateByFelId(param, felformula.getId())); //每项公式带入计算
				}
			} else {
				throw new ServiceException("缺少产品Id");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	 	/**
		 * 查询某个产品相关的所有公式
		 */
		 @Override
		 public List<FelFormula> queryByProductId(Long id) throws ServiceException
		 {
		 try {
		 ProductParam product = productParamDao.getProductById(id); //获取产品
		 
		 ProductType type = new ProductType();
		 type.setId(product.getProductType());  
		 type = productTypeDao.getProductTypeItem(type);  //获取产品类型
		 
		 List<FelFormula> fels = felFormulaDao.selectFormulaById(type.getFormulaId());  //获取产品类型对应的公式
		
		 return fels;
		 } catch (Exception e) {
		 e.printStackTrace();
		 throw new ServiceException(e);
		 }
		 }
		 
		 /**
			 * 
			 * @description 计算某个公式的值，参数由外界提供
			 * @param param 输入的参数
			 * @param id 公式id
			 * @return
			 * @throws ServiceException
			 * @author liyc
			 * @return BigDecimal
			 * @since  1.0.0
			 */
			public BigDecimal calculateByFelId(Map<String, Object> param, long id)
					throws ServiceException {
				try {
					FelFormula fel = getFelformula(id);  //获得公式
					FelType type = new FelType();
					type.setId(fel.getTypeId());
					type = felTypeDao.SelectFeltype(type); //获得公式类型    //
					return this.calculateByTypeAndPType(type.getEnglishName(), Long.valueOf(param.get("productType").toString()),  param);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException(e);
				}
			}
			
			/**
			 * 
			 * @description 根据公式id,公式级内容
			 * @param felformulaId
			 * @return
			 * @throws PersistentDataException
			 * @author 孙凯伦
			 * @return List<Felformula>
			 * @since  1.0.0
			 */
			private FelFormula getFelformula(long formulaId){
				FelFormula f = new FelFormula();
				f.setId(formulaId);
				try {
					return felFormulaDao.SelectFelformula(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			
			/**
			 * 
			 * @description 计算某产品类型下的某个类型的公式
			 * @param felType
			 * @param pType 产品类型
			 * @return
			 * @throws ServiceException
			 * @author liyc
			 * @return BigDecimal
			 * @since  1.0.0
			 */
			public BigDecimal calculateByTypeAndPType(String felTypeEnName, Long pTypeId, Map<String, Object> param) throws Exception {
				try {
					FelEngine felEngine = new FelEngineImpl();
					FelContext context = felEngine.getContext();
					
					//查找产品类型
					ProductType productType = new ProductType();
					productType.setId(pTypeId);
					productType = productTypeDao.getProductTypeItem(productType);
					
					//查找公式类型
					FelType type = felTypeDao.selectByEnName(felTypeEnName);
					Map<String, Object> map=new HashMap<String, Object>();
							map.put("id", productType.getFormulaId());
							map.put("typeId", type.getId());
					FelFormula fel = felFormulaDao.SelectFelformulaByTypeAndId(map);
//							felFormulaDao.SelectFelformulaByTypeAndId(
//							productType.getFormulaId(), Long.valueOf(type.getId()));
					
					List<FelParam> felParams = getFelparam(fel.getParamId());
					
					for (FelParam felparam : felParams) {
						if (FelConstant.DATA_SOURCE_FEL.equals(felparam.getDataSource())) {
							BigDecimal res = this.calculateByTypeAndPType(felparam.getEnglishName(), pTypeId, param);
							context.set(felparam.getEnglishName(), res);
							System.err.println(felparam.getChineseName() + ":" + res);
						} else {
							throwIfNullException(param.get(felparam.getEnglishName()), "公式参数:" + felparam.getChineseName());
							context.set(felparam.getEnglishName(), new BigDecimal(param.get(felparam.getEnglishName()).toString()));
							System.err.println(felparam.getChineseName() + ":" + new BigDecimal(param.get(felparam.getEnglishName()).toString()));
						}
					}
					
					return new BigDecimal(felEngine.eval(fel.getFormula()).toString()).setScale(FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
				} catch (PersistentDataException e) {
					e.printStackTrace();
					throw new ServiceException(e);
				}
			}
			


			/**
			 * 
			 * @description 根据参数级id,查询出公式对于参数
			 * @param paramId
			 * @return
			 * @throws PersistentDataException
			 * @author 孙凯伦
			 * @return Felparam
			 * @since  1.0.0
			 */
			private List<FelParam> getFelparam(String paramId){
			//	FelParam p = new FelParam();
			//	p.setId(Long.valueOf(paramId));
				try {
					return felParamDao.selectParamById(paramId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			public void throwIfNullException(Object obj, String msg) throws ServiceException{
				if (null == obj) {
					throw new ServiceException("没有找到该" + msg);
				}
			}

			@Override
			public List<Map<String, Object>> getOfficeCombo(
					Map<String, Object> map) throws Exception {
				// TODO Auto-generated method stub
				try {
					return productParamDao.getOfficeCombo(map);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
				}
			}
			 
}