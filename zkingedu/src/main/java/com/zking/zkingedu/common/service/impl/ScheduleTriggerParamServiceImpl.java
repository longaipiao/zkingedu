package com.zking.zkingedu.common.service.impl;



import com.zking.zkingedu.common.dao.ScheduleTriggerParamMapper;
import com.zking.zkingedu.common.model.ScheduleTriggerParam;
import com.zking.zkingedu.common.service.IScheduleTriggerParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * quratz
 */
@Service
public class ScheduleTriggerParamServiceImpl implements IScheduleTriggerParamService {

    @Resource
    private ScheduleTriggerParamMapper scheduleTriggerParamMapper;

    @Override
    public List<ScheduleTriggerParam> queryScheduleParam(Integer triggerId) {
        return scheduleTriggerParamMapper.queryScheduleParam(triggerId);
    }
}
