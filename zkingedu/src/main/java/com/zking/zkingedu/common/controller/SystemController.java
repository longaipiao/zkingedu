package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.LogService;
import com.zking.zkingedu.common.service.ScommentService;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private LogService logService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time = dateFormat.format(new Date());


    @Autowired
    private ScommentService scommentService;

    /**
     * 首页获取体系 12个
     * ajax
     * yan
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/CourseEight")
    public ResultUtil indexgetCourseEight() {
        return systemService.getSystemsResult();
    }


    /**
     * yan
     *
     * @return 点击课程体系 既然体系详情课程页面
     * 获取体系id 查询所有子体系以及子体系下面的课程
     */
    @ResponseBody
    @GetMapping("/resourseShow/{courseId}")
    public ModelAndView coursepathsShow(@PathVariable(value = "courseId") Integer courseId) {
        ModelAndView mv = new ModelAndView();
        System systemBySid = systemService.getSystemBySid(courseId);
        mv.addObject("system", systemBySid);
        List<System> systems = systemService.getsystemsonByFId(courseId);
        mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        mv.addObject("systems", systems);
        mv.setViewName("user/paths/show");
        return mv;
    }


    /**
     * 后台admin 管理课程体系   展示所有课程体系一级   搜索分页
     * 作者：yan
     *
     * @param page
     * @param limit
     * @param system
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSystems")
    @RequiresAuthentication
    public Object getSystemMenu(Integer page, Integer limit, System system) {
        PageBean<System> systemPageBean = new PageBean<>();
        systemPageBean.setPageIndex(page);
        systemPageBean.setPageSize(limit);
        systemPageBean.setT(system);
        return systemService.getAllSystems(systemPageBean);
    }


    /**
     * admin体系添加
     * yan
     *
     * @param system
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSystem")
    @RequiresAuthentication
    public ResultUtil addSystem(System system, HttpServletRequest request) {
        int i = 0;
        try {
            system.setSystemState(0);//状态默认上架
            system.setSystemFid(0);//一级体系  父id默认0
            i = systemService.adminAddSystem(system);
            if (i > 0) {
                //放入日志
                Emp emp = (Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "添加了体系，体系id为：" + system.getSystemID());
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
                return ResultUtil.ok("添加体系成功");
            } else {
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
     *
     * @return
     */
    @RequestMapping("/addSystemPage")
    @RequiresAuthentication
    public String pageAddSystem() {
        return "admin/course/systemAdd";
    }


    /**
     * 修改体系状态接口
     * yan
     *
     * @param state
     * @param systemId
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSystemState")
    @RequiresAuthentication
    public ResultUtil updateSystemState(boolean state, Integer systemId, HttpServletRequest request) {
        try {
            Integer stateid = 0;
            if (state) {
                stateid = 0;
            } else if (!state) {
                stateid = 1;
            }
            int i = systemService.updateSystemState(stateid, systemId);
            if (i > 0) {
                //放入日志
                Emp emp = (Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了体系状态：" + state + "，体系id为：" + systemId);
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
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
     *
     * @return editSystem.html
     * yan
     */
    @RequestMapping("/sysperedit")
    public ModelAndView PreadminEditSystem(@RequestParam("sid") Integer sid) {
        ModelAndView mv = new ModelAndView();

        System system = null;
        try {
            system = systemService.getSystemBySid(sid);
        } catch (Exception e) {
            system.setSystemName("数据异常");
            e.printStackTrace();
        }
        mv.addObject("system", system);
        mv.setViewName("admin/course/systemEdit");
        return mv;
    }


    /**
     * admin 修改体系
     *
     * @param system
     * @return yan
     */
    @ResponseBody
    @RequestMapping("/editSystem")
    @RequiresAuthentication
    public ResultUtil updateSystem(System system, HttpServletRequest request) {
        int i = systemService.updateSystem(system);
        if (i > 0) {
            //放入日志
            Emp emp = (Emp) request.getSession().getAttribute("emp");
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了体系，体系id为：" + system.getSystemID());
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return ResultUtil.ok("修改成功");
        }
        return ResultUtil.error("您的操作过于频繁....");
    }


    /**
     * 跳转至课程 阶段管理页面
     *
     * @return yan
     */
    @RequestMapping("/pageStageManager")
    @RequiresAuthentication
    public ModelAndView pageStageManager(@RequestParam("systemID") Integer systemID) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("id", systemID);
        mv.setViewName("admin/course/stage/stageManger");
        return mv;
    }


    /**
     * yan
     * admin管理体系阶段
     *
     * @param sid 体系id
     * @return 返回体系对应的阶段数据
     */
    @ResponseBody
    @RequestMapping("/getSystemStages")
    @RequiresAuthentication
    public ResultUtil getSystemStages(@RequestParam("sid") Integer sid, Integer page, Integer limit) {
        PageBean<Integer> pageBean = new PageBean<>();
        pageBean.setT(sid);
        pageBean.setPageIndex(page);
        pageBean.setPageSize(limit);
        return systemService.getsystemsonByFId(pageBean);
    }


    /**
     * admin  添加体系阶段
     *
     * @param fid       阶段父id
     * @param stageName 阶段名称
     * @return yan
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/addSystemStage")
    @RequiresAuthentication
    public ResultUtil adminAddSystemStage(@RequestParam("fid") Integer fid, @RequestParam("stageName") String stageName, HttpServletRequest request) {
        int i = 0;
        try {
            i = systemService.addSystemStage(fid, stageName);
            if (i > 0) {
                //放入日志
                Emp emp = (Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "添加了体系阶段，体系父id为：" + fid + "，体系阶段名为" + stageName);
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
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
     *
     * @param sid       阶段id
     * @param stageName 名称
     * @return result
     * yan
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateStageName")
    @RequiresAuthentication
    public ResultUtil adminUpdateStageName(@RequestParam("sid") Integer sid, @RequestParam("stageName") String stageName, HttpServletRequest request) {
        int i = 0;
        try {
            i = systemService.updateSystemAndStageBySid(sid, stageName);
            if (i > 0) {
                //放入日志
                Emp emp = (Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了体系阶段名，体系阶段id为：" + sid);
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
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
    @RequiresAuthentication
    public ResultUtil getSysMenu() {
        return ResultUtil.ok(systemService.getSystemAndStageMenu());
    }


    /**
     * 删除体系
     * yan
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSys")
    @RequiresAuthentication
    public ResultUtil delSystemById(Integer sid){
        try {
            //1.查询体系下面阶段数量
            Integer num = systemService.selSysByFidNum(sid);
            if(num>0){
                return ResultUtil.error("该体系下存在阶段数据，无法直接删除");
            }
            else {//体系下面没有阶段
                int i = systemService.delSystemById(sid);
                if(i>0){
                    return ResultUtil.ok("操作成功");
                }
                else {
                    return ResultUtil.ok("您的操作过于频繁...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }



    /**
     * 删除体系阶段
     * yan
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSysByStage")
    @RequiresAuthentication
    public ResultUtil delSystemAndStageById(Integer sid){
        try {
            //1.查询体系下面阶段数量
            Integer n = systemService.selStageAndCourseNum(sid);
            if(n>0){
                return ResultUtil.error("该阶段下存在课程数据，无法直接删除");
            }
            else {//体系下面没有阶段
                int i = systemService.delSystemById(sid);
                if(i>0){
                    return ResultUtil.ok("操作成功");
                }
                else {
                    return ResultUtil.ok("您的操作过于频繁...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

}
