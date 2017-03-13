package com.rongdu.eloan.modules.fel.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.itextpdf.text.log.SysoCounter;
import com.rongdu.eloan.common.utils.ObjectTool;
import com.rongdu.eloan.common.utils.fel.Formula;
import com.rongdu.eloan.modules.common.dao.PlFeeinfoDao;
import com.rongdu.eloan.modules.common.dao.PlProductDao;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.fel.context.FelConstant;
import com.rongdu.eloan.modules.fel.context.FelTypeEnum;
import com.rongdu.eloan.modules.fel.dao.FelFormulaDao;
import com.rongdu.eloan.modules.fel.dao.FelParamDao;
import com.rongdu.eloan.modules.fel.dao.FelTypeDao;
import com.rongdu.eloan.modules.fel.dao.FelproductDao;
import com.rongdu.eloan.modules.fel.domain.FelFormula;
import com.rongdu.eloan.modules.fel.domain.FelParam;
import com.rongdu.eloan.modules.fel.domain.FelType;
import com.rongdu.eloan.modules.fel.domain.Felproduct;
import com.rongdu.eloan.modules.fel.service.FelFormulaService;
import com.rongdu.eloan.modules.fel.service.FelService;

/**
 * 
 *	@Description 公式配置器,调用方法
 *  @author 孙凯伦,skl@erongdu.com
 *  @CreatTime 2016年2月22日 下午4:32:19
 *  @since version 1.0.0
 */
@Service(value = "felService")
public class FelServiceImpl implements FelService{
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(FelFormulaServiceImpl.class);
    /**
	 * 公式配置,公式模块dao层
	 */
    @Autowired
    private FelFormulaDao felFormulaDao;
    
    @Autowired
    private FelFormulaService felFormulaService;
    
    /**
	 * 公式配置-------参数模块dao层
	 */
    @Autowired
    private FelParamDao felparamDao;
    /**
	 * 公式配置,产品模块dao层
	 */
    @Autowired
    private FelproductDao felproductDao;
    /**
	 * 公式配置,类型模块dao层
	 */
    @Autowired
    private FelTypeDao feltypeDao;
    
    @Autowired
    private PlProductDao plProductDao;
    
    private final String productNameSpace = "PlProduct";
    
    @Autowired
    private PlFeeinfoDao plFeeinfoDao;
	/**
	 * 
	 * @description 公式配置器,产品级开始,计算方法
	 * @param productType
	 * @param type
	 * @param param
	 * @return
	 * @author 孙凯伦
	 * @return String
	 * @since  1.0.0
	 */
	public String calculate(String productType,String type,Map<String, Object> param){
		//公式配置,产品级
		Felproduct pr = getFelproduct(productType);
		//公式配置,类型级
		FelType t = getFeltype(type);
		//公式配置,公式级
		FelFormula f = getFelformula(pr.getFormulaId(),Long.valueOf(t.getId()));
		//公式配置,参数级
		List<FelParam> p = getFelparam(f.getParamId());
		//参数级存放的map
		Map<String, Object> map = new HashMap<String, Object>();
		for (FelParam fp : p) {
			map.put(fp.getChineseName(), fp.getChineseName());
		}
		//计算结果
		Formula formula = Formula.getInstance();
		return ObjectTool.String(formula.calculate(map, param,f.getFormula())); 
	}
	/**
	 * 
	 * @description 公式配置器,公式级开始,计算方法
	 * @param formulaId
	 * @param param
	 * @return
	 * @author 孙凯伦
	 * @return String
	 * @since  1.0.0
	 */
	public BigDecimal calculate(String formulaId,Map<String, Object> param) throws ServiceException {
		return this.calculate(formulaId, param, FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
	}
	
	@Override
	public BigDecimal calculate(String formulaId, Map<String, Object> param, int newScale, RoundingMode roundingMode) throws ServiceException {
		Map<String, Object> paramLog = new HashMap<String, Object>();
		FelEngine felEngine = new RDFelEngineImpl();
		FelContext felContext = felEngine.getContext();
		
		//根据 公式类型和 产品信息中的产品类型 查找 具体公式
		FelFormula select = new FelFormula();
		select.setId(Long.valueOf(formulaId));
		FelFormula fel = felFormulaDao.SelectFelformula(select);
		throwIfNullException(fel, "公式  id:");
		System.err.println("start caculate:" + fel.getChineseName());
		log.debug("start caculate:" + fel.getChineseName());
		
		//组装参数 根据公式中的参数英文名，从产品中读取，以key：金额，value：1000 的形式 ，存入公式计算上下文
		List<FelParam> p = felparamDao.selectParamById(fel.getParamId());
		throwIfNullException(p, "公式参数 id:" + fel.getParamId());
		for (FelParam felparam : p) {
//			if ("account".equals(felparam.getEnglishName().trim())) {
//				felContext.set(felparam.getEnglishName(), amount);
//				paramLog.put(felparam.getEnglishName(), amount);
//				continue;
//			} 
			
//			if ("公式".equals(felparam.getCreateName())) {
//				if (felType.equals(felparam.getEnglishName())) {
//					throw new ServiceException("循环调用 公式  id:" + productType.getFormulaId());
//				}
//				BigDecimal resTemp = this.calculateUseFeeInfo(procInsId, amount, felparam.getEnglishName(), extraParam, newScale, roundingMode);
//				felContext.set(felparam.getEnglishName(), resTemp);
//				paramLog.put(felparam.getEnglishName(), resTemp);
//				continue;
//			}
			
			if (param != null && param.get(felparam.getEnglishName()) != null) {
				felContext.set(felparam.getEnglishName(), param.get(felparam.getEnglishName()));
				paramLog.put(felparam.getEnglishName(), param.get(felparam.getEnglishName()));
				continue;
			}
			
			if (param.get(felparam.getEnglishName()) != null) {
				felContext.set(felparam.getEnglishName(), param.get(felparam.getEnglishName()));
				paramLog.put(felparam.getEnglishName(), param.get(felparam.getEnglishName()));
				continue;
			}
			
			throw new ServiceException("找不到该" + felparam.getEnglishName() + "参数的值");
		}
		log.debug("params:" + paramLog.toString());
		log.debug("fel:" + fel.getFormula());
		BigDecimal res = new BigDecimal(felEngine.eval(fel.getFormula()).toString());
		log.debug("res:" + res.toString());
		//fel.jar 调用jdk环境计算导致存在精度问题，现先向下取整截取小数点后6位再作进位
//		res = res.setScale(6, RoundingMode.DOWN).setScale(newScale, roundingMode);
		res.setScale(newScale, roundingMode);
		log.debug("rouding:" + res.toString());
		log.debug("end caculate:" + fel.getChineseName());
		System.err.println("end caculate:" + fel.getChineseName());
		//计算并返回值
		return res;
	}
	
	/**
	 * 
	 * @description 更具产品名称查询出,该产品对于公式
	 * @param productType
	 * @return
	 * @throws PersistentDataException
	 * @author 孙凯伦
	 * @return Felproduct
	 * @since  1.0.0
	 */
	private Felproduct getFelproduct(String productType){
		Felproduct f = new Felproduct();
		f.setProductType(productType);
		try {
			return felproductDao.SelectFelproduct(f);
		} catch (PersistentDataException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @description 根据公式id和类型id查询出,公式级内容
	 * @param felformulaId
	 * @return
	 * @throws PersistentDataException
	 * @author 孙凯伦
	 * @return List<Felformula>
	 * @since  1.0.0
	 */
	private FelFormula getFelformula(String formulaId,Long typeId){
		FelFormula f = new FelFormula();
		f.setId(Long.valueOf(formulaId));
		f.setTypeId(Integer.valueOf(String.valueOf(typeId)));
		try {
			return felFormulaDao.SelectFelformula(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	private FelFormula getFelformula(Long formulaId){
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
	 * @description 根据中文名,查询出类型id
	 * @param type
	 * @return
	 * @throws PersistentDataException
	 * @author 孙凯伦
	 * @return Feltype
	 * @since  1.0.0
	 */
	private FelType getFeltype(String type){
		FelType t = new FelType();
		t.setChineseName(type);
		try {
			return feltypeDao.SelectFeltype(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		FelParam p = new FelParam();
		p.setId(Long.valueOf(paramId));
		try {
			return felparamDao.Select(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @description 根据产品id 借款金额 公式类型计算对应的值
	 * @param formulaId
	 * @param param
	 * @return
	 * @author liyc
	 * @return String
	 * @since  1.0.0
	 */
	@Override
	public BigDecimal calculate(long productId, BigDecimal amount, String felType) throws ServiceException {
		return this.calculate(productId, amount, felType, null, FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
	}
	
	public void throwIfNullException(Object obj, String msg) throws ServiceException{
		if (null == obj) {
			throw new ServiceException("没有找到该" + msg);
		}
	}
	
	/**
	 * 
	 * @description 计算带保留小数
	 * @param productId
	 * @param amount
	 * @param felType
	 * @param newScale
	 * @param roundingMode
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	@Override
	public BigDecimal calculate(long productId, BigDecimal amount, String felType, Map<String, Object> extraParam, 
			int newScale, RoundingMode roundingMode)
			throws ServiceException {
		try {
			FelEngine felEngine = new RDFelEngineImpl();
			FelContext felContext = felEngine.getContext();
			
			//查询公式类型
			FelType typeQuery = new FelType();
			typeQuery.setEnglishName(felType);
			FelType type = feltypeDao.SelectFeltype(typeQuery);
			
			//查询相关产品信息
			Map<String, Object> product = plProductDao.getItemMapById(productId);
			throwIfNullException(product, "产品 id:" + productId);
			
			//查询产品类型
			Felproduct sel = new Felproduct();
			sel.setId(product.get("product_type").toString());
			Felproduct felproduct = felproductDao.SelectFelproduct(sel);
			throwIfNullException(felproduct, "产品类型 id:" + product.get("product_type"));
			
			//根据 公式类型和 产品信息中的产品类型 查找 具体公式
			FelFormula felformula = new FelFormula();
			felformula.setId(Long.valueOf(felproduct.getFormulaId()));
			felformula.setTypeId(((Long)type.getId()).intValue());
			FelFormula fel = felFormulaDao.SelectFelformula(felformula);
			throwIfNullException(fel, "公式  id:" + felproduct.getFormulaId());
			
			//组装参数 根据公式中的参数英文名，从产品中读取，以key：金额，value：1000 的形式 ，存入公式计算上下文
			List<FelParam> p = getFelparam(fel.getParamId());
			throwIfNullException(p, "公式参数 id:" + fel.getParamId());
			for (FelParam felparam : p) {
				if ("account".equals(felparam.getEnglishName().trim())) {
					felContext.set(felparam.getEnglishName(), amount);
					continue;
				} 
				
				if ("公式".equals(felparam.getCreateName())) {
					if (felType.equals(felparam.getEnglishName())) {
						throw new ServiceException("循环调用 公式  id:" + felproduct.getFormulaId());
					}
					felContext.set(felparam.getEnglishName(), this.calculate(productId, amount, felparam.getEnglishName(), extraParam, newScale, roundingMode));
					continue;
				}
				
				if (null == product.get(felparam.getEnglishName())) {
					//产品中取不到参数时 去额外传入参数取
					throwIfNullException(extraParam.get(felparam.getEnglishName()), "公式参数:" + felparam.getEnglishName());
					felContext.set(felparam.getEnglishName(), extraParam.get(felparam.getEnglishName()));
					continue;
				}
				
				felContext.set(felparam.getEnglishName(), product.get(felparam.getEnglishName()));
			}
			
			//计算并返回值
			return new BigDecimal(felEngine.eval(fel.getFormula()).toString()).setScale(newScale, roundingMode);
		} catch (PersistentDataException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 
	 * @description 根据产品id 借款金额 公式类型计算对应的值 默认保留两位小数 可传入额外参数
	 * @param productId
	 * @param amount
	 * @param felType
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	@Override
	public BigDecimal calculate(long productId, BigDecimal amount, String felType, Map<String, Object> extraParam) 
			throws ServiceException {
		return this.calculate(productId, amount, felType, extraParam, FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
	}

	/**
	 * 
	 */
	@Override
	public Map<String, Object> inspect(Map<String, Object> param)
			throws ServiceException {
		try {
			Map<String, Object> res = new HashMap<String, Object>();
			if ((Integer) param.get("id") != null) {
				Long id = Long.valueOf(param.get("id").toString());
				List<FelFormula> fels = felFormulaService.queryByProductId(id);
				for (FelFormula felformula : fels) {
					FelType type = new FelType();
					type.setId(Long.valueOf(felformula.getTypeId().toString()));
					type = feltypeDao.SelectFeltype(type);
					
					if (FelTypeEnum.OVERDUE_PENALTY.value().equals(type.getEnglishName())) {
						if (param.get("overdue_date")==null || "0".equals((String)param.get("overdue_date"))) {
							res.put(type.getChineseName(), 0);
							continue;
						}
					}
					
					res.put(type.getChineseName(), this.calculateByFelId(param, String.valueOf(felformula.getId())));
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
	 * 
	 * @description 计算某个公式的值，参数由外界提供
	 * @param param
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	@Override
	public BigDecimal calculateByFelId(Map<String, Object> param, String id)
			throws ServiceException {
		try {
			FelFormula fel = getFelformula(Long.valueOf(id));
			FelType type = new FelType();
			type.setId(Long.valueOf(fel.getTypeId().toString()));
			type = feltypeDao.SelectFeltype(type);
			return this.calculateByTypeAndPType(type.getEnglishName(), Long.valueOf(param.get("product_type").toString()),  param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 
	 * @description 计算某产品类型下的某个类型的公式
	 * @param felType
	 * @param pType
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	public BigDecimal calculateByTypeAndPType(String felTypeEnName, Long pTypeId, Map<String, Object> param) throws ServiceException {
		try {
			FelEngine felEngine = new RDFelEngineImpl();
			FelContext context = felEngine.getContext();
			
			//查找产品类型
			Felproduct productType = new Felproduct();
			productType.setId(pTypeId.toString());
			productType = felproductDao.SelectFelproduct(productType);
			
			//查找公式类型
			FelType type = feltypeDao.selectByEnName(felTypeEnName);
			
			FelFormula fel = felFormulaDao.SelectFelformulaByTypeAndId(
					productType.getFormulaId(), Long.valueOf(type.getId()));
			
			List<FelParam> felParams = getFelparam(fel.getParamId());
			
			for (FelParam felparam : felParams) {
				if (FelConstant.DATA_SOURCE_FEL.equals(felparam.getDataSource())) {
					context.set(felparam.getEnglishName(), this.calculateByTypeAndPType(felparam.getEnglishName(), pTypeId, param));
				} else {
					throwIfNullException(param.get(felparam.getEnglishName()), "公式参数:" + felparam.getChineseName());
					context.set(felparam.getEnglishName(), new BigDecimal(param.get(felparam.getEnglishName()).toString()));
				}
			}
			
			return new BigDecimal(felEngine.eval(fel.getFormula()).toString()).setScale(FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
		} catch (PersistentDataException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	private BigDecimal calculateUseFeeInfo(String procInsId, BigDecimal amount,
			String felType, Map<String, Object> extraParam, int newScale,
			RoundingMode roundingMode) throws ServiceException {
		try {
			Map<String, Object> paramLog = new HashMap<String, Object>();
			FelEngine felEngine = new RDFelEngineImpl();
			FelContext felContext = felEngine.getContext();
			
			//查询公式类型
			FelType typeQuery = new FelType();
			typeQuery.setEnglishName(felType);
			FelType type = feltypeDao.SelectFeltype(typeQuery);
			
			//查询相关feeinfo信息
			Map<String, Object> feeinfo = plFeeinfoDao.getItemMapByProcInsId(procInsId);//todo
			throwIfNullException(feeinfo, "feeinfo ProcInsId:" + procInsId + "公式类型:" + felType);
			
			//查询产品类型
			Felproduct sel = new Felproduct();
			sel.setId(feeinfo.get("product_type").toString());
			Felproduct felproduct = felproductDao.SelectFelproduct(sel);
			throwIfNullException(felproduct, "产品类型 id:" + feeinfo.get("product_type")+ "公式类型:" + felType);
			
			//根据 公式类型和 产品信息中的产品类型 查找 具体公式
			FelFormula felformula = new FelFormula();
			felformula.setId(Long.valueOf(felproduct.getFormulaId()));
			felformula.setTypeId(((Long)type.getId()).intValue());
			FelFormula fel = felFormulaDao.SelectFelformula(felformula);
			throwIfNullException(fel, "公式  id:" + felproduct.getFormulaId() + "产品类型id:" + type.getId() + "公式类型:" + felType);
			System.err.println("start caculate:" + fel.getChineseName());
			log.debug("start caculate:" + fel.getChineseName());
			
			//组装参数 根据公式中的参数英文名，从产品中读取，以key：金额，value：1000 的形式 ，存入公式计算上下文
			List<FelParam> p = getFelparam(fel.getParamId());
			throwIfNullException(p, "公式参数 id:" + fel.getParamId() + "公式类型:" + felType);
			for (FelParam felparam : p) {
				if ("account".equals(felparam.getEnglishName().trim())) {
					felContext.set(felparam.getEnglishName(), amount);
					paramLog.put(felparam.getEnglishName(), amount);
					continue;
				} 
				
				if ("公式".equals(felparam.getCreateName())) {
					if (felType.equals(felparam.getEnglishName())) {
						throw new ServiceException("循环调用 公式  id:" + felproduct.getFormulaId());
					}
					BigDecimal resTemp = this.calculateUseFeeInfo(procInsId, amount, felparam.getEnglishName(), extraParam, newScale, roundingMode);
					felContext.set(felparam.getEnglishName(), resTemp);
					paramLog.put(felparam.getEnglishName(), resTemp);
					continue;
				}
				
				if (null == feeinfo.get(felparam.getEnglishName())) {
					//产品中取不到参数时 去额外传入参数取
					throwIfNullException(extraParam.get(felparam.getEnglishName()), "公式参数:" + felparam.getEnglishName());
					felContext.set(felparam.getEnglishName(), extraParam.get(felparam.getEnglishName()));
					paramLog.put(felparam.getEnglishName(), extraParam.get(felparam.getEnglishName()));
					continue;
				}
				
				felContext.set(felparam.getEnglishName(), feeinfo.get(felparam.getEnglishName()));
				paramLog.put(felparam.getEnglishName(), feeinfo.get(felparam.getEnglishName()));
			}
			BigDecimal res = new BigDecimal(felEngine.eval(fel.getFormula()).toString());
			log.debug("params:" + paramLog.toString());
			log.debug("fel:" + fel.getFormula());
			log.debug("res:" + res.toString());
			//fel.jar 调用jdk环境计算导致存在精度问题，现先向下取整截取小数点后6位再作进位
			res = res.setScale(6, RoundingMode.DOWN).setScale(newScale, roundingMode);
			log.debug("rouding:" + res.toString());
			log.debug("end caculate:" + fel.getChineseName());
			System.err.println("end caculate:" + fel.getChineseName());
			//计算并返回值
			return res;
		} catch (PersistentDataException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public BigDecimal calculateUseFeeInfo(String procInsId, BigDecimal amount,
			String felType, Map<String, Object> extraParam)
			throws ServiceException {
		return calculateUseFeeInfo(procInsId, amount,
				felType, extraParam, FelConstant.FEL_SCALE, FelConstant.FEL_ROUNDINGMODE);
	}

	@Override
	public BigDecimal calculateUseFeeInfo(String procInsId, BigDecimal amount,
			String felType) throws ServiceException {
		return calculateUseFeeInfo(procInsId, amount, felType, null);
	}
	
}
