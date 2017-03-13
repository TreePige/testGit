package com.rongdu.eloan.modules.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rongdu.eloan.modules.common.utils.Tree.TreeObject;

/**
 * 
 * 实现树型
 * @version 1.0
 * @author 吴国成
 * @created 2014年12月18日 下午2:39:24
 */
@SuppressWarnings("unchecked")
public class Tree{			
	static class TreeObject{
		public Object value;
		public Object parent;
		public String label;
		public Boolean leaf=true;
		public Boolean expanded = true;
		public List<TreeObject> children=null;//new ArrayList();
	}
	/**
	 * 树型列表
	 * @param list
	 * @param primaryKey
	 * @param textKey
	 * @param parentKey
	 * @return 
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年12月18日
	 */
	public static List<TreeObject> TreeList(List<?> list,String primaryKey,String textKey,String parentKey) throws Exception{
		Map<String,TreeObject> mapping = new TreeMap<String, TreeObject>();
		for(Object object:list){
			TreeObject treeObject = new TreeObject();
			treeObject.value = getObjectKeyValue(object,primaryKey).toString();
			treeObject.parent = getObjectKeyValue(object,parentKey).toString();
			treeObject.label = "" + getObjectKeyValue(object,textKey);
			mapping.put(treeObject.value+"", treeObject);
		}
		for(TreeObject treeObject:mapping.values()){
			TreeObject parentObject = mapping.get(treeObject.parent+"");
			if(parentObject!=null){
				parentObject.leaf=false;				
				if(parentObject.children==null){
					parentObject.children=new ArrayList<Tree.TreeObject>();				    
				}
				parentObject.children.add(treeObject);
			}
		}
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		for(TreeObject treeObject:mapping.values()){			
			TreeObject parentObject = mapping.get(treeObject.parent+"");
			if(parentObject==null){
				treeList.add(treeObject);
			}			
		}

		return treeList;
	}
	
	public static String TreeJson(List list,String primaryKey,String textKey,String parentKey) throws Exception{
		return com.alibaba.fastjson.JSONObject.toJSONString(Tree.TreeList(list, primaryKey, textKey, parentKey)).toString();
	}
	
	private static Object getObjectKeyValue(Object object,String key){
		if(object instanceof Map)
			return ((Map)object).get(key);
		else{
			Field[] fields = object.getClass().getDeclaredFields();
			for(Field field:fields)
			{
				if(field.getName().equals(key)){
					Object value=null;
					try{
						value = field.get(object);
					} catch (Exception e){}
					if(value!=null)
						return value;
				}
			}
			String getMethodName="get"+ForMat(key);
			Method method=null;
			try{
				method = object.getClass().getMethod(getMethodName);
			} catch (Exception e){}
			if(method!=null)
			{
				Object value=null;
				try{
					value=method.invoke(object);
				} catch (Exception e){}
				return value;
			}
		}
		return null;
	}
	
	private static String ForMat(String string){
		if(string==null || string.equals(""))
			return string;
		return string.substring(0,1).toUpperCase() + string.substring(1);
	}	
}
