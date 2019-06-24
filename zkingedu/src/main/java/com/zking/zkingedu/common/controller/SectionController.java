package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.model.Video;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SectionService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 章节视频  控制器
 * 颜
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class SectionController {


    @Autowired
    private SectionService sectionService;

    @Autowired
    private VideoService videoService;



    @Autowired
    private CourseService courseService;

    /**
     * 用户点击章节开始学习  视频播放
     * @param sid 课程id
     * @param id    章节id
     * @return
     * 作者：颜
     */
    @RequestMapping("/video")
    public ModelAndView videoPlayer(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "id") Integer id){
        ModelAndView mv = new ModelAndView();

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);

        //根据章节id获取视频信息
        Video videoById = videoService.getVideoById(id);

        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);

        mv.addObject("course",courseBYcourseID);
        mv.addObject("video",videoById);
        mv.addObject("sections",sectionsBycid);
        mv.setViewName("/user/courses/show");
        return mv;
    }

}
