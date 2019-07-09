package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 服务层
 */
public interface UserService {


     // 查询所有的用户信息
    public PageInfo<User> getAll(User user, Integer page, Integer pageSize);

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
    //根据手机号修改手机号
    Integer updatePhone(@Param("oldphone")String oldphone,@Param("newphone") String newphone);
    //根据邮箱修改邮箱
    Integer updateEamil(@Param("uid")Integer uid,@Param("newEamil") String newEamil);
    //判断邮箱不能重复
    String cfEamil(@Param("Email")String Email);
    //根据用户id封禁
    Integer updateSpase(@Param("uid") Integer uid);
    //根据用户id解封
    Integer updatejf(@Param("uid") Integer uid);



    /**
     * 查询用户积分的方法
     */
    public int findIntegrsl(Integer user_id);



    /**
     * 根据用户id  查询用户信息
     * yan
     * @param id
     * @return
     */
    User getUserById(Integer id);


    /**
     *  根据用户id 消费用户积分的方法
     */
    public void updateUserIntegral(Integer CourseIntegral,Integer userID);


    /**
     *根据用户id 充值积分的方法
     */
    public int updateIntegral(Integer CourseIntegral, Integer userID);


}
