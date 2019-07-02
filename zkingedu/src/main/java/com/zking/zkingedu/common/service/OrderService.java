package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Order服务接口
 */
public interface OrderService {

    /**
     * 购买整套视频的方法
     */
    public int gmshiporder(Order order);

    /**
     * 查询课程id是否存在
     */
    public Integer findCourseID(Integer courseID);

    /**
     * 查询用户id是否存在
     */
    public Integer findUserID(Integer userID);


    /**
     * 根据用户id查询该用户的订单
     */
    public PageInfo<Map<String,Object>> findOrderUid(Integer userID, Integer page, Integer limit);


    /**
     * 购买单个章节视频的方法
     * 阿飘
     */
    public int gmdgsection(Order order);


    /**
     * 查询章节视频id是否存在
     * 阿飘
     */
    public Integer findorderCid(Integer cid);

    /**
     * 根据用户id查询订单表中是否存在重负的视频
     */
    public Integer finduidsidcid(Integer userId,Integer courseId,Integer sectionId);

    /**
     * 后台的订单查询记录
     */
    public PageInfo<Map<String,Object>> findOrder(String userName,String orderID, Integer page, Integer limit);
}
