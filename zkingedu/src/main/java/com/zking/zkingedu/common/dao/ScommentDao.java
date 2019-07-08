package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Scomment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程评论接口
 */
public interface ScommentDao {

    /**
     * 根据课程id  查询课程下面所有评论
     * yan
     * @param cid
     * @return
     */
    List<Scomment> getScommentByCid(@Param("cid")Integer cid);


    /**
     * 前台用户课程评论增加
     * yan
     * @param scomment
     * @return
     */
    int UseraddScomment(Scomment scomment);


    /**
     * 删除课程评论
     * yan
     * @param id
     * @return
     */
    int delScommentById(@Param("id")Integer id);


    /**
     * 根据课程id  查询有多少课程评论
     * yan
     * @param cid
     * @return
     */
    Integer getScommentAndCousecNumber(@Param("cid") Integer cid);

}
