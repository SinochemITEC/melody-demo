package com.eyeieye.melody.demo.cache;

import java.util.List;

public interface CacheManager<T extends CacheEntry> {
	/**
	 * 新增缓存
	 * @param name
	 * @param entry
	 */
	void add(String name, T entry);

	/**
	 * 更新缓存
	 * @param name
	 * @param entry
	 */
	void update(String name, T entry);

	/**
	 * 移除缓存
	 * @param name
	 * @param key
	 */
	void remove(String name, String key);

	/**
	 * 移除缓存
	 * @param name
	 */
	void remove(String name);

	/**
	 * 获取对应名称所有缓存
	 * @param name
	 * @param <E>
	 * @return
	 */
	<E extends T> List<E> getDatas(String name);

	/**
	 * 获取指定名称指定键缓存
	 * @param name
	 * @param key
	 * @param <E>
	 * @return
	 */
	<E extends T> E getData(String name, String key);

	/**
	 * 是否存在执行名称指定键缓存
	 * @param name
	 * @param key
	 * @return
	 */
	boolean has(String name, String key);

}
