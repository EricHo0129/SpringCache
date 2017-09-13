package com.eric.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.eric.model.EmployeModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * 快取設定檔(需繼承CachingConfigurerSupport)
 * @author yung.ho
 *
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName("172.19.7.129");
		redisConnectionFactory.setPort(6379);
		redisConnectionFactory.setDatabase(10);
		return redisConnectionFactory;
	}
	
	@Bean
	public Jackson2JsonRedisSerializer<EmployeModel> serializer() {
		return new Jackson2JsonRedisSerializer<EmployeModel>(EmployeModel.class);
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf, RedisSerializer<EmployeModel> serializer) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setDefaultSerializer(serializer);
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		redisCacheManager.setDefaultExpiration(300); //seconds
		return redisCacheManager;
	}
}
