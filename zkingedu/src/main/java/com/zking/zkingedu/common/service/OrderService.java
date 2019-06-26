package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Order;

/**
 * Order服务接口
 */
public interface OrderService {

    /**
     * 购买整套视频的方法
     */
    public int gmshiporder(Order order);
}
