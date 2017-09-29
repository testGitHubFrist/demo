package com.nilo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2017/5/22.
 */

//@Configuration
//@EnableAutoConfiguration
public class RedisCacheConfiguration {

//    private static Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);
//
//
//    @Bean
//    public RedisTemplate<?, ?> getRedisTemplate(){
//        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
//        return template;
//    }
}

