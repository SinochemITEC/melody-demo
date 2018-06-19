package com.eyeieye.melody.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sagahl<br>
 */
@Service
public class CacheManagerImpl<T extends CacheEntry> implements CacheManager<T> {

	
	@Autowired(required = false)
	private CacheStore<T> cacheStore;

	@Override
	public void add(String name, T entry){
		cacheStore.add(name, entry);
	}

	@Override
	public void update(String name, T entry){
		cacheStore.update(name, entry);
	}

	@Override
	public void remove(String name, String key) {
		cacheStore.remove(name, key);
	}

	@Override
	public void remove(String name){
		cacheStore.remove(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends T> List<E> getDatas(String name) {
		return (List<E>) cacheStore.getDatas(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends T> E getData(String name, String key) {
		return (E) cacheStore.getData(name, key);
	}

	@Override
	public boolean has(String name, String key) {
		return cacheStore.has(name, key);
	}
}
