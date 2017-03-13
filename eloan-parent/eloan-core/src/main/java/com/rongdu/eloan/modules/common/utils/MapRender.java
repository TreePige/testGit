package com.rongdu.eloan.modules.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/2/13.
 */
public class MapRender {
    private Collection<Map> mapList;
    private Map renders=new HashMap();

    public MapRender(Collection<Map> mapList) {
        this.mapList = mapList;
    }

    public interface Render{
        Object render(Object value,Object key,Map map,Object[] results);
    }
    public MapRender registerRender(Object[][] renders){
        this.renders=new HashMap();
        for(Object[] render:renders){
            try {
                this.renders.put(render[0],(Render)render[1]);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return this;
    }
    public Collection<Map> render(){
        try {
            Collection<Map> newMapList=mapList.getClass().newInstance();
            for(Map item:mapList){
                Map newMap=item.getClass().newInstance();
                newMap.putAll(item);

                newMapList.add(newMap);

                for(Object renderKey:renders.keySet()){
                    Render render= (Render) renders.get(renderKey);
                    Object value=item.get(renderKey);

                    if(item.containsKey(renderKey)){
                        Object[] par=new Object[2];
                        value=render.render(value,renderKey,item,par);
                        newMap.put(renderKey,value);
                        if(par[0]!=null && par[1]!=null){
                            newMap.put(par[0],par[1]);
                        }
                    }
                }
            }

            mapList.clear();
            mapList.addAll(newMapList);
            return newMapList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
