package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.*;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 课程 控制器
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class CourseController {

    //章节的service
    @Autowired
    private SectionService sectionService;

    //课程的service
    @Autowired
    private CourseService courseService;

    //用户的service
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ScommentService scommentService;


    @Autowired
    private HoardingService hoardingService;


    @Autowired
    private LogService logService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time = dateFormat.format(new Date());





    /**
     * 查询课程需要的值
     * @param sid 课程id
     * @param request
     * @param model
     * @param mv
     * @return
     */
    /*@RequestMapping("/showCourse1")
    public String findUserDenglu(Integer sid, HttpServletRequest request, Model model,ModelAndView mv){

        //获取登录过来的用户积分    需要后期改变用户ID

        Integer integrsl = userService.findIntegrsl(SessionUtil.getUserById());

        return null;
    }*/






    /**
     * 在体系页面选择课程 点击跳转至课程详情页面
     *
     * @return sid 是课程ID
     */
    @RequestMapping("/showCourse/{sid}")
    public ModelAndView SystemRequestCourse(@PathVariable(value = "sid") Integer sid, HttpServletRequest request, Model model, ModelAndView mv){

        User u = (User) request.getSession().getAttribute("user");
        if(u!=null){

        Integer courseIntegrsl = courseService.findCourseIntegrsl(sid);

        request.getSession().setAttribute("userintegrsl", u.getUserIntegrsl());
        request.getSession().setAttribute("courseIntegrsl", courseIntegrsl);

        //课程id
        model.addAttribute("courseId", sid);
        model.addAttribute("userId", SessionUtil.getUserById());

        //单个用户的积分
        mv.addObject("userintegrsl", u.getUserIntegrsl());
        //整套课程的积分
        mv.addObject("courseIntegrsl", courseIntegrsl);

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);


        //课程点击量增加 yan
        courseBYcourseID.setCourseNum(courseBYcourseID.getCourseNum() + 1);
        courseService.updatecliNum(sid, courseBYcourseID.getCourseNum());

        User user = new User();
        user.setUserID(SessionUtil.getUserById());
        mv.addObject("user", user);//模拟登陆
        mv.addObject("sections", sectionsBycid);
        mv.addObject("section", null);//视频id默认为空
        mv.addObject("course", courseBYcourseID);

        mv.addObject("scommentNum", scommentService.getScommentAndCousecNumber(sid));//课程评论数量
        mv.addObject("courseNum", hoardingService.getCourseNumber(sid));//查询课程 下面有多少人收藏 统计
        mv.addObject("isCheck", hoardingService.getHoardingByUidAndCid(user.getUserID(), sid));//用户id'  课程id'
        mv.addObject("coursefours", courseService.getCoursefour());//获取最热的四个课程（播放量）

        mv.setViewName("user/courses/show1");
            return mv;
        }else{
            //获取所有的章节视频
            List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
            //获取课程信息
            Course courseBYcourseID = courseService.getCourseBYcourseID(sid);


            //课程点击量增加 yan
            courseBYcourseID.setCourseNum(courseBYcourseID.getCourseNum() + 1);
            courseService.updatecliNum(sid, courseBYcourseID.getCourseNum());

            User user = new User();
            user.setUserID(SessionUtil.getUserById());
            mv.addObject("user", user);//模拟登陆
            mv.addObject("sections", sectionsBycid);
            mv.addObject("section", null);//视频id默认为空
            mv.addObject("course", courseBYcourseID);
            mv.addObject("scommentNum", scommentService.getScommentAndCousecNumber(sid));//课程评论数量
            mv.addObject("courseNum", hoardingService.getCourseNumber(sid));//查询课程 下面有多少人收藏 统计
            mv.addObject("isCheck", hoardingService.getHoardingByUidAndCid(user.getUserID(), sid));//用户id'  课程id'
            mv.addObject("coursefours", courseService.getCoursefour());//获取最热的四个课程（播放量）

            mv.setViewName("user/courses/show1");
            return mv;
        }
    }


    /**
     * 重载  点击章节观看视频
     * yan
     *
     * @param sid
     * @param id
     * @return
     */
    @RequestMapping("/showVideo/{sid}/{id}")
    public ModelAndView SystemRequestCourse(@PathVariable(value = "sid") Integer sid, @PathVariable(value = "id") Integer id, HttpServletRequest request, Model model){
        ModelAndView mv = new ModelAndView();
        //获取登录过来的用户积分    需要后期改变用户ID
        //int integrsl = userService.findIntegrsl(SessionUtil.getUserById());
        User u = (User) request.getSession().getAttribute("user");
        if(u!=null){


        int courseIntegrsl = courseService.findCourseIntegrsl(sid);

        request.getSession().setAttribute("userintegrsl",u.getUserIntegrsl());
        request.getSession().setAttribute("courseIntegrsl",courseIntegrsl);


        //课程id
        model.addAttribute("courseId",sid);
        model.addAttribute("userId",SessionUtil.getUserById());

        //单个用户的积分
        mv.addObject("userintegrsl",u.getUserIntegrsl());

        //整套课程的积分
        mv.addObject("courseIntegrsl",courseIntegrsl);

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);

        //根据章节id获取视频信息
        //Video videoById = videoService.getVideoById(id);
        Section section = sectionService.getSectionById(id);

        User user = new User();
        user.setUserID(SessionUtil.getUserById());
        mv.addObject("user",user);//模拟登陆
        mv.addObject("sections",sectionsBycid);
        mv.addObject("section",section);//视频id默认为空
        mv.addObject("course",courseBYcourseID);
        mv.addObject("scommentNum", scommentService.getScommentAndCousecNumber(sid));//课程评论数量
        mv.addObject("courseNum",hoardingService.getCourseNumber(sid));//查询课程 下面有多少人收藏 统计
        mv.addObject("isCheck",hoardingService.getHoardingByUidAndCid(user.getUserID(),sid));//用户id'  课程id'
        mv.addObject("coursefours",courseService.getCoursefour());//获取最热的四个课程（播放量）

        mv.setViewName("user/courses/show1");
        return mv;
        }else{
            //获取所有的章节视频
            List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
            //获取课程信息
            Course courseBYcourseID = courseService.getCourseBYcourseID(sid);

            //根据章节id获取视频信息
            //Video videoById = videoService.getVideoById(id);
            Section section = sectionService.getSectionById(id);

            User user = new User();
            user.setUserID(SessionUtil.getUserById());
            mv.addObject("user",user);//模拟登陆
            mv.addObject("sections",sectionsBycid);
            mv.addObject("section",section);//视频id默认为空
            mv.addObject("course",courseBYcourseID);
            mv.addObject("scommentNum", scommentService.getScommentAndCousecNumber(sid));//课程评论数量
            mv.addObject("courseNum",hoardingService.getCourseNumber(sid));//查询课程 下面有多少人收藏 统计
            mv.addObject("isCheck",hoardingService.getHoardingByUidAndCid(user.getUserID(),sid));//用户id'  课程id'
            mv.addObject("coursefours",courseService.getCoursefour());//获取最热的四个课程（播放量）

            mv.setViewName("user/courses/show1");
            return mv;
        }
    }



    @Autowired
    private SystemService systemService;
    /**
     * 用户点击进入course课程搜索页面
     *
     * @return
     */
    @RequestMapping("/courseSearch")
    public ModelAndView courseSearch(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        mv.setViewName("user/courses/index");
        return mv;
    }


    /**
     * 用户课程搜索页面
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchCourse")
    public ResultUtil userCourseSearch(Course course, Integer page, Integer limit) {
//        log.info("===========================page:"+page+"____limit:"+limit);
        PageBean<Course> pageBean = new PageBean<Course>();
        pageBean.setT(course);
        pageBean.setPageIndex(page);//当前页
        pageBean.setPageSize(limit);//没页多少条
        return courseService.SearchCourse(pageBean);
    }


    /**
     * index首页获取最热课程四个
     * yan
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/indexGetFour")
    public ResultUtil indexgetCoursefour() {
        List<Course> coursefour;
        try {
            coursefour = courseService.getCoursefour();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500, e.getMessage());
        }
        return new ResultUtil(200, coursefour);
    }


    /**
     * admin 进入课程管理页面
     * yan
     *
     * @return
     */
    @RequestMapping("/stageManager")
    public String StageManager() {
        return "admin/course/courseManager";
    }


    /**
     * admin 查询所有的课程信息  分页 查询
     * yan
     *
     * @param page
     * @param limit
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCourses")
    public ResultUtil getCourses(Integer page, Integer limit, Course course) {
        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setPageSize(limit);
        pageBean.setPageIndex(page);
        pageBean.setT(course);
        return courseService.getAllCourses(pageBean);
    }


    /**
     * 跳转至  添加课程页面
     * yan
     *
     * @return
     */
    @RequestMapping(value = "/addCoursePage")
    public String enterAddCoursePage() {
        return "admin/course/courseAdd";
    }


    /**
     * yan
     * 管理员添加课程 信息
     *
     * @param course
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping("/addCourse")
    public ResultUtil adminAddCourse(Course course) {
        try {
            //获取当前员工信息
            Emp emp = SessionUtil.getEmp();
            //设置员工id
            course.setCourseEid(emp.getEmpID());
            //默认状态 上架 0
            course.setCourseState(0);
            //设置免费章节数默认0
            course.setCourseFree(0);
            //设置默认播放量默认0
            course.setCourseNum(0);
            //开始添加
            int i = courseService.addCourse(course);
            if (i > 0) {
                return ResultUtil.ok("添加课程成功");
            } else {
                return ResultUtil.error("您的操作过于频繁");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 根据课程id  返回对应的课程信息
     *
     * @param sid
     * @return yan
     * yan
     */
    @ResponseBody
    @RequestMapping("/getCourseBySid")
    public ResultUtil getCourseById(@RequestParam(value = "sid") Integer sid) {
        return ResultUtil.ok(courseService.getCourseBySid(sid));
    }


    /**
     * 修改课程之前跳转页面 加载数据
     * yan
     *
     * @param sid
     * @return
     */
    @RequestMapping("/PreUpCourseloaddata")
    public ModelAndView editPerUpCourse(@RequestParam(value = "sid") Integer sid) {
        ModelAndView mv = new ModelAndView();
        //加载数据
        mv.addObject("course", courseService.getCourseBySid(sid));

        mv.setViewName("admin/course/courseEdit");
        return mv;
    }


    /**
     * 修改课程信息
     *
     * @param course
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/editCourse")
    public ResultUtil editCourse(Course course, HttpServletRequest request) {
        try {
            int i = courseService.updateCourse(course);
            if (i > 0) {
                //放入日志
                Emp emp = (Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了课程信息，课程ID为：" + course.getCourseID());
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
                return ResultUtil.ok("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.error("您的操作过于频繁");
    }


    /**
     * 修改课程状态 修改课程状态  0显示  1影藏
     *
     * @param id
     * @param state
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/editCourseState")
    public ResultUtil editCourseState(@RequestParam(value = "id") Integer id, @RequestParam("state") boolean state, HttpServletRequest request) {
        try {
            Integer sid;
            if(state){
                sid=0;
            }
            else{
                sid=1;
            }
            int i = courseService.editCourseState(id, sid);
            if(i>0){
                //放入日志
                Emp emp =(Emp) request.getSession().getAttribute("emp");
                mylog.setEmp(emp);
                mylog.setLogTime(time);
                StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了课程状态，课程ID为："+id);
                mylog.setLogDetails(stringBuilder.toString());
                logService.addLog(mylog);
                //放入日志结束
                return ResultUtil.ok("修改课程状态成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.error(e.getMessage());
        }
        return ResultUtil.error("您的操作过于频繁，请稍后再试....");
    }


    /**
     * 根据课程id  删除课程  如果该课程下面有章节信息，无法删除
     * yan
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCourse")
    public ResultUtil delCourseById(Integer cid){
        try {
            //1.查询该课程下面是否存在章节信息
            Integer num = courseService.getCourseAndSectionNum(cid);
            if(num>0){
                return ResultUtil.error("该课程下操作课程章节，无法删除");
            }
            else {
                //没有章节  删除课程
                int i = courseService.delCourse(cid);
                if(i>0){
                    return ResultUtil.ok("课程删除成功");
                }
                else{
                    return ResultUtil.ok("您的操作过于频繁");
                }
            }
        } catch (Exception e) {
            return new ResultUtil(500,"您的操作过于频繁",e.getMessage());
        }
    }









}
