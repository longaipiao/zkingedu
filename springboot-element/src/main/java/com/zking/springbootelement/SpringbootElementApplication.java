package com.zking.springbootelement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.zking.springbootelement.dao")//注入所有dao层接口
@EnableTransactionManagement
public class SpringbootElementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElementApplication.class, args);
    }

}
