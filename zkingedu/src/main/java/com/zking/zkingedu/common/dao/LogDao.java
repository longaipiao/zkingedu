package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Log;

/**
 * 日志接口
 */
public interface LogDao {

    /**
     * 增加日志
     */
    int addLog(Log log);
}
