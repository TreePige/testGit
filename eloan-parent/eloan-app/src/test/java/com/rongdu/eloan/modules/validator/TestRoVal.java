package com.rongdu.eloan.modules.validator;

import com.rongdu.eloan.common.exception.ParamValideException;
import com.rongdu.eloan.common.utils.validator.ValidatorUtil;

public class TestRoVal {

	public static void main(String[] args) {
		
		   TestEntity testEntity = new TestEntity();
		   
		   
		   testEntity.setAge(12);
		   testEntity.setName("fdgdgdgdgdg");
		try {
			//ValidatorUtil.validateDomain(testEntity);
			ValidatorUtil.validateProperty(testEntity, "age");
		} catch (ParamValideException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
              
	}

}
