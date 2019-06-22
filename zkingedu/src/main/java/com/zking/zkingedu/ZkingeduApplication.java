package com.zking.zkingedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.zking.zkingedu.common.dao")//注入所有dao层接口
public class ZkingeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkingeduApplication.class, args);
    }

}
