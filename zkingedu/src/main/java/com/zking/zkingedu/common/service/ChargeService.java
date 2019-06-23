package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Charge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
