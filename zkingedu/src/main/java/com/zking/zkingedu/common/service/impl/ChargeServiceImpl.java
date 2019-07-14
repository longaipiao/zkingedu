package com.zking.zkingedu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.dao.ChargeDao;
import com.zking.zkingedu.common.model.AlipayBean;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.ChargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
     * 支付宝支付方法
     * @param
     * @param request
     * @param response
     * @return
     */
    @Override
    public String alipay(String outTradeNo, String subject, String totalAmount, String body, String notifyUrl, HttpServletRequest request, HttpServletResponse response) {
        String form = "";
        try {
            AlipayBean alipayBean = new AlipayBean();
            alipayBean.setOut_trade_no(outTradeNo);
            alipayBean.setSubject(subject);
            alipayBean.setTotal_amount(totalAmount);
            alipayBean.setBody(body);

            // 1、获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.PRIVARY_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
            // 2、设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            // 页面跳转同步通知页面路径
            alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);
            // 服务器异步通知页面路径
            alipayRequest.setNotifyUrl(notifyUrl);
            // 封装参数
            alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
            // 调用SDK生成表单
            response.reset();
            // 发起支付且成功以后会返回一个支付宝收银台页面的地址,按照官方demo直接到页面即可
            form = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getOutputStream().write(form.getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception e){

        }

        // 返回付款信息
        return form;

    }

    /**
     * 根据用户id查询出所有的充值记录信息
     * @param userId 用户id
     * @return
     */
    @Override
    public PageInfo<Charge> findCharge(Integer userId, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Charge> charges = chargeDao.findCharge(userId);
        return new PageInfo<>(charges);
    }

    /**
     * 修改充值记录表的状态 为2 ，页面上的按钮是删除
     * @return
     */
    @Override
    public int updateState(Integer chargeID) {
        return chargeDao.updateState(chargeID);
    }


}
