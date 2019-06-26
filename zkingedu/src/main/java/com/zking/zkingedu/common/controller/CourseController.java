package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SectionService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import com.zking.zkingedu.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @Autowired
    private SectionService sectionService;

    @Autowired
    private CourseService courseService;


    @Autowired
    private UserService userService;

    /**
     * 在体系页面选择课程 点击跳转至课程详情页面
     * @return sid 是课程ID
     */
    @RequestMapping("/showCourse")
    public ModelAndView SystemRequestCourse(Integer sid,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        //获取登录过来的用户积分    需要后期改变用户ID
        int integrsl = userService.findIntegrsl(2);
        System.err.println("用户的积分是："+integrsl);
        int courseIntegrsl = courseService.findCourseIntegrsl(sid);
        System.err.println("整套课程的积分是："+courseIntegrsl);

        request.getSession().setAttribute("userintegrsl",integrsl);
        request.getSession().setAttribute("userintegrsl",integrsl);

        //单个用户的积分
        mv.addObject("userintegrsl",integrsl);
        //整套课程的积分
        mv.addObject("courseIntegrsl",courseIntegrsl);

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);
        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);

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
        log.info("===========================page:"+page+"____limit:"+limit);
        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setT(course);
        pageBean.setPageIndex(page);//当前页
        pageBean.setPageSize(limit);//没页多少条
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






}
