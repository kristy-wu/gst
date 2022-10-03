package com.gst.framework.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.time.Duration;

/*
 * How to disable Redis cache with Spring Boot?
 *
 * added 3 properties to make it work: spring.cache.type: simple,
 * spring.data.redis.repositories.enabled: false, spring.autoconfigure.exclude:
 * org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */

//@Configuration
//@EnableCaching
public class GSCacheConfiguration extends CachingConfigurerSupport {
  
  @Bean
  public KeyGenerator gsBulletinItemListKeyGenerator() {
    return new KeyGenerator() {
      @NotNull
      @Override
      public Object generate(
        @NotNull Object target, @NotNull Method method, @NotNull Object... params) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : params) {
          System.out.println("obj: " + obj.toString());
          sb.append("-").append(obj);
        }
        return sb.toString();
      }
    };
  }
  
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    // RedisStandaloneConfiguration这个配置类是Spring Data Redis2.0后才有的~~~
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    // 2.0后的写法
    configuration.setHostName("192.168.74.88");
    // configuration.setPassword(RedisPassword.of("123456"));
    configuration.setPort(6379);
    configuration.setDatabase(0);
    
    LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
    // Spring Data Redis1.x这么来设置  2.0后建议使用RedisStandaloneConfiguration来取代
    // factory.setHostName("10.102.132.150");
    // factory.setPassword("123456");
    // factory.setPort(6379);
    // factory.setDatabase(0);
    return factory;
  }
  
  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    return redisTemplate;
  }
  
  @Bean
  public CacheManager cacheManager() {
    // 1.x是这么配置的：仅供参考
    // RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    // cacheManager.setDefaultExpiration(ONE_HOUR * HOURS_IN_ONE_DAY);
    // cacheManager.setUsePrefix(true);
    
    // --------------2.x的配置方式--------------
    // 方式一：直接create
    // RedisCacheManager redisCacheManager = RedisCacheManager.create(redisConnectionFactory());
    // 方式二：builder方式（推荐）
    
    RedisSerializationContext.SerializationPair<Object> pair =
      RedisSerializationContext.SerializationPair.fromSerializer(
        new GenericJackson2JsonRedisSerializer());
    
    RedisCacheConfiguration configuration =
      RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(pair)
        .entryTtl(Duration.ofDays(1)) // Duration.ZERO表示永不过期（此值一般建议必须设置）
        // .disableKeyPrefix() // 禁用key的前缀
        // .disableCachingNullValues() //禁止缓存null值
        
        // === 前缀我个人觉得是非常重要的，建议约定：注解缓存一个统一前缀、RedisTemplate直接操作的缓存一个统一前缀===
        // .prefixKeysWith("baidu:") // 底层其实调用的还是computePrefixWith()
        // 方法，只是它的前缀是固定的（默认前缀是cacheName，此方法是把它固定住，一般不建议使用固定的）
        // .computePrefixWith(CacheKeyPrefix.simple()); // 使用内置的实现
        .computePrefixWith(cacheName -> "caching:" + cacheName); // 自己实现，建议这么使用(cacheName也保留下来了)
    
    RedisCacheManager redisCacheManager =
      RedisCacheManager.builder(redisConnectionFactory())
        // .disableCreateOnMissingCache() // 关闭动态创建Cache
        // .initialCacheNames() // 初始化时候就放进去的cacheNames（若关闭了动态创建，这个就是必须的）
        .cacheDefaults(configuration) // 默认配置（强烈建议配置上）。  比如动态创建出来的都会走此默认配置
        // .withInitialCacheConfigurations() // 个性化配置  可以提供一个Map，针对每个Cache都进行个性化的配置（否则是默认配置）
        // .transactionAware() // 支持事务
        .build();
    return redisCacheManager;
  }

  /*@Bean
  public KeyGenerator gsKeyGenerator() {
    return new KeyGenerator() {
      @NotNull
      @Override
      public Object generate(
          @NotNull Object target, @NotNull Method method, @NotNull Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        for (Object obj : params) {
          sb.append("_").append(obj.toString());
        }

        return sb.toString();
      }
    };
  }

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {

    RedisSerializationContext.SerializationPair<Object> pair =
        RedisSerializationContext.SerializationPair.fromSerializer(
            new GenericJackson2JsonRedisSerializer());
    RedisCacheConfiguration defaultCacheConfig =
        RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(pair) // 序列化方式
            .entryTtl(Duration.ofHours(1)); // 過期時間

    return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
        .cacheDefaults(defaultCacheConfig)
        .build();
  }*/
  
  @Bean
  public JavaTimeModule dateTimeModule() {
    return new JavaTimeModule();
  }
  
  // =====================================================================================

  /*@Bean
  JedisConnectionFactory jedisConnectionFactory() {
    System.out.println("******************  jedisConnectionFactory **********************");
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    System.out.println("******************  redisTemplate **********************");

    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new BSTRedisMessageSubscriber());
  }

  @Bean
  RedisMessageListenerContainer redisContainer() {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory());
    container.addMessageListener(messageListener(), topic());
    return container;
  }

  @Bean
  BSTMessagePublisher redisPublisher() {
    return new BSTRedisMessagePublisher(redisTemplate(), topic());
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("pubsub:queue");
  }

  @Bean
  public JavaTimeModule dateTimeModule() {
    return new JavaTimeModule();
  }*/
}
