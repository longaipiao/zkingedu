package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    /**
     * 接口
     * 查询所有的课程类别   page分页 查询
     * yan
     * @param courseType
     * @param page  当前页
     * @param limit  每页显示页数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllCourseTypesAndPage")
    public ResultUtil getTypesPageAndSearch(Integer page,Integer limit,CourseType courseType){
        PageBean<CourseType> pageBean = new PageBean<>();
        pageBean.setT(courseType);
        pageBean.setPageIndex(page);
        pageBean.setPageSize(limit);
        return  courseTypeService.getCoursesAndPageSearch(pageBean);
    }


    /**
     * 跳转至课程类型管理页面
     * yan
     * @return
     */
    @RequestMapping("/courseTypePage")
    public String pageCourseType(){
        return "/admin/course/courseType/courseTypeManager";
    }


    /**
     * 添加课程类型
     * yan
     * @param courseType
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/courseType/add")
    public ResultUtil addCourseType(CourseType courseType){
        ResultUtil result;
        try {
            int i = courseTypeService.addCourseType(courseType);
            if(i>0){
                result = ResultUtil.ok("添加成功");
            }
            else{
                result = ResultUtil.error("操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
        return result;
    }


    /**
     * 修改课程类别信息
     * yan
     * @param courseType
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/courseType/edit")
    public ResultUtil updateCourseType(CourseType courseType){
        ResultUtil result;
        try {
            int i = courseTypeService.updateCourseType(courseType);
            if(i>0){
                result = ResultUtil.ok("修改成功");
            }
            else{
                result = ResultUtil.error("操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
        return result;
    }


    /**
     * 删除多个课程类型
     * @param ids
     * @return
     * yan
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/courseType/dels")
    public ResultUtil delsCourserType(@RequestParam(value = "ids[]")List<Integer> ids){
        try {
            int i = courseTypeService.delsCourseType(ids);
            if(i>0){
                return ResultUtil.ok("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.error("您的操作过于频繁");
    }

}
