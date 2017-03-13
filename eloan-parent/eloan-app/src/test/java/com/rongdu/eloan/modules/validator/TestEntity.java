package com.rongdu.eloan.modules.validator;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;

public class TestEntity {
    
	@Max(value=2,message="最大不能超过3")
	private int age;
	
	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Length(max=1,message="长度不对")
	private String name ;
}
