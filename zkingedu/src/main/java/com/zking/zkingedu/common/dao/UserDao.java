package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户接口
 */
public interface UserDao {
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


    /**
     * 查询用户积分的方法
     */
    public int findIntegrsl(Integer userID);
}
