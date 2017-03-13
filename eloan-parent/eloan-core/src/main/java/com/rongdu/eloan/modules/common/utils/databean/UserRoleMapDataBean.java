package com.rongdu.eloan.modules.common.utils.databean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * 区分登入人员 输入客服岗还是审批岗.
 * @author wdk
 */
public class UserRoleMapDataBean implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 客服岗map. */
	private Map<String,String> customerMap;
	
	/** 审批岗map. */
	private Map<String,String> cuditorMap;
	
	/**
	 * Instantiates a new user role map data bean.
	 */
	public UserRoleMapDataBean(){
		this.customerMap = addCustomerMap();
		this.cuditorMap = addCuditorMap();
	}
	
	/**
	 * Adds the customer map.
	 *
	 * @return the map
	 */
	public  Map<String,String> addCustomerMap(){
		Map<String,String> cMap = new HashMap<String, String>();
		cMap.put("customerServiceStaff", "customerServiceStaff");
		cMap.put("customerManager", "customerManager");
		return cMap;
	}
	
	/**
	 * Adds the cuditor map.
	 *
	 * @return the map
	 */
	public  Map<String,String> addCuditorMap(){
		Map<String,String> cMap = new HashMap<String, String>();
		cMap.put("initialAuditor", "initialAuditor");
		cMap.put("finalAuditor", "finalAuditor");
		cMap.put("secondaryFinalAuditor", "secondaryFinalAuditor");
		cMap.put("doubleDiscussStaff", "doubleDiscussStaff");
		cMap.put("financialStaff", "financialStaff");
		cMap.put("financialManager", "financialManager");
		cMap.put("manager", "manager");
		cMap.put("auditorLeader", "auditorLeader");
		return cMap;
	}

	/**
	 * Gets the customer map.
	 *
	 * @return the customer map
	 */
	public Map<String, String> getCustomerMap() {
		return customerMap;
	}

	/**
	 * Sets the customer map.
	 *
	 * @param customerMap the customer map
	 */
	public void setCustomerMap(Map<String, String> customerMap) {
		this.customerMap = customerMap;
	}

	/**
	 * Gets the cuditor map.
	 *
	 * @return the cuditor map
	 */
	public Map<String, String> getCuditorMap() {
		return cuditorMap;
	}

	/**
	 * Sets the cuditor map.
	 *
	 * @param cuditorMap the cuditor map
	 */
	public void setCuditorMap(Map<String, String> cuditorMap) {
		this.cuditorMap = cuditorMap;
	}
	
	
}
