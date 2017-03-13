package com.rongdu.eloan.modules.common.utils;

import java.math.BigDecimal;
import java.math.MathContext;

public abstract class ObjectInitUtil {
    private ObjectInitUtil() {
    }

    /**
     * @description 初始化BigDecimal
     * @param bd
     * @param value
     * @param mc
     * @return
     * @author wtc
     * @return BigDecimal
     * @since 1.0.0
     */
    public static BigDecimal initBigDecimal(BigDecimal bd,String value, MathContext mc) {
        if(bd==null){
            if (mc != null)
                return new BigDecimal(value, mc);
            else
                return new BigDecimal(value);
        }else{
            return bd;
        }
    }

    /**
     * @description 初始化BigDecimal
     * @param value
     * @return
     * @author wtc
     * @return BigDecimal
     * @since  1.0.0
    */
    public static BigDecimal initBigDecimal(BigDecimal bd,String value) {
        return initBigDecimal(bd,value, null);
    }
    
    public static BigDecimal initBigDecimal(BigDecimal bd){
        return initBigDecimal(bd,"0", null);
    }
    public static Integer initInteger(Integer i,String value){
        if(i==null){
            return Integer.parseInt(value);
        }else{
            return i;
        }
    }
    public static Integer initInteger(Integer i){
        return initInteger(i, "0");
    }
    public static Byte initByte(Byte i,String value){
        if(i==null){
            return Byte.parseByte(value);
        }else{
            return i;
        }
    }
    public static Byte initByte(Byte i){
        return initByte(i, "0");
    }
}
