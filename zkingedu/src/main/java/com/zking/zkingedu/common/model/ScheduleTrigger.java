package com.zking.zkingedu.common.model;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class ScheduleTrigger implements Serializable {
    private Integer id;

    private String cron;

    private String status;

    private String jobName;

    private String jobGroup;

    public ScheduleTrigger(Integer id, String cron, String status, String jobName, String jobGroup) {
        this.id = id;
        this.cron = cron;
        this.status = status;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
    }

    public ScheduleTrigger() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
}