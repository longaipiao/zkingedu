package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Charge;

import java.util.List;

/**
 * 充值记录接口
 */
public interface ChargeDao {

    /**
     * 充值积分的方法
     */
    public int addCharge(Charge charge);

    /**
     * 根据用户id查询出所有的充值记录信息
     */
    public List<Charge> findCharge(Integer userId);

    /**
     * 修改充值记录表的状态 为2 ，页面上的按钮是删除
     */
    public int updateState(Integer chargeID);


}
