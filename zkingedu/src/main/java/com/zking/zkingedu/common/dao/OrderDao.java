package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    /**
     * 查询章节视频id是否存在
     * 阿飘
     */
    public Integer findorderCid(@Param("ordercid") Integer cid);

    /**
     * 根据用户id查询该用户的订单
     */
    public List<Map<String, Object>> findOrderUid(Integer userID);

    /**
     * 购买单个章节视频的方法
     * 阿飘
     */
    public int gmdgsection(Order order);


    /**
     * 根据用户id查询订单表中是否存在重负的视频
     */
    public Integer finduidsidcid(@Param("userId") Integer userId, @Param("courseId") Integer courseId, @Param("sectionId") Integer sectionId);


    /**
     * 后台的订单查询记录
     */
    public List<Map<String, Object>> findOrder(@Param("userName") String userName, @Param("orderID") String orderID);


    /**
     * 根据用户id查询出自己的资源
     * 阿飘
     */
    public List<Map<String, Object>> findziyuanUid(@Param("userID") Integer userID);


    /**
     * 查询订单收入的总金额数
     */
    public Integer orderSum();


}
