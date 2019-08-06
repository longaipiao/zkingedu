//package com.zking.zkingedu.common.config;
//
//import com.zking.zkingedu.common.redis.MybatisRedisCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 静态注入的方式。这个方式是通过RedisCacheTransfer来实现的。
// * 颜
// */
//@Component
//public class RedisCacheTransfer {
//
//    @Autowired
//    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
//        MybatisRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
//    }
//
//}
