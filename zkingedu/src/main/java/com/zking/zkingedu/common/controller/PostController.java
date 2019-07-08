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

    //定义全局变量，获取点赞
    Integer dzs=0;

    @Autowired
    private PostService postService;

    /**
     * 增加贴子的方法
     * @return
     */

    @RequestMapping(value = "/addpost")
    @ResponseBody
    public  int addPost(Post pt, String zt,HttpServletRequest request, HttpServletResponse response){
        //假设获取用户id
        Integer uid=8;
        pt.setPostUid(uid);
        pt.setPostState(Integer.parseInt(zt));
        pt.setPostTime(new Date().toLocaleString());//获取当前时间
        pt.setPostNum(0);//设置浏览量
        int i = postService.addPost(pt);//调用增加方法*/
        return i;
    }

    /**
     * 获取帖子下面的所有分类
     * @return
     */
    @RequestMapping(value = "/getpcs")
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
    public Map<String,Object> xxlt(Integer id){
        Integer uid=3;

        //开始调用找到帖子的方法
        Map<String, Object> postandUse = postService.getPostandUse(id);
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


        return tcomments;
    }



   //开始拼接的方法

    /**
     *
     * @param id
     * @param current
     * @return
     */
   /*@RequestMapping(value = "/appendAllTs")
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
   }*/


















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
     * @param uid
     * @param fid
     * @return
     */
    @RequestMapping(value = "/delPl")
    @ResponseBody
    public int delpls(Integer uid,Integer fid){
        //判断是否是父级评论
        if(fid==0){
            //删除父级评论的同时把子类的评论也删除
            int i = postService.deletePl(uid);

            int i1 = postService.deleteFpl(uid);


        }
        //如果是子级评论
        if(fid!=0){
            //删除子类
            int u=postService.deletePl(uid);
            return u;
        }
        return 1;
    }


    /**
     * 增加浏览量
     * @param id
     * @return
     */
    @RequestMapping(value = "/addpstNum")
    @ResponseBody
    public  int addpostNums(Integer id){
        //调用增加浏览量的方法
        int i = postService.addpostNum(id);
     return i;
    }


    /**
     * 根据uid和帖子id查找是否有这个点赞记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/querygive")
    @ResponseBody
    public int queryGive(Integer id){
        //假设用户的id为3
        Integer uid=3;
        Give give=new Give();
        //设置uid
        give.setGiveUid(uid);
        //设置帖子id
        give.setGivePid(id);
        int i = postService.queryGive(give);

        return i;
    }


    /**
     * 点赞和取消点赞
     * @param id
     * @return
     */
    @RequestMapping(value = "/addorDelGive")
    @ResponseBody
    public int addordeleteGive(Integer id){
        //获取用户的id
        Integer uid=3;
        Give give2=new Give();
        give2.setGiveUid(uid);
        give2.setGivePid(id);
        int z = postService.queryGive(give2);

        if(z>0){ //如果查到此贴有点赞的话就是删除
            Give give=new Give();
            give.setGivePid(id);
            give.setGiveUid(uid);
            give.setGiveTime(new Date().toLocaleString());
            int i = postService.delGive(give);
            return 3;
        }
        if(z==0) {//增加点赞
            Give give=new Give();
            give.setGivePid(id);
            give.setGiveUid(uid);
            int i = postService.addGive(give);
            return 4;

        }
        return 1;
    }


    /**
     * 收藏
     * @param id
     * @return
     */
    @RequestMapping(value = "/addordelCollection")
    @ResponseBody
    public int addCollection(Integer id){
        //假设一个用户id
        //查询是否已经收藏了
        Integer uid=3;
        Hoarding hoarding=new Hoarding();
        hoarding.setCollectionUid(uid);
        hoarding.setCollectionState(1);
        hoarding.setCollectionZpid(id);
        int i = postService.queryCollection(hoarding);
        //》0说明已经存在了
        if(i>0){//开始调用取消收藏
            int i1 = postService.deleteCollention(hoarding);
            return 3;//说明取消成功
        }
        if(i==0){//开始调用增加收藏的方法
            Hoarding hoarding1=new Hoarding();
            hoarding1.setCollectionUid(uid);
            hoarding1.setCollectionZpid(id);
            hoarding1.setCollectionState(1);
            hoarding1.setCollectionTime(new Date().toLocaleString());
            int u = postService.addCollection(hoarding);
            return 4;//说明增加收藏成功

        }


        //给收藏表对象赋值

        return 5 ;
    }


    /**
     * 查询是否已经收藏了
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryCollection")
    @ResponseBody
    public int queryColle(Integer id){
        //假设一个用户id
        Integer uid=3;
             Hoarding hoarding=new Hoarding();
             hoarding.setCollectionUid(uid);
             hoarding.setCollectionState(1);
             hoarding.setCollectionTime(new Date().toLocaleString());
             hoarding.setCollectionZpid(id);
        int i = postService.queryCollection(hoarding);
        return i;
    }


    /**
     * 修改文章状态
     * @param pzt
     * @return
     */
    @RequestMapping(value = "/updatePcat")
    @ResponseBody
    public int updatePostCate(Integer pzt,Integer pid){
        //假设一个用户ID
        Integer uid=8;
        if(pzt==0){//如果pzt=0的话，修改成隐藏
            postService.update(uid, 1,pid);
            //调用查询的方法，把状态查询出来
            int i = postService.queryCate(uid, pid);
            return i;
        }
        if(pzt==1){//如果pzt==1的话，修改成显示
            int i=postService.update(uid, 0,pid);
            //调用查询的方法，把状态查询出来
            int k = postService.queryCate(uid, pid);
            return k;
        }
        return 5;
    }


    /**
     * 根据用户id和post_id修改post状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatePState")
    @ResponseBody
    public int updaPoSta(Integer id){
        //假设一个uid=8
        Integer uid=8;
        //修改状态为3
        Integer zt=3;
        int i = postService.updatePostzt(uid, zt, id);
        return i;
    }


    /**
     * 获取所有的讨论
     * @param page
     * @param pageSize
     * @param cid
     * @param nr
     * @return
     */
    @RequestMapping(value = "/getTcomments")
    @ResponseBody
    public Map<String,Object> getTcomments(Integer page, Integer pageSize,Integer cid,String nr){
        System.out.println("输出cid:"+cid);
        //假装有一个用户id
        //获得所有的文章
        PageInfo<Map<String, Object>> allLt = postService.getAllLt(page, pageSize, cid, nr);

        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",allLt.getPages());
        maps.put("ff",allLt.getTotal());

        maps.put("data",allLt.getList());
        return maps;
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
