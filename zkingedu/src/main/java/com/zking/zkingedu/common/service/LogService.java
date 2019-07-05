package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Log;

import java.util.List;
import java.util.Map;

import com.zking.zkingedu.common.model.Log;

/**
 * 日志接口
 */
public interface LogService {

    /*
     * 查询所以的日志消息
     */
    public PageInfo<Map<String,Object>> findLog(Log log,Integer page,Integer limit);




    /**
     * 增加日志
     */
    int addLog(Log log);

}
