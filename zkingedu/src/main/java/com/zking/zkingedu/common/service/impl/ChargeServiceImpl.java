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
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Log4j
public class ChargeServiceImpl implements ChargeService {

    @Resource
    private ChargeDao chargeDao;

    //充值记录表的注入
    @Resource
    private ChargeService chargeService;
    //账单表的记录
    @Resource
    private BillService billService;

    @Resource
    private UserService userService;

    @Autowired
    private Charge charge;
    @Autowired
    private Bill bill;

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


            //System.err.println("进入充值方法");
        //if(request.getParameter("total_amount")!=null){
            //String total_amount = request.getParameter("total_amount");
            //System.err.println("回调之后的金额："+total_amount);
            double Integral = Double.parseDouble(totalAmount) * 10;
            //System.err.println("充值之后的积分："+Integral);

            String integrala = String.valueOf(Integral);
            String integral = integrala.substring(0,integrala.lastIndexOf("."));

            //System.out.println("截取后的积分："+integral);
            //        //log.info("获取金额和积分");
//        //log.info("开始增加充值记录表的数据");
            charge.setChargeUid(SessionUtil.getUserById());//用户id
            charge.setChargeMoney(Double.parseDouble(totalAmount));//收入金额

            charge.setChargeIntegral(Integer.parseInt(integral));//充值积分
            //充值时间
            charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            charge.setChargeState(1);//状态
            int i = chargeService.addCharge(charge);//开始执行充值的方法
//        //log.info("结束增加充值记录表的数据");
            if (i > 0) {
                log.info("****充值记录表成功******");
                //修改用户的积分的方法
                int i1 = userService.updateIntegral(Integer.parseInt(integral), SessionUtil.getUserById());
                if (i1 > 0) {
                    log.info("*****修改用户积分成功******");
                } else {
                    log.info("****充值记录表失败******");
                }
            } else {
                log.info("****充值记录表失败******");
            }

//        //log.info("开始增加账单表的数据");
            bill.setBillUid(SessionUtil.getUserById());//用户id
            bill.setBillType(1);//充值状态
            bill.setBillIntegral(Integer.parseInt(integral));//充值积分
            //账单时间
            bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //账单内容
            bill.setBillContent("本次在本平台消费金额为" + Double.parseDouble(totalAmount) + "元，" + "充值积分为" + Integral + ",充值完成。");
            bill.setBillState(1);//状态
            billService.addBill(bill);
            log.info("结束增加账单表的数据");
        //}

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
