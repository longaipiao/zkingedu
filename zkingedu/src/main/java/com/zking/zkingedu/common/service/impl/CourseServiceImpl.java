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
import java.util.Map;

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
            Course course=pageBean.getT();
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

    /**
     * 查询所有的课程信息
     * yan
     * @param pageBean  查询参数
     * @return
     */
    @Override
    public ResultUtil getAllCoursesAndSearchByPage(PageBean<Course> pageBean) {
        if(pageBean.getPageIndex()==null){
            pageBean.setPageIndex(1);
        }
        if(pageBean.getPageSize()==null){
            pageBean.setPageSize(10);
        }
        try {
            Page<Object> objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<Course> courses = courseDao.getAllCoursesAndSearchByPage(pageBean.getT());
            ResultUtil result = new ResultUtil();
            result.setData(courses);
            result.setCount(String.valueOf(objects.getTotal()));
            result.setMsg("success");
            result.setCode(0);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 查询所有的课程信息
     * yan
     * @return  map
     */
    @Override
    public ResultUtil getAllCourses(PageBean<Course> pageBean) {
        if(pageBean.getPageIndex()==null){
            pageBean.setPageIndex(1);
        }
        if(pageBean.getPageSize()==null){
            pageBean.setPageSize(10);
        }
        try {
            Page<Object> objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            //查询数据
            List<Map<String, Object>> allCourses = courseDao.getAllCourses(pageBean.getT());
            ResultUtil result = new ResultUtil();
            result.setData(allCourses);
            result.setCount(String.valueOf(objects.getTotal()));
            result.setMsg("success");
            result.setCode(0);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 课程添加
     * yan
     * @param course
     * @return
     */
    @Transactional
    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    /**
     * yan
     * 根据课程id  查询
     * @param sid
     * @return
     */
    @Override
    public Map<String, Object> getCourseBySid(Integer sid) {
        return courseDao.getCourseBySid(sid);
    }

    /**
     * 课程信息修改
     * yan
     * @param course
     * @return
     */
    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    /**
     * 修改课程状态  0显示  1影藏
     * yan
     * @param id
     * @param stateid
     * @return
     */
    @Override
    public int editCourseState(Integer id, Integer stateid) {
        return courseDao.editCourseState(id,stateid);
    }
}
