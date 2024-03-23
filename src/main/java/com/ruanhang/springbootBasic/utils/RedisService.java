package com.ruanhang.springbootBasic.utils;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

//对redis的简单封装，对外提供api  
//TODO  这里应该提供hash list set zset string 对外的api
@Service
public class RedisService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @param key
	 * @param value
	 * @return 存储key,value的实体对象
	 */
	public boolean setObject(String key, Object value) {
		boolean result = false;
		// TODO 如何封装，王大神
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key.getBytes(), SerializeUtil.serizlize(value));
			result = true;
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * @param key
	 * @return 通过key获取相对应的POJO
	 */
	public Object getObject(String key) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			// TODO 如何封装，王大神
			boolean exists = redisTemplate.hasKey(key);
			if (exists) {
				System.out.println("exists is true");
				Object object = operations.get(key);
				return object.toString();
			} else {
				System.out.println("exists is false");
			}
		} catch (Exception e) {

		}
		return result;
	}

}
