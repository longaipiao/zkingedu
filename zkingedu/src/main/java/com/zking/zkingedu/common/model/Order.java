package com.zking.zkingedu.common.model;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 订单表
 * @ClassName Order
 * @Author likai
 **/
@Data
@Component
public class Order implements Serializable {
    private static final long serialVersionUID = 3373981139157922935L;
    //订单id
    private String orderID;
    //用户id
    private Integer orderUid;
    //章节id
    private Integer orderSid;
    //课程id
    private Integer orderCid;
    //消费积分
    private Integer orderIntegral;
    //'账单时间
    private String chargeTime;
    //状态
    private Integer orderState;
}
