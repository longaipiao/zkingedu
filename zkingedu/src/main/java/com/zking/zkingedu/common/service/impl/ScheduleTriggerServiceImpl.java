package com.zking.zkingedu.common.service.impl;



import com.zking.zkingedu.common.dao.ScheduleTriggerMapper;
import com.zking.zkingedu.common.dao.ScheduleTriggerParamMapper;
import com.zking.zkingedu.common.model.ScheduleTrigger;
import com.zking.zkingedu.common.model.ScheduleTriggerParam;
import com.zking.zkingedu.common.service.IScheduleTriggerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleTriggerServiceImpl implements IScheduleTriggerService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleTriggerMapper scheduleTriggerMapper;

    @Resource
    private ScheduleTriggerParamMapper scheduleTriggerParamMapper;

    @Scheduled(cron = "* 01 23 * * ?")
    public void refreshScheduler(){
        try {
            //循环获取数据库中的定时调度任务
            List<ScheduleTrigger> scheduleTriggers = scheduleTriggerMapper.queryScheduleTrigger();
            if(null!=scheduleTriggers){
                //循环遍历定时任务
                for (ScheduleTrigger scheduleTrigger : scheduleTriggers) {
                    String cron = scheduleTrigger.getCron();  //表达式
                    String jobName = scheduleTrigger.getJobName(); //任务名称
                    String jobGroup = scheduleTrigger.getJobGroup();//任务分组
                    String status = scheduleTrigger.getStatus();//任务状态

                    //根据jobname和jobGroup生成TriggerKey
                    TriggerKey triggerKey =
                            TriggerKey.triggerKey(jobName, jobGroup);
                    //根据TriggerKey获取scheduler调度器中的触发器
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                    //判断是否存在trigger触发器
                    if(null==trigger){
                        //判断当前任务是否可用
                        if(status.equals("0"))
                            continue;
                        System.out.println("创建调度器....");
                        //创建任务详情
                        JobDetail jobDetail=
                                JobBuilder.newJob((Class<? extends Job>) Class.forName(jobName))
                                .withIdentity(jobName,jobGroup)
                                .build();

                        //往任务中传递参数 TODO
                        JobDataMap jobDataMap = jobDetail.getJobDataMap();
                        List<ScheduleTriggerParam> scheduleTriggerParams =
                                scheduleTriggerParamMapper.queryScheduleParam(scheduleTrigger.getId());
                        for (ScheduleTriggerParam param : scheduleTriggerParams) {
                            jobDataMap.put(param.getName(),param.getValue());
                        }

                        //创建表达式调度器
                        CronScheduleBuilder cronScheduleBuilder =
                                CronScheduleBuilder.cronSchedule(cron);

                        //创建触发器
                        trigger= TriggerBuilder.newTrigger()
                                .withIdentity(jobName,jobGroup)
                                .withSchedule(cronScheduleBuilder)
                                .build();

                        //将JobDetail和Trigger注入到scheduler调度器中
                        scheduler.scheduleJob(jobDetail,trigger);
                    }else{
                        System.out.println("Quartz 调度器中已经存在任务");
                        if(status.equals("0")){
                            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
                            scheduler.deleteJob(jobKey);
                            continue;
                        }
                        //获取当前运行的触发器的表达式
                        String cronExpression = trigger.getCronExpression();
                        if(!cron.equals(cronExpression)){
                            System.out.println(cron+","+cronExpression);
                            //创建表达式调度器
                            CronScheduleBuilder cronScheduleBuilder =
                                    CronScheduleBuilder.cronSchedule(cron);
                            //重构
                            trigger=trigger.getTriggerBuilder()
                                    .withIdentity(triggerKey)
                                    .withSchedule(cronScheduleBuilder)
                                    .build();

                            //刷新调度器
                            scheduler.rescheduleJob(triggerKey,trigger);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
