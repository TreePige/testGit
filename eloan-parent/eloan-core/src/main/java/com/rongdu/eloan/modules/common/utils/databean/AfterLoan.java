package com.rongdu.eloan.modules.common.utils.databean;

public enum AfterLoan {

	EXTENSION("展期-",1),INADVANCE("提前还款-",2);
	
	private String name;
	private int num;
	
	
	private AfterLoan(String name, int num) {
		this.name = name;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public static AfterLoan numOf(int index){
		for (AfterLoan afterLoan : values()) {
			if(afterLoan.getNum() == index){
				return afterLoan;
			}
		}
		return null;
	}
}
