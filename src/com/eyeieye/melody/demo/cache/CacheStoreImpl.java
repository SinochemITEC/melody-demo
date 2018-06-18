package com.eyeieye.melody.demo.cache;

import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.web.util.ObjectFactory;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Redisª∫¥Ê µœ÷
 * @author sagahl<br>
 */
@Component
public class CacheStoreImpl<T extends CacheEntry> implements CacheStore<T>, InitializingBean {

	private JedisPool jedisPool;

	@Autowired
	private ObjectFactory objectFactory;

	interface JedisExcutor {
		Object execute(Jedis jedis);
	}

	private Object execute(JedisExcutor excutor) {
		Jedis jedis = jedisPool.getResource();
		Object o = excutor.execute(jedis);
		if (jedis != null) {
			jedisPool.returnResourceObject(jedis);
		}
		return o;
	}

	public boolean has(final String name) {
		Boolean has = (Boolean) execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.exists(name);
			}
		});
		return has;
	}

	public boolean has(final String name, final String key) {
		Boolean has = (Boolean) execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hexists(name, key);
			}
		});
		return has;
	}

	public void add(final String name, final List<T> datas) {
		if (ArrayUtil.isEmpty(datas)) {
			return;
		}
		execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				Map<byte[], byte[]> hmap = new HashMap<byte[], byte[]>();
				for (T data : datas) {
					hmap.put(data.getKey().getBytes(),
							SerializationUtils.serialize(data));
				}
				jedis.hmset(name.getBytes(), hmap);
				return null;
			}
		});
	}

	public void add(final String name, final T entry) {
		if (entry == null) {
			return;
		}
		execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				if (!jedis.hexists(name, entry.getKey())) {
					return jedis.hset(name.getBytes(), entry.getKey()
							.getBytes(), SerializationUtils.serialize(entry));
				}
				return null;
			}
		});
	}

	public void update(final String name, final T entry) {
		execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				if (jedis.hexists(name, entry.getKey())) {
					return jedis.hset(name.getBytes(), entry.getKey()
							.getBytes(), SerializationUtils.serialize(entry));
				}
				return null;
			}
		});
	}

	public void remove(final String name) {
		execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				if (jedis.exists(name)) {
					return jedis.del(name.getBytes());
				}
				return null;
			}
		});
	}

	public void remove(final String name, final String key) {
		execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				if (jedis.exists(name)) {
					return jedis.hdel(name.getBytes(), key.getBytes());
				}
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public T getData(final String name, final String key) {
		return (T) execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				if (jedis.hexists(name, key)) {
					return SerializationUtils.deserialize(jedis.hget(
							name.getBytes(), key.getBytes()));
				}
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> getDatas(final String name) {
		return (List<T>) execute(new JedisExcutor() {
			@Override
			public Object execute(Jedis jedis) {
				Map<byte[], byte[]> objectmap = jedis.hgetAll(name.getBytes());
				List<T> tList = new ArrayList<T>();
				for (byte[] objectByteArr : objectmap.values()) {
					tList.add((T) SerializationUtils.deserialize(objectByteArr));
				}
				return tList;
			}
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JedisPool jedisPool = objectFactory.getBean(JedisPool.class);
		if (jedisPool != null) {
			this.jedisPool = jedisPool;
		}

	}

}
