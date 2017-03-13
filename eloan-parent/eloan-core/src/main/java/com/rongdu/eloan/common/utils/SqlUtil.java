package com.rongdu.eloan.common.utils;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by lsk on 2015/10/21.
 */
public class SqlUtil {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String buildSaveOrUpdatesMap(String tableName,List<Map<String,Object>> list){
        StringBuilder sql=new StringBuilder(buildInsertSqlMap(tableName, list));
        sql.append("on duplicate key update ");
        for (String name : list.get(0).keySet()) {
            sql.append("`"+name+"`=values(`"+name+"`),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();

    }
    public static String buildInsertSqlMap(String tableName, List<Map<String, Object>> list){
        StringBuilder sb=new StringBuilder("insert into `"+tableName+"`(");

        Set<String> keys=list.get(0).keySet();
        for (String key : keys) {
            sb.append("`"+key+"`,");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")values");

        for (Map<String, Object> rec : list) {
            sb.append("(");
            for (String key : keys) {
                Object value=rec.get(key);
                if(value!=null && !(value instanceof Number) && !(value instanceof Boolean) ){
                    if(value instanceof Date){
                        value=sdf.format(value);
                    }else{
                        value=value.toString().replaceAll("['\\\\]","\\\\$0");
                    }
                    value="'"+value+"'";
                }
                sb.append(value+",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
