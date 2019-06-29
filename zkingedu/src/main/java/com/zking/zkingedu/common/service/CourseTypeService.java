package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;

import java.util.List;

/**
 * 课程类型  服务层
 * yan
 */
public interface CourseTypeService {

    /**
     * 获取所有的课程
     * yan
     * @return
     */
    List<CourseType> getCourseTypes();

    /**
     * yan
     * 查询所有课程类别  分页查询
     * @param courseType
     * @return
     */
    List<CourseType> getCourseTypeByPageAndSearch(CourseType courseType);


    /**
     * 查询所有课程类别  分页   搜索
     * yan
     * @param pageBean
     * @return
     */
    ResultUtil getCoursesAndPageSearch(PageBean<CourseType> pageBean);



    /**
     * yan
     * 添加课程类别
     * @param courseType
     * @return
     */
    int addCourseType(CourseType courseType);


    /**
     * yan
     * 修改课程类型
     * @param courseType
     * @return
     */
    int updateCourseType(CourseType courseType);


    /**
     * 删除多个课程类型
     * yan
     * @param ids
     * @return
     */
    int delsCourseType(List<Integer> ids);
}
