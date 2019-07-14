package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.LogService;
import com.zking.zkingedu.common.service.ToolService;
import com.zking.zkingedu.common.utils.ResponseUtil;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 14:05
 */
@Controller
@Slf4j
public class ToolController {

    @Autowired
    private ToolService toolService;

    @Autowired
    private LogService logService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time=dateFormat.format(new Date());

    /**
     * 查询所有的工具信息
     * @return
     */
    @RequestMapping(value = "/findTool")
    @ResponseBody
    public Map<String,Object> findTool(Tool tool,Integer page, Integer limit){
        PageInfo<Tool> tools = toolService.findAll(tool,page, limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",tools.getTotal());
        maps.put("data",tools.getList());
        return maps;
    }


    /**
     * 增加工具的方法
     */
    @RequestMapping(value = "/User/addTool")
    public void addTool(Tool tool, HttpServletResponse response,HttpServletRequest request) throws Exception {
        tool.setToolTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        //log.info("*************开始增加工具的方法*************");
        int t = toolService.addTool(tool);
        //放入日志
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        mylog.setEmp(emp);
        mylog.setLogTime(time);
        StringBuilder stringBuilder = new StringBuilder(emp.getEmpName()+"添加了工具，工具id为："+tool.getToolID());
        mylog.setLogDetails(stringBuilder.toString());
        logService.addLog(mylog);
        //放入日志结束
        ResponseUtil.write(response,t);
        //log.info("***********结束增加工具的方法**************");
    }

    /**
     * 根据工具id获取工具图片
     */
    @RequestMapping(value = "/findImg")
    public ModelAndView findImg(Integer toolID, HttpServletResponse response) throws Exception {
        //log.info("************开始查询图片的方法***********");
        ModelAndView mv = new ModelAndView();
        Tool tool = toolService.findImg(toolID);
        mv.addObject("tools",tool);
        mv.setViewName("admin/html/integral-list");
        //log.info("************结束查询图片的方法***********");
        return mv;
    }

    /**
     * 修改工具的方法
     */
    @RequestMapping(value = "/User/updateTool")
    public void updateTool(Tool tool,HttpServletResponse response,HttpServletRequest request) throws Exception {
        //log.info("************开始修改工具的方法****************");
        tool.setToolTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        int tool1 = toolService.updateTool(tool);
        //放入日志
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        mylog.setEmp(emp);
        mylog.setLogTime(time);
        StringBuilder stringBuilder = new StringBuilder(emp.getEmpName()+"修改了工具，工具id为："+tool.getToolID());
        mylog.setLogDetails(stringBuilder.toString());
        logService.addLog(mylog);
        //放入日志结束
        ResponseUtil.write(response,tool1);
        //log.info("************结束修改工具的方法****************");
    }



}
