package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.utils.PageBean;
import org.apache.ibatis.annotations.Param;

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
     * 获取最热课程 四个播放量排序
     * @return
     * yan
     */
    List<Course> getCoursefour();

}
