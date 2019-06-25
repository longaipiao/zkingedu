package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CourseTypeDao;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {

    @Resource
    private CourseTypeDao courseTypeDao;

    /**
     * //查询所有课程类型
     * @return
     * yan
     */
    @Override
    public List<CourseType> getCourseTypes() {
        return courseTypeDao.getCourseTypes();
    }
}
