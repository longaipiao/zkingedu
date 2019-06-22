package com.zking.zkingedu.common.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

//解决spring不能在quartz中注入bean的问题
@Component
@Slf4j
public class MyJobFactory extends AdaptableJobFactory {

    //自动由Spring注入进来
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        //解决Spring不能在Quartz中注入Bean的问题
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
