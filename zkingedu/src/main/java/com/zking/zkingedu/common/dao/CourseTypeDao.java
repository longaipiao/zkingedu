package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.CourseType;

import java.util.List;

/**
 * 课程类型
 * yan
 */
public interface CourseTypeDao {

    //查询所有课程类型
    List<CourseType> getCourseTypes();

}
