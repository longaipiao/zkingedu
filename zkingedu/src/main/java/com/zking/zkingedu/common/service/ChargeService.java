package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Charge;

/**
 * 充值记录接口
 */
public interface ChargeService {

    /**
     * 充值积分的方法
     */
    public int addCharge(Charge charge);

}
