package com.zking.zkingedu.common.service;


import com.zking.zkingedu.common.model.ScheduleTriggerParam;

import java.util.List;

public interface IScheduleTriggerParamService {
    List<ScheduleTriggerParam> queryScheduleParam(Integer triggerId);
}