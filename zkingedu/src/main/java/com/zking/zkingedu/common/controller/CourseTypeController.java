package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 课程 控制器
 * yan
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class CourseTypeController {

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 获取所有课程
     * @return
     * yan
     */
    @ResponseBody
    @RequestMapping("/getCourseTypes")
    public ResultUtil getCourses(){
        //获取所有课程类别
        List<CourseType> courseTypes = null;
        try {
            courseTypes = courseTypeService.getCourseTypes();
        } catch (Exception e) {
            return new ResultUtil(500,"error",e.getMessage());
        }
        return new ResultUtil(200,"success",courseTypes);
    }

}
