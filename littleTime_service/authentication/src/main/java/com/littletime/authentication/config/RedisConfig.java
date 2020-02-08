package com.littletime.authentication.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.littletime.authentication.Bean.User;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author YiBuBuHuiTou
 */
@Configuration
public class RedisConfig {

    // 缓存过期时间 15 分钟
    private Duration ttl = Duration.ofMinutes(15);
    // redis 配置对象生成
    private RedisStandaloneConfiguration generateRedisStandaloneConfiguration(String host, int port, int db, String password) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setDatabase(db);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return redisStandaloneConfiguration;
    }

    /**
     * 配置第一个数据源的配置
     *
     * @return
     */
    @Bean
    public RedisStandaloneConfiguration redisConfig1(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port
            , @Value("${spring.redis.database}") int db, @Value("${spring.redis.password}") String password) {
        return generateRedisStandaloneConfiguration(host, port, db, password);
    }


    /**
     * 配置第二个数据源配置
     * @return
     */
    @Bean
    public RedisStandaloneConfiguration redisConfig2(@Value("${spring.redis2.host}") String host, @Value("${spring.redis2.port}") int port
            , @Value("${spring.redis2.database}") int db, @Value("${spring.redis2.password}") String password) {
        return generateRedisStandaloneConfiguration(host, port, db, password);
    }


    /**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     *
     * @param redisConfig
     * @return
     */
    @Bean("factory")
    @Primary
    public LettuceConnectionFactory factory(RedisStandaloneConfiguration redisConfig1) {
        return new LettuceConnectionFactory(redisConfig1);
    }
    /**
     * 配置第二个数据源的连接工厂
     * @param redisConfig2
     * @return
     */
    @Bean("factory2")
    public LettuceConnectionFactory factory2(RedisStandaloneConfiguration redisConfig2) {
        return new LettuceConnectionFactory(redisConfig2);
    }

    private RedisTemplate<String, Object> generateRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = getValueSerializer();
        StringRedisSerializer stringRedisSerializer = getKeySerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
    /**
     * Default redisTemplate Bean
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("factory") RedisConnectionFactory factory) {
        return generateRedisTemplate(factory);
    }
    /**
     * second redisTemplate Bean
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate2(@Qualifier("factory2") RedisConnectionFactory factory2) {
        return generateRedisTemplate(factory2);
    }
    /**
     * 缓存管理器
     */
    @Bean
    @Primary
    public RedisCacheManager cacheManager(@Qualifier("factory2") RedisConnectionFactory factory2) {
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder  = RedisCacheManager.RedisCacheManagerBuilder.
                fromConnectionFactory(factory2);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(ttl).disableCachingNullValues().serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(getKeySerializer())).
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getValueSerializer()));
        return redisCacheManagerBuilder.cacheDefaults(redisCacheConfiguration).build();
    }
    /**
     * 缓存管理器2
     */
    @Bean
    public RedisCacheManager stringCacheManager(@Qualifier("factory2") RedisConnectionFactory factory2) {
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder  = RedisCacheManager.RedisCacheManagerBuilder.
                fromConnectionFactory(factory2);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(ttl).disableCachingNullValues().serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(getKeySerializer())).
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getKeySerializer()));
        return redisCacheManagerBuilder.cacheDefaults(redisCacheConfiguration).build();
    }
    /**
     * key 序列化
     * @return
     */
    private StringRedisSerializer getKeySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * value 序列化
     * @return
     */
    private Jackson2JsonRedisSerializer<User> getValueSerializer() {
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // enableDefaultTyping 方法有漏洞，临时使用
        // TODO
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
