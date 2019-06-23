package com.zking.zkingedu.common.controller;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * 贴子
 */
@Controller
@RequestMapping(value = "/pst")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 增加贴子的方法
     * @return
     */

    @RequestMapping(value = "/addpost")
    @ResponseBody
    public  int addPost(Post pt, String zt,HttpServletRequest request, HttpServletResponse response){
        pt.setPostState(Integer.parseInt(zt));
        System.out.println(pt);
        pt.setPostTime(new Date().toLocaleString());//获取当前时间
        int i = postService.addPost(pt);//调用增加方法*/
        return i;
    }

    @RequestMapping(value = "getpcs")
    @ResponseBody
    public List<Pcata> getpcs(){
        //获取所有的分类
        List<Pcata> getpctae = postService.getpctae();
        return getpctae;
    }


    /**
     * 跳转界面
     *
     */

    /*************************************************************************/


    /**
     * 增加帖子成功跳转的界面
     * @return
     */
    @RequestMapping(value = "/userinfo")
    public String tz(){
        return "user/userinfo/userinfo";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addtz")
    public String t(){
        return "user/questions/post";
    }

    @RequestMapping(value = "/qindex")
    public String tlzym(){
        return "user/questions/index";
    }



















    /*************************************************************************/



}
