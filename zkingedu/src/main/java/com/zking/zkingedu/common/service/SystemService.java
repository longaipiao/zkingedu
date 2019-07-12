package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程体系接口
 */
public interface SystemService {


    //首页获取课程12个

    /**
     * 首页加载课程体系
     *
     * @return yan
     */
    List<System> getsystemsEight();


    /**
     * yan
     * 返回所有课程体系数据
     *
     * @return
     */
    ResultUtil getSystemsResult();


    /**
     * 根据fid查询子体系
     *
     * @param fid
     * @return yan
     */
    List<System> getsystemsonByFId(Integer fid);


    /**
     * 查询体系阶段数据
     * fid
     */
    List<System> getSystemStages(Integer fid);


    /**
     * 根据体系id查询体系对应信息
     * yan
     *
     * @param id
     * @return
     */
    System getSystemBySid(Integer id);


    /**
     * admin
     * 获取所有的体系信息  1级
     *
     * @return yan
     */
    ResultUtil getAllSystems(PageBean<System> pageBean);


    /**
     * admin
     * 体系添加
     * yan
     *
     * @param system
     * @return
     */
    int adminAddSystem(System system);


    /**
     * 修改课程状态
     *
     * @param stateId
     * @param systemId
     * @return yan
     */
    int updateSystemState(Integer stateId, Integer systemId);


    /**
     * admin
     * 体系信息修改
     *
     * @param system
     * @return yan
     */
    int updateSystem(System system);


    /**
     * 根据fid查询子体系
     *
     * @param pageBean 分页
     * @return yan
     */
    ResultUtil getsystemsonByFId(PageBean<Integer> pageBean);


    /**
     * admin添加体系阶段
     * yan
     *
     * @param fid       体系id
     * @param stageName 阶段名称
     * @return
     */
    int addSystemStage(Integer sid, String stageName);


    /**
     * yan
     * 根据fid  查询对应的体系阶段
     *
     * @param fid
     * @return
     */
    List<System> getSystemsAndStagesByFid(Integer fid);


    /**
     * admin
     * 修改 阶段名称
     *
     * @param sid 体系id
     * @return yan
     */
    int updateSystemAndStageBySid(Integer sid, String stageName);


    /**
     * admin
     * 获取所有的体系
     * yan
     *
     * @return
     */
    List<System> getAll();


    /**
     * yan
     * 获取 所有的体系 以及对应的体系阶段
     *
     * @return
     */
    List<Map<String, Object>> getSystemAndStageMenu();

}
