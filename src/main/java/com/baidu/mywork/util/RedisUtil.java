package com.baidu.mywork.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
	
	private static final int TIMEOUT = 60;

	public static void setStringValue(String key,String value) {
		RedisTemplate<String,String> template = (RedisTemplate<String,String>)SpringContextUtil.getBeanByType(RedisTemplate.class);
		template.opsForValue().set(key, value, TIMEOUT, TimeUnit.MINUTES);
	}
	
	public static String getStringValue(String key) {
		RedisTemplate<String,String> template = (RedisTemplate<String,String>)SpringContextUtil.getBeanByType(RedisTemplate.class);
		return template.opsForValue().get(key);
	}
}
