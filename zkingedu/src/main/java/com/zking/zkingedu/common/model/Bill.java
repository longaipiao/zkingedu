package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 账单表
 * @ClassName Bill
 * @Author likai
 **/
@Data
@Component
public class Bill implements Serializable {
    private static final long serialVersionUID = -9204740485055011707L;
    //账单id
    private Integer billID;
    //用户id
    private Integer billUid;
    //类型0收入1支出
    private Integer billType;
    //积分
    private Integer billIntegral;
    //账单时间
    private String billTime;
    //'账单内容
    private String billContent;
}
