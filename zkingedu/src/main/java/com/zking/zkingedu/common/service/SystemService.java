package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.utils.ResultUtil;

import java.util.List;

/**
 * 课程体系接口
 */
public interface SystemService {


    //首页获取课程12个
    /**
     * 首页加载课程体系
     * @return
     * yan
     */
    List<System> getsystemsEight();


    /**
     * yan
     * 返回所有课程体系数据
     * @return
     */
    ResultUtil getSystemsResult();



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

}
