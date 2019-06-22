package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 充值记录表
 * @ClassName Charge
 * @Author likai
 **/
@Data
@Component
public class Charge implements Serializable {
    private static final long serialVersionUID = 8001550989483865390L;
    //'id
    private Integer chargeID;
    //用户id
    private Integer chargeUid;
    //金额
    private Double chargeMoney;
    //积分
    private Integer chargeIntegral;
    //'账单时间
    private String chargeTime;
}
