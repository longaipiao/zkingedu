package com.zking.zkingedu;

import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class test {
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.42.128");
        Set<String> keys = jedis.keys("*");

    }

    private static Logger logger = LoggerFactory.getLogger(test.class);

    @Test
    public void test1(){
        //log.error("来了error");
        log.info("来了info");
        //log.debug("来了debug");
    }



    @Test
    public void  te(){
        Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(16[0-9])|(19[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

        System.out.println(p.matcher("19773867049").matches());
    }







}
