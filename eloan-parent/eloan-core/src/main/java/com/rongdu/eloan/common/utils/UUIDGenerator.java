/**
 * @title UUIDGenerator.java
 * @package com.rongdu.eloan.common.utils
 * @author 吴国成
 * @version V1.0
 * created 2015年4月23日
 */
package com.rongdu.eloan.common.utils;

import java.util.UUID;

/**
 * 公用类 关生uuid
 * @version 1.0
 * @author 吴国成
 * @created 2015年4月23日 下午5:00:25
 */

public class UUIDGenerator {
	
	public UUIDGenerator() {   
    }   
  
    public static String getUUID() {   
        UUID uuid = UUID.randomUUID();   
        String str = uuid.toString();   
        // 去掉"-"符号   
        //String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        str = str.replace("-", "");
        return str;   
    }      
  
    public static void main(String[] args) {   
        String ss = getUUID();   

        System.out.println(ss + "  "+ss.length());   
  
    }   

}
