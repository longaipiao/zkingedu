package com.zking.zkingedu.common.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfiguration {

    @Autowired
    private MyJobFactory myJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        //1.创建ScheduleFactoryBean
        //2.加载自定义的quartz.properites配置文件
        //3.注入自定义的MyJobFactory
        SchedulerFactoryBean sc=new SchedulerFactoryBean();

        try {
            sc.setQuartzProperties(quartzProperties());
            sc.setJobFactory(myJobFactory);
            return sc;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean=new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/application.yml"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean(name="scheduler")
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
