package com.rongdu.eloan.modules.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class TestValiad {
	
   public static void main(String[] args) {
	
	   
	   ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	   
	   Validator validator = factory.getValidator();
	   
	   TestEntity testEntity = new TestEntity();
	   
	   
	   testEntity.setAge(12);
	   testEntity.setName("fdgdgdgdgdg");
	   Set<ConstraintViolation<TestEntity>> constraintViolations = validator.validate(testEntity);
	   
	   for (ConstraintViolation<TestEntity> constraintViolation:constraintViolations) {
		   
		   System.out.println(constraintViolation.getPropertyPath());
		   System.out.println(constraintViolation.getMessageTemplate());
		   System.out.println(constraintViolation.getMessage());
		
	}
}

}
