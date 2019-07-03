package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.LogDao;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日志接口  实现层
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public int addLog(Log log) {
        return logDao.addLog(log);
    }
}
