package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.ChargeDao;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.ChargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 充值记录接口服务层
 */
@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {

    @Resource
    private ChargeDao chargeDao;

    /**
     * 充值积分的方法
     * @param charge 充值积分的对象
     * @return
     */
    @Override
    public int addCharge(Charge charge) {
        return chargeDao.addCharge(charge);
    }
}
