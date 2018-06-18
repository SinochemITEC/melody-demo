package com.eyeieye.melody.demo.cache;

import java.util.List;

public interface CacheStore<T extends CacheEntry> {
    /**
     * 缓存是否存在
     * @param name 缓存名
     * @return 是否存在相应的缓存
     */
    boolean has(String name);

    /**
     * 某个缓存值是否存在
     * @param name 缓存名
     * @param key 缓存的key
     * @return 是否存在
     */
    boolean has(String name, String key);

    /**
     * 添加缓存
     * @param name 缓存名
     * @param datas 对象集合
     */
    void add(String name, List<T> datas);

    /**
     * 添加缓存
     * @param name 缓存名
     * @param entry 缓存对象
     */
    void add(String name, T entry);

    /**
     * 更新缓存
     * @param name 缓存名
     * @param entry 缓存对象
     */
    void update(String name, T entry);

    /**
     * 移除缓存
     * @param name 缓存名
     */
    void remove(String name);

    /**
     * 移除缓存
     * @param name 缓存名
     * @param key 缓存key
     */
    void remove(String name, String key);

    /**
     * 获取数据
     * @param name 缓存名
     * @param key 缓存key
     * @return 缓存对象
     */
    T getData(String name, String key);

    /**
     * 获取缓存数据
     * @param name 缓存名
     * @return 对象集合
     */
    List<T> getDatas(String name);
}
