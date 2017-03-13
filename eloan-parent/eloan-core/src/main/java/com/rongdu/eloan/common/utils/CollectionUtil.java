package com.rongdu.eloan.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2014/10/23.
 */
public class CollectionUtil {
    public static<T> List<T> toList(Collection<T> set){
        List<T> list=new ArrayList<T>();
        for(T t:set){
            list.add(t);
        }
        return list;
    }
}
