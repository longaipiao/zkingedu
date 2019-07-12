package com.zking.springbootelement.dao;

import com.zking.springbootelement.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有的用户
     * @param Uname
     * @return
     */
    public List<User> findAll(@Param("Uname") String Uname);



}
