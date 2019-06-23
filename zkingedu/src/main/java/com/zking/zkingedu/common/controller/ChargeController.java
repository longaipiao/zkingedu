package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.utils.PayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ChargeController {

    @Resource
    private ChargeService chargeService;


    /**
     * 充值积分的方法
     * @return charge 充值积分的对象
     */
    /**
     * @param outTradeNo  订单号
     * @param chargeMoney     商品名称
     * @param chargeIntegral 付款金额
     * @param body        商品描述
     * @param type        支付方式 1 支付宝支付 2 连连支付
     * @return String
     */
    @RequestMapping(value = "/pay")
    public String aliPay(String outTradeNo, String chargeIntegral, String chargeMoney, String body, String type, HttpServletRequest request, HttpServletResponse response) {
        // 为防止订单号重否 此处模拟生成唯一订单号
        outTradeNo = PayUtils.createUnilCode();
        //支付宝支付
        return chargeService.alipay(outTradeNo, chargeIntegral, chargeMoney.toString(), body, AlipayConfig.NOTIFY_URL, request, response);

    }


    /**
     * 支付宝异步回调
     */
   /* @RequestMapping(value = "/alipay/orderNotify", method = RequestMethod.POST)
    private static Map<String,String> getReceiveMap(HttpServletRequest request){
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        return params;
    }*/


    /*@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletResponse response, HttpServletRequest request) throws Exception {
        return "paySuccess";
    }*/

}
