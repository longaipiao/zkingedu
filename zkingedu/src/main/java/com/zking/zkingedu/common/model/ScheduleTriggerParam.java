package com.zking.zkingedu.common.model;

public class ScheduleTriggerParam {
    private Integer paramId;

    private String name;

    private String value;

    private Integer scheduleTriggerId;

    public ScheduleTriggerParam(Integer paramId, String name, String value, Integer scheduleTriggerId) {
        this.paramId = paramId;
        this.name = name;
        this.value = value;
        this.scheduleTriggerId = scheduleTriggerId;
    }

    public ScheduleTriggerParam() {
        super();
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getScheduleTriggerId() {
        return scheduleTriggerId;
    }

    public void setScheduleTriggerId(Integer scheduleTriggerId) {
        this.scheduleTriggerId = scheduleTriggerId;
    }
}