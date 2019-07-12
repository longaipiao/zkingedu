package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户表
 *
 * @ClassName User
 * @Author likai
 **/
@Data
@Component
public class User implements Serializable {

    private static final long serialVersionUID = -3685564909593262030L;
    //用户ID
    private Integer userID;
    //用户名称
    private String userName;
    //用户密码
    private String userPassword;
    //用户积分
    private Integer userIntegrsl;
    //状态0正常1禁用
    private Integer userState;
    //用户访问ID地址
    private String userIP;
    //访问时间
    private String userLastTime;
    //注册时间
    private String userRegTime;
    //QQ登录id
    private String userOpenID;
    //用户头像
    private String userImg;
    //用户邮箱
    private String userEmail;
    //用户手机号
    private String userPhone;
    //密码错误次数
    private Integer userCwcs;
}
