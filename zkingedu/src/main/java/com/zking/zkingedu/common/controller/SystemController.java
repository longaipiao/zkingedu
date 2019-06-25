package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 体系conreoller
 * 作者 颜
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class SystemController {


    @Autowired
    private SystemService systemService;


    /**
     * 首页获取体系 12个
     * ajax
     * yan
     * @return
     */
    @ResponseBody
    @RequestMapping("/CourseEight")
    public ResultUtil indexgetCourseEight(){
        return systemService.getSystemsResult();
    }


    /**
     * yan
     * @return
     * 点击课程体系 既然体系详情课程页面
     * 获取体系id 查询所有子体系以及子体系下面的课程
     */
    @ResponseBody
    @RequestMapping("/resourseShow")
    public ModelAndView coursepathsShow(Integer courseId){
        ModelAndView mv = new ModelAndView();
        System systemBySid = systemService.getSystemBySid(courseId);
        mv.addObject("system",systemBySid);
        log.info("============================体系信息:"+systemBySid);
        List<System> systems = systemService.getsystemsonByFId(courseId);
        mv.addObject("systems",systems);
        mv.setViewName("/user/paths/show");
        return mv;
    }


    /**
     * 后台admin 管理课程体系   展示所有课程体系一级   搜索分页
     * 作者：yan
     * @param page
     * @param limit
     * @param system
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSystems")
    public Object getSystemMenu(Integer page,Integer limit,System system){
        PageBean<System> systemPageBean = new PageBean<>();
        systemPageBean.setPageIndex(page);
        systemPageBean.setPageSize(limit);
        systemPageBean.setT(system);
        return  systemService.getAllSystems(systemPageBean);
    }

}
