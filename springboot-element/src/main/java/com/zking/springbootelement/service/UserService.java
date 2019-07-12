package com.zking.springbootelement.service;

import com.zking.springbootelement.model.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有的用户
     * @param Uname
     * @return
     */
    public List<User> findAll(String Uname);


}
