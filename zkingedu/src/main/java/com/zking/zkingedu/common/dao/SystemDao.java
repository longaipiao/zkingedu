package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.System;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程体系接口
 */
public interface SystemDao {


    /**
     * 首页加载课程体系12个
     * @return
     */
    List<System> getsystemsEight();


    /**
     * 根据fid查询子体系
     * @param fid
     * @return
     * yan
     */
    List<System> getsystemsonByFId(Integer fid);




    /**
     * 根据体系id查询体系对应信息
     * yan
     * @param id
     * @return
     */
    System getSystemBySid(Integer id);


    /**
     * 根据体系fid查询该体系下面有多少课程
     * @param fid
     * @return System
     */
    System getSystemCourseNum(Integer fid);


    /**
     * admin
     * 获取所有的体系信息  1级
     * @return
     * yan
     */
    List<System> getAllSystems(@Param("system") System system);


    /**
     * admin
     * 体系添加
     * yan
     * @param system
     * @return
     */
    int adminAddSystem(@Param("system") System system);


    /**
     * 修改课程状态
     * @param stateId
     * @param systemId
     * @return
     * yan
     */
    int updateSystemState(@Param("stateid") Integer stateId,@Param("systemid") Integer systemId);


    /**
     * admin
     * 体系信息修改
     * @param system
     * @return
     * yan
     */
    int updateSystem(@Param("system") System system);


    /**
     * admin添加体系阶段
     * yan
     * @param fid  体系id
     * @param stageName  阶段名称
     * @return
     */
    int addSystemStage(@Param("sid")Integer sid,@Param("stageName")String stageName);


    /**
     * yan
     * 根据fid  查询对应的体系阶段
     * @param fid
     * @return
     */
    List<System> getSystemsAndStagesByFid(@Param("fid")Integer fid);

    /**
     * admin
     * 修改 阶段名称
     * @param sid
     * @return
     * yan
     */
    int updateSystemAndStageBySid(@Param("sid")Integer sid,@Param("stageName")String stageName);

}
