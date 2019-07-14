package com.zking.zkingedu.common.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> getAll(User user, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> all = userDao.getAll(user);
        return new PageInfo<>(all);
    }

    @Override
    public String pdcf(String phone) {
        return userDao.pdcf(phone);
    }

    @Override
    public Integer add(User user) {
        return userDao.add(user);
    }

    @Override
    public User userlogin(User user) {
        return userDao.userlogin(user);
    }

    @Override
    public Integer updateipaddrlastTime(Integer uid, String ipaddr, String lastTime) {
        return userDao.updateipaddrlastTime(uid, ipaddr, lastTime);
    }

    @Override
    public User getUser(Integer uid) {
        return userDao.getUser(uid);
    }

    @Override
    public Integer getCwcs(String phone) {
        return userDao.getCwcs(phone);
    }

    @Override
    public Integer updateCwcs(String phone) {
        return userDao.updateCwcs(phone);
    }

    @Override
    public Integer updatePhonePassword(String phone, String password) {
        return userDao.updatePhonePassword(phone, password);
    }

    @Override
    public Integer updateEmmitPassword(String emit, String password) {
        return userDao.updateEmmitPassword(emit, password);
    }

    @Override
    public User getopenid(String openid) {
        return userDao.getopenid(openid);
    }

    @Override
    public Integer updateOpenid(String openid, String phone, String password) {
        return userDao.updateOpenid(openid, phone, password);
    }

    @Override
    public Integer updateOpenids(String phone, String openid, String ip) {
        return userDao.updateOpenids(phone, openid, ip);
    }

    @Override
    public Integer updateupload(Integer uid, String upload) {
        return userDao.updateupload(uid, upload);
    }

    @Override
    public Integer updatePhone(String oldphone, String newphone) {
        return userDao.updatePhone(oldphone, newphone);
    }

    @Override
    public Integer updateEamil(Integer uid, String newEamil) {
        return userDao.updateEamil(uid, newEamil);
    }

    @Override
    public String cfEamil(String Email) {
        return userDao.cfEamil(Email);
    }

    @Override
    public Integer updateSpase(Integer uid) {
        return userDao.updateSpase(uid);
    }

    @Override
    public Integer updatejf(Integer uid) {
        return userDao.updatejf(uid);
    }

    /**
     * 查询用户积分的方法
     */
    @Override
    public int findIntegrsl(Integer userID) {
        return userDao.findIntegrsl(userID);
    }

    /**
     * 根据用户id  查询用户信息
     * yan
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    /**
     * 根据用户id修改用户积分的方法
     * 阿飘
     */
    @Override
    public void updateUserIntegral(Integer CourseIntegral, Integer userID) {
        userDao.updateUserIntegral(CourseIntegral, userID);
    }


    /**
     * 根据用户id 充值积分的方法
     *
     * @param CourseIntegral 积分
     * @param userID         用户id
     * @return 阿飘
     */
    @Override
    public int updateIntegral(Integer CourseIntegral, Integer userID) {
        return userDao.updateIntegral(CourseIntegral, userID);
    }

    @Override
    public Integer getUserphone(String phone) {
        return userDao.getUserphone(phone);
    }


}
