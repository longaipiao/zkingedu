package com.zking.zkingedu.common.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int a = 0/5;
        System.out.println("这是一个空的执行任务,时间："+new SimpleDateFormat("yyyy-MM-dd  hh-mm-ss").format(new Date()));
    }
}
