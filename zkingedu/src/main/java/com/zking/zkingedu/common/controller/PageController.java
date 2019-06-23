package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 9:32
 */
@Controller
@RequestMapping(value = "/user")
@Slf4j
public class PageController {

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
        //log.info("请求成功。。。");
        return empDao.getemps();
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    /**
     * 开发者
     * @return
     */
    @RequestMapping(value = "/developer")
    public String developer(){
        return "user/developer/index";
    }


    /**
     * 测试git
     */
    public void test(){
        System.out.println("hello");
    }


}
