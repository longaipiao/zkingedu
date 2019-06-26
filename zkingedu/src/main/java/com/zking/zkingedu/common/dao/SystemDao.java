package com.zking.zkingedu.common.dao;

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

}
