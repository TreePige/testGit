package com.rongdu.eloan.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2015/4/22.
 */
public class MapUtil {
    public static void applyif(Map map,Map newMap) {
        if(newMap==null)return;
        for(Object key:newMap.keySet()){
            if(!map.containsKey(key)){
                map.put(key,newMap.get(key));
            }
        }
    }
    public static void apply(Map map,Map newMap) {
        if(newMap==null)return;
        for(Object key:newMap.keySet()){
            map.put(key,newMap.get(key));
        }
    }

    public static List collectProperty(List<Map> list,String property){
        return collectProperty(list,property,true);
    }
    
    //  upper
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List collectProperty(List<Map> list,String property,boolean acceptNull){
        List rlist=new ArrayList();
        for (Map rec : list) {
            Object o=rec.get(property);
            if(o==null && !acceptNull)continue;
            rlist.add(o);
        }
        return rlist;
    }
    /**
     * 
     * @description 	将一个 Map 对象转化为一个 JavaBean 
     * @param type 		要转化的类型 
     * @param map		包含属性值的 map 
     * @return			 转化出来的 JavaBean 对象 
     * @author 孙凯伦
     * @return Object
     * @since  1.0.0
     */
    public static Object convertMap(Class type, Map map){   
        BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
	        Object obj = type.newInstance(); // 创建 JavaBean 对象   
	   
	        // 给 JavaBean 对象的属性赋值   
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();   
	        for (int i = 0; i< propertyDescriptors.length; i++) {   
	            PropertyDescriptor descriptor = propertyDescriptors[i];   
	            String propertyName = descriptor.getName();   
	   
	            if (map.containsKey(propertyName)) {   
	                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。   
	                Object value = map.get(propertyName);   
	   
	                Object[] args = new Object[1];   
	                args[0] = value;   
	   
	                descriptor.getWriteMethod().invoke(obj, args);   
	            }   
	        }   
	        return obj;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} // 获取类属性   
		catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
    } 
    
    /**
     * 
     * @description			将一个 JavaBean 对象转化为一个  Map 
     * @param bean			bean 要转化的JavaBean 对象 
     * @return				转化出来的  Map 对象 
     * @throws IntrospectionException		IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException		IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException	InvocationTargetException 如果调用属性的 setter 方法失败 
     * @author 孙凯伦
     * @return Map
     * @since  1.0.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })   
    public static Map convertBean(Object bean)   
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {   
        Class type = bean.getClass();   
        Map returnMap = new HashMap();   
        BeanInfo beanInfo = Introspector.getBeanInfo(type);   
   
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();   
        for (int i = 0; i< propertyDescriptors.length; i++) {   
            PropertyDescriptor descriptor = propertyDescriptors[i];   
            String propertyName = descriptor.getName();   
            if (!propertyName.equals("class")) {   
                Method readMethod = descriptor.getReadMethod();   
                Object result = readMethod.invoke(bean, new Object[0]);   
                if (result != null) {   
                    returnMap.put(propertyName, result);   
                } else {   
                    returnMap.put(propertyName, "");   
                }   
            }   
        }   
        return returnMap;   
    }
}
