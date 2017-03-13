package com.rongdu.eloan.modules.fel.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.rongdu.eloan.modules.common.exception.ServiceException;

/**
 * 
 *	@Description 公式配置器,调用
 *  @author 孙凯伦,skl@erongdu.com
 *  @CreatTime 2016年2月22日 下午4:32:05
 *  @since version 1.0.0
 */
public interface FelService {
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
	public String calculate(String productType,String type,Map<String, Object> param);
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
	public BigDecimal calculate(String formulaId,Map<String, Object> param) throws ServiceException;
	
	public BigDecimal calculate(String formulaId,Map<String, Object> param, int newScale, RoundingMode roundingMode) throws ServiceException;
	
	/**
	 * 
	 * @description 根据产品id 借款金额 公式类型计算对应的值 默认保留两位小数
	 * @param formulaId
	 * @param param
	 * @return
	 * @author liyc
	 * @return String
	 * @since  1.0.0
	 */
	public BigDecimal calculate(long productId, BigDecimal amount, String felType) throws ServiceException;
	
	/**
	 * 
	 * @description 计算带保留小数
	 * @param productId
	 * @param amount
	 * @param felType
	 * @param newScale
	 * @param roundingMode BigDecimal 的精度模式
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	public BigDecimal calculate(long productId, BigDecimal amount, String felType, Map<String, Object> extraParam, int newScale, RoundingMode roundingMode) 
			throws ServiceException;
	
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
	public BigDecimal calculate(long productId, BigDecimal amount, String felType, Map<String, Object> extraParam) 
			throws ServiceException;
	
	/**
	 * 
	 * @description 计算产品所有包含公式的值
	 * @param param
	 * @param id 产品ID
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return Map<String,Object>
	 * @since  1.0.0
	 */
	public Map<String, Object> inspect(Map<String, Object> param) throws ServiceException; 
	
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
	public BigDecimal calculateByFelId(Map<String, Object> param, String id) throws ServiceException;
	
	/**
	 * 
	 * @description 参数来源feeinfo
	 * @param procInsId
	 * @param amount
	 * @param felType
	 * @param extraParam
	 * @return
	 * @throws ServiceException
	 * @author liyc
	 * @return BigDecimal
	 * @since  1.0.0
	 */
	public BigDecimal calculateUseFeeInfo(String procInsId, BigDecimal amount, String felType, 
			Map<String, Object> extraParam) throws ServiceException;
	
	public BigDecimal calculateUseFeeInfo(String procInsId, BigDecimal amount, String felType) throws ServiceException;
}
