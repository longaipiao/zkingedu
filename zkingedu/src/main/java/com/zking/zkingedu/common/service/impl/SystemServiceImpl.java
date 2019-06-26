package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.SystemDao;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程体系接口   服务实现层
 *
 */
@Service("systemService")
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemDao systemDao;


    /**
     * yan
     * 返回所有课程体系数据  result
     * @return
     */
    @Override
    public ResultUtil getSystemsResult() {
        //new
        List<System> newsystems = new ArrayList<>();
        try {
            //返回所有体系
            List<System> systems = systemDao.getsystemsEight();
            for (System system : systems) {
                //返回体系对应的 课程数量
                System systemCourseNum = systemDao.getSystemCourseNum(system.getSystemID());
                //修改课程数量至体系中
                system.setSourcesNum(systemCourseNum.getSourcesNum());
                //添加至新的集合中
                newsystems.add(system);
            }
        } catch (Exception e) {
            return new ResultUtil(500,"数据接口异常");
        }
        return new ResultUtil(200,"success",newsystems);
    }

    /**
     * 首页加载课程体系12个
     * yan
     * @return
     */
    @Override
    public List<System> getsystemsEight() {
        return systemDao.getsystemsEight();
    }


    /**
     * 根据fid查询子体系
     * @param fid
     * @return
     * yan
     */
    @Override
    public List<System> getsystemsonByFId(Integer fid) {
        return systemDao.getsystemsonByFId(fid);
    }



    /**
     * 根据体系id查询体系对应信息
     * yan
     * @param id
     * @return
     */
    @Override
    public System getSystemBySid(Integer id) {
        System systemBySid = systemDao.getSystemBySid(id);
            System systemCourseNum = systemDao.getSystemCourseNum(systemBySid.getSystemID());
            systemBySid.setSourcesNum(systemCourseNum.getSourcesNum());
        return systemBySid;
    }


    /**
     * admin
     * 获取所有的体系信息  1级
     * 分页  查询
     * @return
     * yan
     */
    @Override
    public ResultUtil getAllSystems(PageBean<System> pageBean) {

        ArrayList<System> systems = new ArrayList<>();
        Page<Object> objects;
        try {
            //分页
            objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<System> allSystems = systemDao.getAllSystems(pageBean.getT());
            for (System allSystem : allSystems) {
                System systemCourseNum = systemDao.getSystemCourseNum(allSystem.getSystemID());
                allSystem.setSourcesNum(systemCourseNum.getSourcesNum());
                systems.add(allSystem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(500,e.getMessage());
        }
        ResultUtil result = new ResultUtil();
        result.setData(systems);
        result.setCount(String.valueOf(objects.getTotal()));
        result.setCode(0);
        return result;
    }
}
