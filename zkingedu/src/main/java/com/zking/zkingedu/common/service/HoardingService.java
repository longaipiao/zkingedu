package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Hoarding;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * 收藏接口
 */
public interface HoardingService {


    /**
     * 根据前台用户id  查询收藏课程
     * yan  分页
     *
     * @param uid
     * @return
     */
    List<Map<String, Object>> getHoardingsByUid(Integer uid);


    /**
     * 根据 用户id  查询用户收藏的课程  分页
     * yan
     *
     * @param pageBean
     * @return
     */
    ResultUtil getHoardingsAndPageByUid(PageBean<Integer> pageBean);


    /**
     * 查询课程id  有多少人收藏  统计
     * yan
     *
     * @param courseId
     * @return
     */
    Integer getCourseNumber(Integer courseId);


    /**
     * 根据用户id  和课程id  查询表内是否有记录
     * yan
     *
     * @param uid
     * @param cid
     * @return
     */
    Hoarding getHoardingByUidAndCid(Integer uid, Integer cid);


    /**
     * 用户收藏课程  添加
     * yan
     *
     * @param uid 用户id
     * @param cid 课程id
     * @return
     */
    int addHoardingAndCourse(Integer uid, Integer cid);


    /**
     * 用户取消收藏课程
     *
     * @param uid 用户id
     * @param cid 课程id
     * @return yan
     */
    int delHoardingByuidAndCid(Integer uid, Integer cid);


    /**
     * 用户收藏业务
     * 如果用户收藏了课程  则取消收藏
     * 如果用户没收藏课程   则收藏
     * yan
     *
     * @param uid
     * @param cid
     * @return code=1 收藏  code=2取消收藏
     */
    ResultUtil UserAddHoardingAnddelByUidAndCid(Integer uid, Integer cid);


}
