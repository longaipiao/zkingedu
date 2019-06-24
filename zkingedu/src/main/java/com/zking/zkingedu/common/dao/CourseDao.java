package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Course;

import java.util.List;

/**
 * 课程接口
 */
public interface CourseDao {


    /**
     * yan
     * 根据课程体系id  查询对应的课程
     * @param courseid
     * @return
     */
    List<Course> getCoursesByCourseSid(Integer courseid);


    /**
     * 根据课程id  查询课程信息
     * @param id
     * @return
     * yan
     */
    Course getCourseBYcourseID(Integer id);


    /**
     * 添加查询课程  课程搜索页面
     * @param course
     * @return
     * yan
     */
    List<Course> getCourseSearch(@Param("course") Course course);

    /**
     * 根据课程ID查询单个课程的积分数量在和用户积分对比，判断能不能看此课程视频
     * @param courseid 课程id
     * @return
     */
    public int findCourseIntegrsl(Integer courseid);


}
