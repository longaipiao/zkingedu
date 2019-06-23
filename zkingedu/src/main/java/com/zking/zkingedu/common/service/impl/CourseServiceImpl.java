package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CourseDao;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程接口
 * 服务层
 * 实现
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {


    @Resource
    private CourseDao courseDao;

    /**
     * yan
     * 根据课程体系id  查询对应的课程
     * @param courseid
     * @return
     */
    @Override
    public List<Course> getCoursesByCourseSid(Integer courseid) {
        return courseDao.getCoursesByCourseSid(courseid);
    }


    /**
     * 根据课程id  查询课程信息
     * @param id
     * @return
     * yan
     */
    @Override
    public Course getCourseBYcourseID(Integer id) {
        return courseDao.getCourseBYcourseID(id);
    }
}
