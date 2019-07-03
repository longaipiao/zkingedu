package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Hoarding;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收藏接口
 */
public interface HoardingDao {


    /**
     * 根据前台用户id  查询收藏课程
     * yan  分页
     * @param uid
     * @return
     */
    List<Map<String,Object>> getHoardingsByUid(Integer uid);


    /**
     * 查询课程id  有多少人收藏  统计
     * yan
     * @param courseId
     * @return
     */
    Integer getCourseNumber(Integer courseId);


    /**
     * 根据用户id  和课程id  查询表内是否有记录
     * yan
     * @param uid
     * @param cid
     * @return
     */
    Hoarding getHoardingByUidAndCid(@Param("uid") Integer uid,@Param("cid") Integer cid);


    /**
     * 用户收藏课程  添加
     * yan
     * @param uid  用户id
     * @param cid  课程id
     * @return
     */
    int addHoardingAndCourse(@Param("uid") Integer uid,@Param("cid") Integer cid);

}
