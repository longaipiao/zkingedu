package com.zking.zkingedu.common.controller;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.System;
import java.net.SocketTimeoutException;
import java.util.*;

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

    /**
     * 获取帖子下面的所有分类
     * @return
     */
    @RequestMapping(value = "getpcs")
    @ResponseBody
    public List<Pcata> getpcs(){
        //获取所有的分类
        List<Pcata> getpctae = postService.getpctae();
        return getpctae;
    }


    /**
     * 获得所有的文章 根据uid
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAllposts")
    @ResponseBody
    public Map<String,Object> findTool(Integer page, Integer pageSize){
        //假装有一个用户id
        //获得所有的文章
        PageInfo<Post> allposts = postService.getAllposts(8, page, pageSize);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",allposts.getTotal());
        maps.put("data",allposts.getList());
        return maps;
    }


    //跳转到论坛详细界面
    @RequestMapping(value = "/tzxx")
    @ResponseBody
    public Map<String,Object> xxlt(String id){
        //开始调用找到帖子的方法
        Map<String, Object> postandUse = postService.getPostandUse(Integer.parseInt(id));

        Map<String,Object> maps = new HashMap<>();


        maps.put("a",postandUse.get("post_content"));
        maps.put("b",postandUse.get("post_id"));
        maps.put("c",postandUse.get("post_uid"));
        maps.put("d",postandUse.get("post_name"));
        maps.put("e",postandUse.get("user_id"));
        maps.put("f",postandUse.get("user_name"));
        maps.put("g",postandUse.get("post_category"));
        maps.put("h",postandUse.get("post_state"));
        maps.put("i",postandUse.get("post_time"));
        maps.put("j",postandUse.get("post_num"));
        maps.put("k",postandUse.get("jishu"));


        return maps;
    }


    /**
     * 根据帖子id找到所有的评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllTs")
    @ResponseBody
    public List<Tcomment> getTs(Integer id){
        List<Tcomment> tcomAll = postService.getTcomAll(id);
        return tcomAll;
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
        return "user/questions/show";
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

    @RequestMapping(value = "/aa")
    public String ta(){
        return "user/userinfo/userinfo";
    }


















    /*************************************************************************/



}
