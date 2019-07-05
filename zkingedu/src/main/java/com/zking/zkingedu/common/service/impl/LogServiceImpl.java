package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.LogDao;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.dao.LogDao;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 日志接口  实现层
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;


    /*
     * 查询所以的日志消息
     */
    @Override
    public PageInfo<Map<String, Object>> findLog(Log log, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> logs = logDao.findLog(log);
        return new PageInfo<>(logs);
    }

    @Resource
    private LogDao logDao;

    @Override
    public int addLog(Log log) {
        return logDao.addLog(log);
    }
}
