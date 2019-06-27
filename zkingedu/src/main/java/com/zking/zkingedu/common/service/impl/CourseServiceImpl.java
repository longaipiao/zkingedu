package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.CourseDao;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程接口
 * 服务层
 * 实现
 */
@Service("courseService")
@Slf4j
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


    /**
     * 根据课程ID查询单个课程的积分数量在和用户积分对比，判断能不能看此课程视频
     * @param courseid 课程id
     * @return
     */
    @Override
    public int findCourseIntegrsl(Integer courseid) {
        return courseDao.findCourseIntegrsl(courseid);
    }


    /**
     * 添加查询课程  课程搜索页面
     * @param course
     * @return
     * yan
     */
    @Override
    public List<Course> getCourseSearch(Course course) {
        return courseDao.getCourseSearch(course);
    }


    /**
     * 前台课程搜索多条件搜索
     * yan
     * @param pageBean
     * @return
     */
    @Transactional
    @Override
    public ResultUtil SearchCourse(PageBean<Course> pageBean) {
        if(pageBean.getPageIndex()==null){
            pageBean.setPageIndex(1);
        }
        if(pageBean.getPageSize()==null){
            pageBean.setPageSize(9);
        }
        try {
            //加一个分页
            Page<Object> objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<Course> courseSearch = courseDao.getCourseSearch(pageBean.getT());

            ResultUtil resul = new ResultUtil();
            resul.setCode(200);//正常
            resul.setCount(String.valueOf(objects.getTotal()));
            resul.setData(courseSearch);
            resul.setMsg("success");
            return resul;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500,"数据接口异常",e.getMessage());
        }
    }


    /**
     * 根据课程id查询课程名称
     * @param courseid  课程id
     * @return
     */
    @Override
    public String findCourseName(Integer courseid) {
        return courseDao.findCourseName(courseid);
    }


    /**
     * 获取最热课程 四个播放量排序
     * @return
     * yan
     */
    @Override
    public List<Course> getCoursefour() {
        return courseDao.getCoursefour();
    }


    /**
     * 根据课程id查询出此视频是否免费
     * @param courseID 课程id
     * @return
     */
    @Override
    public Integer findCourseInte(Integer courseID) {
        return courseDao.findCourseInte(courseID);
    }
}
