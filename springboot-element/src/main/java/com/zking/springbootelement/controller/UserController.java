package com.zking.springbootelement.controller;


import com.zking.springbootelement.model.User;
import com.zking.springbootelement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Transactional
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录界面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 主界面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        System.err.println("index");
        return "index";
    }

    /**
     * home页面
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    /**
     * 角色中心
     * @return
     */
    @RequestMapping(value = "/role")
    public String role(){
        return "role";
    }

    /**
     * 用户中心
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(){
        return "user";
    }



    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> findall(){
        List<User> users = userService.findAll("");
        System.out.println("所有的用户是："+users);
        return users;
    }


}
