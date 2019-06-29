package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * 课程接口
 */
public interface CourseService {


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
     * 根据课程ID查询单个课程的积分数量在和用户积分对比，判断能不能看此课程视频
     * @param courseid 课程id
     * @return
     */
    public int findCourseIntegrsl(Integer courseid);



    /**
     * 添加查询课程  课程搜索页面
     * @param course
     * @return
     * yan
     */
    List<Course> getCourseSearch(Course course);


    /**
     * 前台课程搜索多条件搜索
     * @param pageBean
     * @return
     * yan
     */
    ResultUtil SearchCourse(PageBean<Course> pageBean);


    /**
     * 根据课程id查询课程名称
     * 阿飘
     */
    public String findCourseName(Integer courseid);


    /**
     * 获取最热课程 四个播放量排序
     * @return
     * yan
     */
    List<Course> getCoursefour();


    /**
     * 查询所有的课程信息
     * yan
     * @param pageBean  查询参数
     * @return
     */
    ResultUtil getAllCoursesAndSearchByPage(PageBean<Course> pageBean);


    /**
     * 查询所有的课程信息
     * yan
     * @return  resultUtil
     */
    ResultUtil getAllCourses(PageBean<Course> pageBean);

    /**
     * 课程添加
     * yan
     * @param course
     * @return
     */
    int addCourse(Course course);


    /**
     * yan
     * 根据课程id  查询
     * @param sid
     * @return
     */
    Map<String,Object> getCourseBySid( Integer sid);



    /**
     * 课程信息修改
     * yan
     * @param course
     * @return
     */
    int updateCourse(Course course);


    /**
     * 修改课程状态  0显示  1影藏
     * yan
     * @param id
     * @param stateid
     * @return
     */
    int editCourseState(Integer id,Integer stateid);



    /**
     * yan
     * 修改课程点击量
     * 课程点击量加一
     * @param id
     * @return
     */
    int updatecliNum(Integer id,Integer num);




    /**
     * 更新  课程免费章节数  以及课程购买总积分
     * yan
     * @param id 课程id
     * @return
     */
    int editFreeAndInte(Integer id);

}
