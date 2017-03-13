package com.rongdu.eloan.modules.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2014/10/21.
 */
public class QueryPameterUtils {

    public static String[] matcheFirstAllGroup(String str,String regex){
        Matcher m= Pattern.compile(regex).matcher(str);
        if(!m.find())return null;
        String[] gps=new String[m.groupCount()+1];
        for(int i=0;i<gps.length;i++){
            gps[i]=m.group(i);
        }
        return gps;
    }

    public static Map<String,Object> getParametersMap(HttpServletRequest request,String startWith){
        Map<String,Object> params=new HashMap<String, Object>();
        List<String> where=getParameters(request,"where");
        params.put("where",where);
        return params;
    }

    public static List<String> getParameters(HttpServletRequest request,String startWith){
        Map<String,String[]> params= request.getParameterMap();
        List<String> list=new ArrayList<String>();
        for(String name:params.keySet()){
            String value=((String[])params.get(name))[0].trim();
            if(name.startsWith(startWith +".") && !"".equals(value)){
                String[] gps=matcheFirstAllGroup(name,"^[^.]+\\.(.+?)(?:\\((null|int)\\))?(?:_((?:not )?(?:regexp|like|in)|[<>!]=?))?$");
                String fieldname=gps[1];
                String type=gps[2];
                String opera=gps[3]==null?"=":gps[3];
                if(fieldname.matches("\\$\\{.*\\}")){
                    fieldname=fieldname.replaceAll("\\$\\{(.*)\\}","$1");
                }else{
                    if("like".equals(opera)){
                        value="'%"+value+"%'";
                    }else if(opera.endsWith("in")){
                        if(!"int".equals(type)){
                            value=value.replaceAll("[^,]+","'$0'");
                        }
                        value="("+value+")";
                    }else{
                        if("int".equals(type)){
                            if("".equals(value))continue;
                        }else if("null".equals(type) && "".equals(value)){
                            opera="is ";
                            value="null";
                        }else{
                            value="'"+value+"'";
                        }
                    }
                }
                list.add(fieldname+" "+opera+value);
            }
        }
        return list;
    }

    public static Map<String,Map<String,Object>> getJsonMapTbs(String json){
        return deleteUnNameField(JSON.parseObject(json, LinkedHashMap.class));
    }
    public static Map<String,Map<String,Object>> deleteUnNameField(Map<String,Map<String,Object>> tbs){
        Map<String,Map<String,Object>> tbs_=new LinkedHashMap<String, Map<String, Object>>();
        for(String tb:tbs.keySet()){
            Map<String,Object> rec=tbs.get(tb);
            Map<String,Object> rec_=new LinkedHashMap<String, Object>();
            for(String column:rec.keySet()){
                if(!column.endsWith("-inputEl")){
                    Object value=rec.get(column);
                    if("".equals(value))value=null;
                    rec_.put(column,value);
                }
            }
            if(!rec_.isEmpty()){
                tbs_.put(tb,rec_);
            }
        }
        return tbs_;
    }


    public static Map<String,Object> buildPageParams(Integer pageNum,Integer pageSize,Map<String,Object> params){
        if(params==null)params=new HashMap<String, Object>();
        if(pageNum!=null && pageSize!=null){
            params.put("start", (pageNum-1)*pageSize);
            params.put("length", pageSize);
        }
        return params;
    }


    public static Map<String,Object> convertEmptyStr2Null(Map<String,Object> rec){
        for(String key:rec.keySet()){
            Object value=rec.get(key);
            if(value instanceof String && "".equals(((String) value).trim())){
                rec.put(key,null);
            }
        }
        return rec;
    }




    public static List<Map> treeForExt(List<Map> list,Boolean leafChecked,Boolean rootChecked,Boolean expanded){
        for(Map rec:list){
            List<Map> children= (List<Map>) rec.get("children");
            if(children==null || children.size()==0){
                rec.put("leaf",true);
                if(leafChecked!=null){
                    rec.put("checked",leafChecked);
                }
            }else{
                if(rootChecked!=null){
                    rec.put("checked",rootChecked);
                }
                if(expanded!=null){
                    rec.put("expanded",expanded);
                }
                treeForExt(children,leafChecked,rootChecked,expanded);
            }
        }
        return list;
    }
}
