package com.zking.springbootelement.service.impl;

import com.zking.springbootelement.dao.UserDao;
import com.zking.springbootelement.model.User;
import com.zking.springbootelement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     *  查询所有的用户
     * @param Uname
     * @return
     */
    @Override
    public List<User> findAll(String Uname) {
        return userDao.findAll(Uname);
    }
}
