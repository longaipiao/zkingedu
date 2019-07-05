package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.OrderDao;
import com.zking.zkingedu.common.model.Order;
import com.zking.zkingedu.common.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户id查询该用户的订单
     */
    @Override
    public PageInfo<Map<String, Object>> findOrderUid(Integer userID, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> orderUid = orderDao.findOrderUid(userID);
        return new PageInfo<>(orderUid);
    }

    /**
     * 购买单个章节视频的方法
     * @param order
     * @return
     */
    @Override
    public int gmdgsection(Order order) {
        return orderDao.gmdgsection(order);
    }

    /**
     * 查询章节视频id是否存在
     * @param cid 章节id
     * @return
     */
    @Override
    public Integer findorderCid(Integer cid) {
        return orderDao.findorderCid(cid);
    }



    /**
     *  根据用户id查询订单表中是否存在重负的视频
     * @param userId 用户id
     * @param courseId  课程id
     * @param sectionId  章节id
     * @return
     */
    @Override
    public Integer finduidsidcid(Integer userId, Integer courseId, Integer sectionId) {
        return orderDao.finduidsidcid(userId,courseId,sectionId);
    }

    /**
     * 后台的订单查询记录
     * @param userName
     * @param orderID
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> findOrder(String userName, String orderID, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> orders = orderDao.findOrder(userName, orderID);
        return new PageInfo<>(orders);
    }

    /**
     * 根据用户id查询出自己的资源
     * 阿飘
     */
    @Override
    public PageInfo<Map<String, Object>> findziyuanUid(Integer userID,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> ziyuans = orderDao.findziyuanUid(userID);
        return new PageInfo<>(ziyuans);
    }


    /**
     * 查询订单收入的总金额数
     * @return
     */
    @Override
    public Integer orderSum() {
        return orderDao.orderSum();
    }


}
