package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Log;

import com.zking.zkingedu.common.model.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志接口
 */
public interface LogDao {

    /*
     * 查询所以的日志消息
     */
    public List<Map<String,Object>> findLog(Log log);



    /**
     * 增加日志
     */
    int addLog(Log log);

    /**
     * 定时删除所有日志
     * @return
     */
    public int deleLog();

}
