package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Charge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 充值记录接口
 */
public interface ChargeService {

    /**
     * 充值积分的方法
     */
    public int addCharge(Charge charge);

    //支付宝支付方法
    public String alipay(String outTradeNo, String subject, String totalAmount, String body, String notifyUrl, HttpServletRequest request, HttpServletResponse response);


    /**
     * 根据用户id查询出所有的充值记录信息
     */
    public PageInfo<Charge> findCharge(Integer userId, Integer page, Integer limit);

    /**
     * 修改充值记录表的状态 为2 ，页面上的按钮是删除
     */
    public int updateState(Integer chargeID);

}
