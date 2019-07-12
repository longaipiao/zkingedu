package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 题目接口
 */
public interface TitleDao {
    //查询所有的题目条件
    List<Title> getAll(Title title);

    //查询所有
    List<Title> getAlls(@Param("tid") Integer tid);

    //增加题目
    Integer addTitle(Title title);

    //修改题目状态
    Integer updatekq(@Param("tid") Integer tid);

    //修改题目状态
    Integer updategb(@Param("tid") Integer tid);

    //根据id删除题目
    Integer deletetim(@Param("tid") Integer tid);

    //根据tid修改
    Integer updatetim(Title title);

    /**
     * 查询所有题库
     *
     * @param title
     * @return
     */
    List<Title> gettitles(Title title);

}
