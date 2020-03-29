package com.by.config;

import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
		// return RedisCacheManager.create(connectionFactory);
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		// 解决查询缓存转换异常的问题
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// 配置序列化（解决乱码的问题）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ZERO)// 失效时间
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.disableCachingNullValues();

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
		return cacheManager;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		// 配置连接工厂
		template.setConnectionFactory(factory);
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
		Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
		// om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		jacksonSeial.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// value采用json序列化
		template.setValueSerializer(jacksonSeial);
		// hash的key采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// hash的value采用json序列化模式
		template.setHashValueSerializer(jacksonSeial);
		template.afterPropertiesSet();
		return template;
	}
	//
	// /**
	// * 自定义redis序列化的机制,重新定义一个ObjectMapper.防止和MVC的冲突
	// *
	// * @return
	// */
	// @Bean
	// public RedisSerializer<Object> redisSerializer() {
	//
	// ObjectMapper objectMapper = new ObjectMapper();
	// // 反序列化时候遇到不匹配的属性并不抛出异常
	// objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	// // 序列化时候遇到空对象不抛出异常
	// objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	// // 反序列化的时候如果是无效子类型,不抛出异常
	// objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE,
	// false);
	// // 不使用默认的dateTime进行序列化,
	// objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
	// false);
	// // 使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
	// objectMapper.registerModule(new JavaTimeModule());
	// // 启用反序列化所需的类型信息,在属性中添加@class
	// objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
	// ObjectMapper.DefaultTyping.NON_FINAL,
	// JsonTypeInfo.As.PROPERTY);
	// // 配置null值的序列化器
	// GenericJackson2JsonRedisSerializer.registerNullValueSerializer(objectMapper,
	// null);
	// return new GenericJackson2JsonRedisSerializer(objectMapper);
	//
	// }
	//
	// @Bean
	// public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory
	// redisConnectionFactory,
	// RedisSerializer<Object> redisSerializer) {
	//
	// RedisTemplate<Object, Object> template = new RedisTemplate<>();
	// template.setConnectionFactory(redisConnectionFactory);
	// template.setDefaultSerializer(redisSerializer);
	// template.setValueSerializer(redisSerializer);
	// template.setHashValueSerializer(redisSerializer);
	// template.setKeySerializer(StringRedisSerializer.UTF_8);
	// template.setHashKeySerializer(StringRedisSerializer.UTF_8);
	// template.afterPropertiesSet();
	// return template;
	// }
}
