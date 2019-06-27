package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.OrderDao;
import com.zking.zkingedu.common.model.Order;
import com.zking.zkingedu.common.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Order服务接口  实现层
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {


    @Resource
    private OrderDao orderDao;
    /**
     * 购买整套视频的方法
     * @param
     * @return
     */
    @Override
    public int gmshiporder(Order order) {
        return orderDao.gmshiporder(order);
    }

    /**
     * 查询课程id和用户id是否存在
     */
    @Override
    public Integer findCourseID(Integer courseID) {
        return orderDao.findCourseID(courseID);
    }

    /**
     * 查询用户id是否存在
     */
    @Override
    public Integer findUserID(Integer userID) {
        return orderDao.findUserID(userID);
    }


}
