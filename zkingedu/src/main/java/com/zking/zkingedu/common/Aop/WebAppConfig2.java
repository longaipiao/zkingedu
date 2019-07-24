package com.zking.zkingedu.common.Aop;


import com.zking.zkingedu.common.service.LogService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/*
拦截器
* */
@Configuration
@Log4j
public class WebAppConfig2 extends WebMvcConfigurerAdapter {

    @Autowired
    private WebAppConfig webAppConfig;
    @Autowired
    private LogService logService;


    /**
     * 阻止用户进行个人信息有关操作
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(webAppConfig)
                .addPathPatterns("/user/userinfo/index", "/user/courses/index");
    }

    /**
     * 定时删除所有日志  每个月的每月的最后一天的11：59触发 0 59 23 L * ?
     */
    @Scheduled(cron = "0 59 23 * * ?")
    public void dshirw(){
        int lid = logService.deleLog();
        log.info("定时删除日志成功！！！"+lid);
    }


}
