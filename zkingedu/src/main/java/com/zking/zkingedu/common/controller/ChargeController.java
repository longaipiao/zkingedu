package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.utils.PayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 21:09
 */
@Controller
@Slf4j
@Transactional
public class ChargeController {

    //充值记录表的注入
    @Resource
    private ChargeService chargeService;
    //账单表的记录
    @Resource
    private BillService billService;

    @Autowired
    private Charge charge;
    @Autowired
    private Bill bill;


    /**
     * 充值积分的方法
     * @return charge 充值积分的对象
     */
    /**
     * @param outTradeNo  订单号
     * @param chargeMoney     商品名称
     * @param chargeIntegral 付款金额
     * @param body        商品描述
     * @return String
     */
    @RequestMapping(value = "/pay")
    public String aliPay(String outTradeNo, String chargeIntegral, String chargeMoney, String body, HttpServletRequest request, HttpServletResponse response) {
        // 为防止订单号重否 此处模拟生成唯一订单号
        outTradeNo = PayUtils.createUnilCode();
        log.info("获取金额和积分");
        String Money = request.getParameter("chargeMoney");
        String Integral = request.getParameter("chargeIntegral");
        log.info("开始增加充值记录表的数据");
        charge.setChargeUid(1);//用户id
        charge.setChargeMoney(Double.parseDouble(Money));//收入金额
        charge.setChargeIntegral(Integer.parseInt(Integral));//充值积分
        //充值时间
        charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        chargeService.addCharge(charge);//开始执行充值的方法
        log.info("结束增加充值记录表的数据");

        log.info("开始增加账单表的数据");
        bill.setBillUid(1);//用户id
        bill.setBillType(0);//充值状态
        bill.setBillIntegral(Integer.parseInt(Integral));//充值积分
        //账单时间
        bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //账单内容
        bill.setBillContent("本次在本平台消费金额为"+Double.parseDouble(Money)+"元，"+"充值积分为"+Integer.parseInt(Integral)+",充值完成。");
        billService.addBill(bill);
        log.info("结束增加账单表的数据");
        //支付宝支付
        return chargeService.alipay(outTradeNo, chargeIntegral, chargeMoney.toString(), body, AlipayConfig.NOTIFY_URL, request, response);
    }


    /**
     * 支付宝异步回调
     */
   /* @RequestMapping(value = "/alipay/orderNotify", method = RequestMethod.POST)
    private String getReceiveMap(HttpServletRequest request){


        return "http://localhost:8899/user/userinfo/index";
    }*/


    /*@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletResponse response, HttpServletRequest request) throws Exception {
        return "paySuccess";
    }*/

}
