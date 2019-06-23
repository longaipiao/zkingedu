package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 14:05
 */
@Controller
public class ToolController {

    @Autowired
    private ToolService toolService;

    /**
     * 查询所有的工具信息
     * @return
     */
    @RequestMapping(value = "/findTool")
    @ResponseBody
    public Map<String,Object> findTool(Integer page, Integer pageSize){
        System.out.println("我想你了");
        PageInfo<Tool> tools = toolService.findAll(page, pageSize);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",tools.getTotal());
        maps.put("data",tools.getList());
        return maps;
    }

}
