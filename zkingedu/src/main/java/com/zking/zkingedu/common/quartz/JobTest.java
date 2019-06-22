package com.zking.zkingedu.common.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTest {
    //@Scheduled(cron = "0/3 * * * * ?")
    public void testt(){
        System.out.println("测试");
    }
}
