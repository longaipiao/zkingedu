package com.zking.zkingedu.common.service.impl;


import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

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
        return userDao.updateipaddrlastTime(uid,ipaddr,lastTime);
    }

    @Override
    public User getUser(Integer uid) {
        return userDao.getUser(uid);
    }

    /**
     * 查询用户积分的方法
     */
    @Override
    public int findIntegrsl(Integer userID) {
        return userDao.findIntegrsl(userID);
    }


}
