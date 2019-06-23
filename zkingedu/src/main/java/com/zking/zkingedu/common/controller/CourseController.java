package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 在体系页面选择课程 点击跳转至课程详情页面
     * @return
     */
    @RequestMapping("/showCourse")
    public ModelAndView SystemRequestCourse(Integer sid){
        ModelAndView mv = new ModelAndView();
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

}
