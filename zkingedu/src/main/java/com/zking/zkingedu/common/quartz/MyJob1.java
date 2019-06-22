package com.zking.zkingedu.common.quartz;



import com.zking.zkingedu.common.model.ScheduleTriggerParam;
import com.zking.zkingedu.common.service.IScheduleTriggerParamService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyJob1 implements Job {

    @Autowired
    private IScheduleTriggerParamService scheduleTriggerParamService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<ScheduleTriggerParam> scheduleTriggerParams =
                scheduleTriggerParamService.queryScheduleParam(2);
        System.out.println("size:"+scheduleTriggerParams.size());
    }
}
