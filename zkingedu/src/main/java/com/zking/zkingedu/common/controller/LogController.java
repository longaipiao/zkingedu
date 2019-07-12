package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/findlog")
    @ResponseBody
    public Map<String, Object> findLog(Log log, Integer page, Integer limit) {
        System.out.println("**********开始查询日志消息**********");
        PageInfo<Map<String, Object>> logs = logService.findLog(log, page, limit);
        Map<String, Object> maps = new HashMap<>();
        maps.put("msg", "success");
        maps.put("code", 0);
        maps.put("count", logs.getTotal());
        maps.put("data", logs.getList());
        return maps;
    }


}
