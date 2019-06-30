package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 服务层
 */
public interface UserService {
    //手机号码重复
    String pdcf(String phone);
    //注册
    Integer add(User user);
    //登入
    User userlogin(@Param("user") User user);
    //修改ip地址和访问时间
    Integer updateipaddrlastTime(@Param("uid") Integer uid,@Param("ipaddr")String ipaddr,@Param("lastTime")String lastTime);
    //强制下线
    User getUser(@Param("uid") Integer uid);
    //根据手机号查询错误次数
    Integer getCwcs(@Param("phone") String phone);
    //根据手机号修改错误次数
    Integer updateCwcs(@Param("phone") String phone);
    //根据手机号修改密码
    Integer updatePhonePassword(@Param("phone") String phone,@Param("password") String password);
    //根据邮箱修改密码
    Integer updateEmmitPassword(@Param("emit") String emit,@Param("password") String password);
    //根据openid查用户信息
    User getopenid(@Param("openid") String openid);
    //根据Openid修改用户的手机号和密码
    Integer updateOpenid(@Param("openid") String openid,@Param("phone") String phone,@Param("passowrd") String password);
    //根据手机号绑定openid
    Integer updateOpenids(@Param("phone") String phone,@Param("openid") String openid,@Param("ip") String ip);
    //根据用户id修改图片
    Integer updateupload(@Param("uid") Integer uid,@Param("upload") String upload);



    /**
     * 查询用户积分的方法
     */
    public int findIntegrsl(Integer user_id);


    /**
     *  根据用户id修改用户积分的方法
     */
    public void updateUserIntegral(Integer CourseIntegral,Integer userID);

}
