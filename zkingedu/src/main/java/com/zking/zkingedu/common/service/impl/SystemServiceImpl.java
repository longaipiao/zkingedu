package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.SystemDao;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Maps;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程体系接口   服务实现层
 */
@Service("systemService")
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemDao systemDao;


    /**
     * yan
     * 返回所有课程体系数据  result
     *
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
            return new ResultUtil(500, "数据接口异常");
        }
        return new ResultUtil(200, "success", newsystems);
    }

    /**
     * 首页加载课程体系12个
     * yan
     *
     * @return
     */
    @Override
    public List<System> getsystemsEight() {
        return systemDao.getsystemsEight();
    }

    /**
     * 获取课程体系5个热门
     * yan
     * @return
     */
    @Override
    public List<System> getsystemsFive() {
        List<System> sys = new ArrayList<>();
        List<System> systems = systemDao.getsystemsEight();
        if (systems.size()>5){
            int i=0;
            for (System system : systems) {
                i++;
                if(i>5){
                    break;
                }
                sys.add(system);
            }
        }
        return sys;
    }

    /**
     * 根据fid查询子体系
     *
     * @param fid
     * @return yan
     */
    @Override
    public List<System> getsystemsonByFId(Integer fid) {
        return systemDao.getsystemsonByFId(fid);
    }


    @Override
    public List<System> getSystemStages(Integer fid) {
        return systemDao.getSystemStages(fid);
    }

    /**
     * 根据体系id查询体系对应信息
     * yan
     *
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
     *
     * @return yan
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
            return new ResultUtil(500, e.getMessage());
        }
        ResultUtil result = new ResultUtil();
        result.setData(systems);
        result.setCount(String.valueOf(objects.getTotal()));
        result.setCode(0);
        return result;
    }


    /**
     * admin
     * 体系添加
     * yan
     *
     * @param system
     * @return
     */
    @Override
    public int adminAddSystem(System system) {
        return systemDao.adminAddSystem(system);
    }


    /**
     * 修改课程状态
     *
     * @param stateId
     * @param systemId
     * @return yan
     */
    @Transactional
    @Override
    public int updateSystemState(Integer stateId, Integer systemId) {
        return systemDao.updateSystemState(stateId, systemId);
    }


    /**
     * admin
     * 体系信息修改
     *
     * @param system
     * @return yan
     */
    public int updateSystem(System system) {
        try {
            return systemDao.updateSystem(system);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 根据fid查询子体系
     *
     * @param pageBean 分页
     * @return yan
     */
    @Override
    public ResultUtil getsystemsonByFId(PageBean<Integer> pageBean) {
        ResultUtil result;
        try {
            Page<Object> objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<System> systems = systemDao.getSystemsAndStagesByFid(pageBean.getT());
            result = new ResultUtil();
            result.setCode(0);
            result.setCount(String.valueOf(objects.getTotal()));
            result.setData(systems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("系统繁忙");
        }
        return result;
    }


    /**
     * admin添加体系阶段
     * yan
     *
     * @param sid       体系id
     * @param stageName 阶段名称
     * @return
     */
    @Override
    public int addSystemStage(Integer sid, String stageName) {
        return systemDao.addSystemStage(sid, stageName);
    }


    /**
     * yan
     * 根据fid  查询对应的体系阶段
     *
     * @param fid
     * @return
     */
    @Override
    public List<System> getSystemsAndStagesByFid(Integer fid) {
        return systemDao.getSystemsAndStagesByFid(fid);
    }


    /**
     * admin
     * 修改 阶段名称
     *
     * @param sid 体系id
     * @return yan
     */
    @Override
    public int updateSystemAndStageBySid(Integer sid, String stageName) {
        return systemDao.updateSystemAndStageBySid(sid, stageName);
    }


    /**
     * admin
     * 获取所有的体系
     * yan
     *
     * @return
     */
    @Override
    public List<System> getAll() {
        return systemDao.getAll();
    }


    /**
     * yan
     * 获取 所有的体系 以及对应的体系阶段
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getSystemAndStageMenu() {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<System> all = systemDao.getAll();
        for (System system : all) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", system.getSystemID());
            map.put("sysName", system.getSystemName());
            map.put("stages", systemDao.getSystemStages(system.getSystemID()));
            maps.add(map);
        }
        return maps;
    }


    /**
     * 删除体系
     * yan
     * @param sid  体系id
     * @return
     */
    @Override
    public int delSystemById(Integer sid) {
        return systemDao.delSystemById(sid);
    }


    /**
     * 查询父体系下面阶段数量
     * @param sid
     * yan
     * @return
     */
    @Override
    public Integer selSysByFidNum(Integer sid) {
        return systemDao.selSysByFidNum(sid);
    }



    /**
     * 查询体系阶段下面的阶段课程数量
     * yan
     * @param sid
     * @return
     */
    @Override
    public Integer selStageAndCourseNum(Integer sid) {
        return systemDao.selStageAndCourseNum(sid);
    }
}
