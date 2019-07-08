package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 题目接口
 */
public interface TitleService {
    //查询所有的题目
     List<Title> getAll(Title title);
    //增加题目
    Integer addTitle(Title title);
    //修改题目状态
    Integer updatekq(@Param("tid") Integer tid);
    //修改题目状态
    Integer updategb(@Param("tid") Integer tid);
    //根据id删除题目
    Integer deletetim(@Param("tid") Integer tid);
    //查询所有
    List<Title> getAlls(@Param("tid") Integer tid);
    //根据tid修改
    Integer updatetim(Title title);

}
