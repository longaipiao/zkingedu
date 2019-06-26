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



}
