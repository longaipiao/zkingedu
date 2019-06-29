package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程类型
 * yan
 */
public interface CourseTypeDao {

    //查询所有课程类型
    List<CourseType> getCourseTypes();


    /**
     * yan
     * 查询所有课程类别  分页查询
     * @param courseType
     * @return
     */
    List<CourseType> getCourseTypeByPageAndSearch(CourseType courseType);


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
