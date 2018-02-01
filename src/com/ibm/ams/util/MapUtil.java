package com.ibm.ams.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MapUtil {
	/***
	 * Map对象转Bean对象
	 * @param map
	 * @param class1
	 * @return
	 */
	 public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {  
	        T bean = null;  
	        try {  
	            bean = class1.newInstance();  
	            BeanUtils.populate(bean, map);  
	        } catch (InstantiationException e) {  
	            e.printStackTrace();  
	        } catch (IllegalAccessException e) {  
	            e.printStackTrace();  
	        } catch (InvocationTargetException e) {  
	            e.printStackTrace();  
	        }  
	        return bean;  
	    }  
}
