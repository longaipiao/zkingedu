package com.zking.zkingedu.common.controller;

import com.alibaba.druid.sql.visitor.functions.Now;

import com.github.pagehelper.PageHelper;
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
import javax.persistence.criteria.CriteriaBuilder;
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


    public final static int DEFAULT_FIRST_COUNT = 8;//第一次列表显示的个数

    public final static int PER_PAGE_COUNT = 4;//没触底一次，追加4个信息对象

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
        Integer uid=3;

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
        maps.put("l",uid);//设置用户ID

        return maps;
    }


    /**
     * 根据帖子id找到所有的评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllTs")
    @ResponseBody
    public List<Tcomment> getTs(Integer id,Integer page,Integer pageSize){
        //根据id获取所欲的评论
        List<Map<String,Object>> allTandUser = postService.getAllTandUser(id);
       //将评论分级，装进集合
        List<Tcomment> tcomments=new ArrayList<>();

        for (Map<String, Object> map : allTandUser) {
            if((Integer) map.get("tcomment_fid")==0){
                Tcomment tcomment=new Tcomment();
                tcomment.setTcommentID((Integer) map.get("tcomment_id"));
                tcomment.setTcommentCid((Integer) map.get("tcomment_cid"));
                tcomment.setTcommentContent(map.get("tcomment_content").toString());
                tcomment.setTcommentTime(map.get("tcomment_time").toString());
                tcomment.setTcommentFid((Integer)map.get("tcomment_fid"));
                tcomment.setTcommentLounum((Integer)map.get("tcomment_lounum"));
                tcomment.setTcommentUid2((Integer)map.get("uid2"));

                User user1=new User();
                user1.setUserID((Integer) map.get("user_id"));
                user1.setUserImg(map.get("user_img").toString());
                user1.setUserName(map.get("user_name").toString());
                //回复对应的用户名 。。我回复他（用户名）
                User user3=new User();
                user3.setUserName(map.get("uname2").toString());
                tcomment.setUser(user1);
                tcomment.setUser2(user3);
                List<Tcomment> tcomments2=new ArrayList<>();

                for (Map<String, Object> map2 : allTandUser) {
                    if((Integer) map.get("tcomment_id")==(Integer) map2.get("tcomment_fid")){
                        Tcomment tcomment2=new Tcomment();
                        tcomment2.setTcommentID((Integer) map2.get("tcomment_id"));
                        tcomment2.setTcommentCid((Integer) map2.get("tcomment_cid"));
                        tcomment2.setTcommentContent(map2.get("tcomment_content").toString());
                        tcomment2.setTcommentTime(map2.get("tcomment_time").toString());
                        tcomment2.setTcommentFid((Integer)map2.get("tcomment_fid"));
                        tcomment2.setTcommentLounum((Integer)map2.get("tcomment_lounum"));
                        tcomment2.setTcommentUid2((Integer)map2.get("uid2"));

                        User user2=new User();
                        user2.setUserID((Integer) map2.get("user_id"));
                        user2.setUserImg(map2.get("user_img").toString());
                        user2.setUserName(map2.get("user_name").toString());
                        User user4=new User();
                        user4.setUserName(map2.get("uname2").toString());
                        tcomment2.setUser(user2);
                        tcomment2.setUser2(user4);
                        tcomments2.add(tcomment2);
                    }
                }
                tcomment.setChirden(tcomments2);
                tcomments.add(tcomment);
            }
        }


        //开始
        int toIndex=Constant.DEFAULT_FIRST_COUNT;
       if(tcomments.size()<Constant.DEFAULT_FIRST_COUNT){
           //如果集合的大小小于自定义默认的8个的话，则使用集合的大小
                 toIndex=tcomments.size();
           List<Tcomment> tcomments1 = tcomments.subList(0, toIndex);//推送出去的对象
           return  tcomments1;
       }
        List<Tcomment> tcomments2=tcomments.subList(0,toIndex);//默认推送出去的个数


        return tcomments;
    }



   //开始拼接的方法

    /**
     *
     * @param id
     * @param current
     * @return
     */
   @RequestMapping(value = "/appendAllTs")
   @ResponseBody
   public List<Tcomment>  apendTcomment(Integer id,Integer current){
       System.out.println("进来了");
       //根据id获取所欲的评论
       List<Map<String,Object>> allTandUser = postService.getAllTandUser(id);
       //将评论分级，装进集合
       List<Tcomment> tcomments=new ArrayList<>();
       for (Map<String, Object> map : allTandUser) {
           if((Integer) map.get("tcomment_fid")==0){
               Tcomment tcomment=new Tcomment();
               tcomment.setTcommentID((Integer) map.get("tcomment_id"));
               tcomment.setTcommentCid((Integer) map.get("tcomment_cid"));
               tcomment.setTcommentContent(map.get("tcomment_content").toString());
               tcomment.setTcommentTime(map.get("tcomment_time").toString());
               tcomment.setTcommentFid((Integer)map.get("tcomment_fid"));
               tcomment.setTcommentLounum((Integer)map.get("tcomment_lounum"));
               tcomment.setTcommentUid2((Integer)map.get("uid2"));
               User user1=new User();
               user1.setUserID((Integer) map.get("user_id"));
               user1.setUserImg(map.get("user_img").toString());
               user1.setUserName(map.get("user_name").toString());
               //回复对应的用户名 。。我回复他（用户名）
               User user3=new User();
               user3.setUserName(map.get("uname2").toString());
               tcomment.setUser(user1);
               tcomment.setUser2(user3);
               List<Tcomment> tcomments2=new ArrayList<>();

               for (Map<String, Object> map2 : allTandUser) {
                   if((Integer) map.get("tcomment_id")==(Integer) map2.get("tcomment_fid")){
                       Tcomment tcomment2=new Tcomment();
                       tcomment2.setTcommentID((Integer) map2.get("tcomment_id"));
                       tcomment2.setTcommentCid((Integer) map2.get("tcomment_cid"));
                       tcomment2.setTcommentContent(map2.get("tcomment_content").toString());
                       tcomment2.setTcommentTime(map2.get("tcomment_time").toString());
                       tcomment2.setTcommentFid((Integer)map2.get("tcomment_fid"));
                       tcomment2.setTcommentLounum((Integer)map2.get("tcomment_lounum"));
                       tcomment2.setTcommentUid2((Integer)map2.get("uid2"));

                       User user2=new User();
                       user2.setUserID((Integer) map2.get("user_id"));
                       user2.setUserImg(map2.get("user_img").toString());
                       user2.setUserName(map2.get("user_name").toString());
                       User user4=new User();
                       user4.setUserName(map2.get("uname2").toString());
                       tcomment2.setUser(user2);
                       tcomment2.setUser2(user4);
                       tcomments2.add(tcomment2);
                   }
               }
               tcomment.setChirden(tcomments2);
               tcomments.add(tcomment);
           }
       }

       //开始拼接的方法
       int len=tcomments.size();//获取整个集合的大小19-8
       //再根据传过来的对象数去减
       int last=len-current;
       if(last<=0){//如果相减<=0的话
             last=0;
       }
       int toIndex=current+Constant.PER_PAGE_COUNT;
       //如果相减小于追加的数的话
       if (last<Constant.PER_PAGE_COUNT){
               toIndex=last+current;
       }
       List<Tcomment> tcomments1 = tcomments.subList(current, toIndex);
       System.out.println("输出tcomments1的大小:"+tcomments1.size());

       return tcomments1;
   }


















    /**
     * 发表评论
     * @param tcomment
     * @return
     */
    @RequestMapping(value = "/addTcomment")
    @ResponseBody
    public int addTct(Tcomment tcomment){

        //获取用户ID
        Integer uid=9;
        //回复者的id，设置
        tcomment.setTcommentUid(uid);
        //设置评论时间
        tcomment.setTcommentTime(new Date().toLocaleString());
        //如果评论父id为0时说明时对贴主的评论
       if (tcomment.getTcommentFid()==0){
                 //设置为0,是第一级评论
                  tcomment.setTcommentFid(0);
            //获取最大的楼主id
            int maxlouZnum = postService.getMaxlouZnum();
            //开始给楼数赋值
            tcomment.setTcommentLounum(maxlouZnum+1);
            //获取方法，开始增加
            int i = postService.addTcomment(tcomment);
            //如果i>0的话，则返回i,否者是0,发表失败
            return i>0?i:0;
        }
        //否者的话则是回复评论
        if(tcomment.getTcommentFid()!=0) {
            //设置楼主为null
            tcomment.setTcommentLounum(null);
            //调用方法
            int i = postService.addTcomment(tcomment);
            return i > 0 ? i : 0;
        }

       return 0;
    }


    /**
     * 删除评论
     * @param id
     * @param fid
     * @return
     */
    public int delpls(Integer id,Integer fid){
        return 0;
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
