package com.zking.zkingedu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    private static Integer maxIdle=8;
    private static Integer maxWaitMillis=1000;
    private static Integer minIdle=0;
    private static Integer maxTotal=8;


    private static String host="47.107.120.48";
    private static Integer port=6379;
    private static String password="";
    private static Integer database=0;
    private static Integer timeout=1000;//




    //@Value("${spring.redis.host}")
//    private String host;
    // 篇幅受限，省略了

    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinIdle(minIdle);
        return config;
    }

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        factory.setHostName(host);
        factory.setPort(port);
        factory.setDatabase(database);
        factory.setPassword(password);
        factory.setTimeout(timeout);
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> getRedisTemplate(){
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
        return template;
    }
}
