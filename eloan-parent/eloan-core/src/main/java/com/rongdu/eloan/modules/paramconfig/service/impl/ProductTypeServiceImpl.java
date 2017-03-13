package com.rongdu.eloan.modules.paramconfig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.modules.paramconfig.dao.ProductTypeDao;
import com.rongdu.eloan.modules.paramconfig.domain.ProductType;
import com.rongdu.eloan.modules.paramconfig.service.ProductTypeService;

/**
* User:    wangmc
* DateTime:2016-07-12 03:22:37
* details: 产品类型表,Service实现层
* source:  代码生成器
*/
@Service(value = "productTypeService")
public class ProductTypeServiceImpl extends BaseServiceImpl implements ProductTypeService {
	/**
	 * 日志操作
	 */
    private static final Logger log = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
    /**
	 * 产品类型表dao层
	 */
    @Autowired
    private ProductTypeDao producrtDao;
	
	/**
	*
	*继承关系
	*/
	@Override
	public BaseDao getMapper() {
		// TODO Auto-generated method stub
		return producrtDao;
	}
	
	/**
	 * 产品类型表表,插入数据
	 * @param collateral 产品类型表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public long insert(ProductType felProduct) throws Exception {
		try {
			return	producrtDao.insert(felProduct);
			
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
	public long update(ProductType felProduct) throws Exception {
		try {
			return	producrtDao.update(felProduct);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	/**
	 * 产品类型表表,查询数据
	 * @param felProduct 产品类型表类
	 * @return           返回页面map
	 * @throws ServiceException
	 */
	public List<ProductType> getProductTypeList(Map<String, Object> map,PageBounds pageBounds) throws Exception {
	
		try {
		
			return	producrtDao.select(map,pageBounds);
			
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
			
			return producrtDao.total(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getProductTypeCombo(Map<String, Object> map) 
			throws Exception {
		// TODO Auto-generated method stub
		try {
			return producrtDao.getProductTypeCombo(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public List<Map<String, Object>> getFaChannelCombo(Map<String, Object> map) 
			throws Exception {
		// TODO Auto-generated method stub
		try {
			return producrtDao.getFaChannelCombo(map);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
}