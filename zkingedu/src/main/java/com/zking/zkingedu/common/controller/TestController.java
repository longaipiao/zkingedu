package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @ResponseBody
    @RequestMapping("/")
    public String test(HttpServletRequest request){

        return "Holle World!!";
    }


    @RequestMapping("/test")
    public String test() {
        redisUtil.set("username","柠泽");
        Object username = redisUtil.get("username");
        System.err.println(username);
        return "text";
    }

    /**
     * 无需权限请求
     * @return
     */
    @RequestMapping(value = "/login")
    public String testShiro1(){
        System.err.println("来了login");
        return "user/index";
    }

    @RequestMapping(value = "index")
    public String testShiro2(){
        System.err.println("来了index");
        return "user/index";
    }



    @RequestMapping(value = "/admin")
    public String testt(){
        System.err.println("来了index");
        return "admin";
    }



    @Resource
    private EmpDao empDao;

    @ResponseBody
    @RequestMapping("/redis")
    public List<Emp> getEmps(){
        return empDao.getemps();
    }

}
