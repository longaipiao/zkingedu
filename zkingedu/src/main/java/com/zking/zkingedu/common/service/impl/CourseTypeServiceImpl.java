package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.CourseTypeDao;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {

    @Resource
    private CourseTypeDao courseTypeDao;

    /**
     * //查询所有课程类型
     *
     * @return yan
     */
    @Override
    public List<CourseType> getCourseTypes() {
        return courseTypeDao.getCourseTypes();
    }


    /**
     * yan
     * 查询所有课程类别  分页查询
     *
     * @param courseType
     * @return
     */
    @Override
    public List<CourseType> getCourseTypeByPageAndSearch(CourseType courseType) {
        return courseTypeDao.getCourseTypeByPageAndSearch(courseType);
    }

    /**
     * yan
     * 查询所有课程类别  分页 搜索
     *
     * @param pageBean
     * @return
     */
    @Override
    public ResultUtil getCoursesAndPageSearch(PageBean<CourseType> pageBean) {
        try {
            Page<Object> page = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<CourseType> courseTypes = courseTypeDao.getCourseTypeByPageAndSearch(pageBean.getT());
            ResultUtil result = new ResultUtil();
            result.setData(courseTypes);
            result.setCount(String.valueOf(page.getTotal()));
            result.setMsg("success");
            result.setCode(0);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * yan
     * 添加课程类别
     *
     * @param courseType
     * @return
     */
    @Override
    public int addCourseType(CourseType courseType) {
        return courseTypeDao.addCourseType(courseType);
    }

    /**
     * yan
     * 修改课程类型
     *
     * @param courseType
     * @return
     */
    @Override
    public int updateCourseType(CourseType courseType) {
        return courseTypeDao.updateCourseType(courseType);
    }

    /**
     * 删除多个课程类型
     * yan
     *
     * @param ids
     * @return
     */
    @Override
    public int delsCourseType(List<Integer> ids) {
        return courseTypeDao.delsCourseType(ids);
    }
}
