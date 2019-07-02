package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
     * qq登入成功页面
     * @return
     */
    @RequestMapping(value = "/binding")
    public String binding(){
        return "user/binding";
    }

    /**
     * qq绑定已注册的账号
     * @return
     */
    @RequestMapping(value = "/ybbinding")
    public String bindings(){
        return "user/ybbinding";
    }


    /**
     * 颜
     * @return
     * 视频播放页面跳转
     */
    @RequestMapping("videoshow")
    public String videoShow(){
        log.info("进来了");
        return "user/courses/show";
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


    /**
     * 跳转值学习路径页面
     * yan
     * @return
     */
    @RequestMapping(value = "/paths/index")
    public String pagesIndex(){return "user/paths/index";}




    @RequestMapping(value = "/videos")
    public String pageVideo(){
        return "admin/course/video/videoUpload";
    }


    /**
     * 前台搜索  可搜索课程，论坛帖子
     * @param type  搜索类型  course课程，post论坛
     * @param SearchName  搜索的内容
     * @return
     */
    @RequestMapping("/PageSearch")
    public ModelAndView Search(String type, String SearchName, ModelAndView mv){

        if("course".equals(type)){//搜索课程
            mv.addObject("content",SearchName);
            mv.setViewName("/user/courses/index");
            return mv;//跳转至课程搜索页面
        }
        else{
            return mv;//搜索论坛帖子
        }
    }


}
