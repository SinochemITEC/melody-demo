package com.eyeieye.melody.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

public class SimpleCacheManager {
    static Map<String,Object> cache = new HashMap();

    public void put(String key, Object object){
        cache.put(key,object);
    }
    public Object get(String key){
        return cache.get(key);
    }

}
