package com.zking.zkingedu.common.Aop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/*
拦截器
* */
@Configuration
public class WebAppConfig2 extends WebMvcConfigurerAdapter {

  /*  @Autowired
    private WebAppConfig webAppConfig;
    *//**
     * 阻止用户进行个人信息有关操作
     *//*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(webAppConfig)
                .addPathPatterns("/user/userinfo/index","/user/courses/index");
    }

*/
}
