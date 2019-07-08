package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.ScommentDao;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.ScommentService;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @Autowired
    private ScommentService scommentService;

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
//        log.info("============================体系信息:"+systemBySid);
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


    /**
     * admin体系添加
     * yan
     * @param system
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSystem")
    public ResultUtil addSystem(System system) {
        int i = 0;
        try {
            system.setSystemState(0);//状态默认上架
            system.setSystemFid(0);//一级体系  父id默认0
            i = systemService.adminAddSystem(system);
            if (i > 0) {
                return ResultUtil.ok("添加体系成功");
            }
            else{
                return ResultUtil.error("您的操作过于频繁");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("您的操作过于频繁");
        }
    }


    /**
     * 进入体系添加页面
     * yan
     * @return
     */
    @RequestMapping("/addSystemPage")
    public String pageAddSystem(){
        return "/admin/course/systemAdd";
    }


    /**
     * 修改体系状态接口
     * yan
     * @param state
     * @param systemId
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSystemState")
    public ResultUtil updateSystemState(boolean state,Integer systemId){
        try {
            Integer stateid=0;
            if(state){
                stateid=0;
            }
            else if (!state){
                stateid=1;
            }
            int i = systemService.updateSystemState(stateid, systemId);
            if(i>0){
                return ResultUtil.ok("修改成功");
            }
            return ResultUtil.error("您的操作过于频繁，请稍后再试...");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("您的操作过于频繁，请稍后再试...");
        }
    }


    /**
     * admin
     * 修改 之前表单数据加载
     * @return  editSystem.html
     * yan
     */
    @RequestMapping("/sysperedit")
    public ModelAndView PreadminEditSystem(@RequestParam("sid")Integer sid){
        ModelAndView mv = new ModelAndView();

        System system = null;
        try {
            system = systemService.getSystemBySid(sid);
        } catch (Exception e) {
            system.setSystemName("数据异常");
            e.printStackTrace();
        }
        mv.addObject("system",system);
        mv.setViewName("/admin/course/systemEdit");
        return mv;
    }


    /**
     * admin 修改体系
     * @param system
     * @return
     * yan
     */
    @ResponseBody
    @RequestMapping("/editSystem")
    public ResultUtil updateSystem(System system){
//        log.info("接收的数据：==============================="+system);
        int i = systemService.updateSystem(system);
        if(i>0){
            return ResultUtil.ok("修改成功");
        }
        return ResultUtil.error("您的操作过于频繁....");
    }


    /**
     * 跳转至课程 阶段管理页面
     * @return
     * yan
     */
    @RequestMapping("/pageStageManager")
    public ModelAndView pageStageManager(@RequestParam("systemID")Integer systemID){
        ModelAndView mv = new ModelAndView();

        mv.addObject("id",systemID);
        mv.setViewName("/admin/course/stage/stageManger");
        return mv;
    }


    /**
     * yan
     * admin管理体系阶段
     * @param sid  体系id
     * @return  返回体系对应的阶段数据
     *
     */
    @ResponseBody
    @RequestMapping("/getSystemStages")
    public ResultUtil getSystemStages(@RequestParam("sid") Integer sid,Integer page,Integer limit){
        PageBean<Integer> pageBean = new PageBean<>();
        pageBean.setT(sid);
        pageBean.setPageIndex(page);
        pageBean.setPageSize(limit);
        return systemService.getsystemsonByFId(pageBean);
    }


    /**
     * admin  添加体系阶段
     * @param fid  阶段父id
     * @param stageName  阶段名称
     * @return
     * yan
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/addSystemStage")
    public ResultUtil adminAddSystemStage(@RequestParam("fid")Integer fid,@RequestParam("stageName")String stageName){
        int i=0;
        try {
            i = systemService.addSystemStage(fid, stageName);
            if(i>0){
                return ResultUtil.ok("添加成功");
            }
            return ResultUtil.error("系统繁忙，请稍后再试");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("您的操作过于频繁");
        }
    }


    /**
     * admin
     * @param sid  阶段id
     * @param stageName  名称
     * @return  result
     * yan
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateStageName")
    public  ResultUtil adminUpdateStageName(@RequestParam("sid")Integer sid,@RequestParam("stageName")String stageName){
        int i=0;
        try {
            i = systemService.updateSystemAndStageBySid(sid, stageName);
            if(i>0){
                return ResultUtil.ok("修改成功");
            }
            return ResultUtil.error("您的操作过于频繁");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("您的操作过于频繁");
        }
    }



    @ResponseBody
    @RequestMapping("/getSystemAndStageMenu")
    public ResultUtil getSysMenu(){
        return ResultUtil.ok(systemService.getSystemAndStageMenu());
    }




}
