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
@Slf4j
public class PageController {

    @RequestMapping(value = "/admin")
    public String alogin(){
        return "admin/login";
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
    @RequestMapping(value = "/user/index")
    public String index(){
        return "user/index";
    }

    /**
     * 后台主页
     * @return
     */
    @RequestMapping(value = "/admin/index")
    public String aindex(){
        return "admin/index";
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


    /**
     * 全部课程的路径
     */
    @RequestMapping(value = "/courses/index")
    public String courses(){
        return "user/courses/index";
    }


    /**
     * 个人中心的路径
     */
    @RequestMapping(value = "/userinfo/index")
    public String userinfo(){
        return "user/userinfo/userinfo";
    }

}
