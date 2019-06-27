package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.CourseType;

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
}
