package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Order;

/**
 * Order接口
 */
public interface OrderDao {

    /**
     * 购买整套视频的方法
     */
    public int gmshiporder(Order order);

    /**
     * 查询课程id和用户id是否存在
     */
    public Integer findCourseID(Integer courseID);


    /**
     * 查询用户id是否存在
     */
    public Integer findUserID(Integer userID);
}
