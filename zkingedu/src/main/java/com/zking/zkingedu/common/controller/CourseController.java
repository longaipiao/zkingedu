package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.OrderService;
import com.zking.zkingedu.common.service.SectionService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 在体系页面选择课程 点击跳转至课程详情页面
     * @return sid 是课程ID
     */
    @RequestMapping("/showCourse")
    public ModelAndView SystemRequestCourse(Integer sid, HttpServletRequest request, Model model){
        ModelAndView mv = new ModelAndView();
        //获取登录过来的用户积分    需要后期改变用户ID
        int integrsl = userService.findIntegrsl(2);
        System.err.println("用户的积分是："+integrsl);
        int courseIntegrsl = courseService.findCourseIntegrsl(sid);
        System.err.println("整套课程的积分是："+courseIntegrsl);

        request.getSession().setAttribute("userintegrsl",integrsl);
        request.getSession().setAttribute("courseIntegrsl",courseIntegrsl);

        //查询订单表中是否存在用户id
        Integer userID = orderService.findUserID(2);
        System.err.println("订单中查询出的用户id是："+userID);
        //查询订单表中是否存在课程id
        Integer courseID1 = orderService.findCourseID(sid);
        System.err.println("订单中查询出的课程id是："+courseID1);

        //存入model里面，前台获取
        model.addAttribute("courseid",courseID1);//课程id
        model.addAttribute("userid",userID);//用户id


        //课程id
        model.addAttribute("courseId",sid);
        System.err.println("课程id是："+sid);
        model.addAttribute("userId",2);
        System.err.println("用户id是"+2);

        //单个用户的积分
        mv.addObject("userintegrsl",integrsl);
        //整套课程的积分
        mv.addObject("courseIntegrsl",courseIntegrsl);

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);


        //课程点击量增加 yan
        courseBYcourseID.setCourseNum(courseBYcourseID.getCourseNum()+1);
        courseService.updatecliNum(sid,courseBYcourseID.getCourseNum());


        mv.addObject("sections",sectionsBycid);
        mv.addObject("course",courseBYcourseID);
        mv.setViewName("/user/courses/show1");
        return mv;
    }


    /**
     * 用户点击进入course课程搜索页面
     * @return
     */
    @RequestMapping("/courseSearch")
    public ModelAndView courseSearch(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/user/courses/index");
        return mv;
    }


    /**
     * 用户课程搜索页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchCourse")
    public ResultUtil userCourseSearch(Course course,Integer page,Integer limit){
//        log.info("===========================page:"+page+"____limit:"+limit);
        PageBean<Course> pageBean = new PageBean<Course>();
        pageBean.setT(course);
        pageBean.setPageIndex(page);//当前页
        pageBean.setPageSize(limit);//没页多少条
        System.err.println("数据接收：=============================="+pageBean);
       return courseService.SearchCourse(pageBean);
    }


    /**
     * index首页获取最热课程四个
     * yan
     * @return
     */
    @ResponseBody
    @RequestMapping("/indexGetFour")
    public ResultUtil indexgetCoursefour(){
        List<Course> coursefour;
        try {
            coursefour = courseService.getCoursefour();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500,e.getMessage());
        }
        return new ResultUtil(200,coursefour);
    }


    /**
     * admin 进入课程管理页面
     * yan
     * @return
     */
    @RequestMapping("/stageManager")
    public String StageManager(){
        return "admin/course/courseManager";
    }


    /**
     * admin 查询所有的课程信息  分页 查询
     * yan
     * @param page
     * @param limit
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCourses")
    public ResultUtil getCourses(Integer page,Integer limit,Course course){
        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setPageSize(limit);
        pageBean.setPageIndex(page);
        pageBean.setT(course);
        return courseService.getAllCourses(pageBean);
    }


    /**
     * 跳转至  添加课程页面
     * yan
     * @return
     */
    @RequestMapping(value = "/addCoursePage")
    public String enterAddCoursePage(){
        return "admin/course/courseAdd";
    }


    /**
     * yan
     * 管理员添加课程 信息
     * @param course
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping("/addCourse")
    public ResultUtil adminAddCourse(Course course){
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
            if(i>0){
                return ResultUtil.ok("添加课程成功");
            }
            else {
                return ResultUtil.error("您的操作过于频繁");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 根据课程id  返回对应的课程信息
     * @param sid
     * @return
     * yan
     * yan
     */
    @ResponseBody
    @RequestMapping("/getCourseBySid")
    public ResultUtil getCourseById(@RequestParam(value = "sid") Integer sid){
        return ResultUtil.ok(courseService.getCourseBySid(sid));
    }


    /**
     * 修改课程之前跳转页面 加载数据
     * yan
     * @param sid
     * @return
     */
    @RequestMapping("/PreUpCourseloaddata")
    public ModelAndView editPerUpCourse(@RequestParam(value = "sid") Integer sid){
        ModelAndView mv = new ModelAndView();
        //加载数据
        mv.addObject("course",courseService.getCourseBySid(sid));

        mv.setViewName("admin/course/courseEdit");
        return mv;
    }


    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/editCourse")
    public ResultUtil editCourse(Course course){
//        System.err.println("====================后台接收的到数据:"+course);
        try {
            int i = courseService.updateCourse(course);
            if(i>0){
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
     * @param id
     * @param state
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/editCourseState")
    public ResultUtil editCourseState(@RequestParam(value = "id")Integer id,@RequestParam("state")boolean state){
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
                return ResultUtil.ok("修改课程状态成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.error(e.getMessage());
        }
        return ResultUtil.error("您的操作过于频繁，请稍后再试....");
    }













}
