package com.dongnaoedu;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建日期：2017/12/03
 * 创建时间: 22:08
 */
public class UseChm {

    HashMap<String,String> hashMap = new HashMap<>();
    ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<>();

    public String putIfAbsent(String key,String value){
        int a ;
        synchronized (hashMap){
            if(hashMap.get(key)==null){
                return hashMap.put(key,value);
            }else{
                return hashMap.get(key);
            }
        }
    }

    public String useChm(String key,String value){
        return chm.putIfAbsent(key,value);
    }

}
