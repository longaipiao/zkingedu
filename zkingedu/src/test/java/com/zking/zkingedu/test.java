package com.zking.zkingedu;

import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;
@Slf4j
public class test {
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.42.128");
        Set<String> keys = jedis.keys("*");
        System.out.println(jedis.get("upwd"));

        System.out.println("完事");
    }

    private static Logger logger = LoggerFactory.getLogger(test.class);

    @Test
    public void test1(){
        //log.error("来了error");
        log.info("来了info");
        //log.debug("来了debug");
    }
}
