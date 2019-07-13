package com.zking.zkingedu.common.controller;

import com.alibaba.druid.sql.visitor.functions.Now;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.utils.MessageUtil;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private MessageUtil messageUtil;

    //根据session获取uid
    int getUserid(HttpSession session){
        User user = (User) session.getAttribute("user");
        //假设如果user不为空的话则返回id
        if (user!=null){
            return user.getUserID();
        }
        //如果为空的话，则放回为0
        else {
            return 0;
        }
    }




    /**
     * 增加贴子的方法
     * @return
     */

    @RequestMapping(value = "/addpost")
    @ResponseBody
    public int addPost(Post pt, String zt,HttpServletRequest request, HttpServletResponse response,HttpSession session){

        //获取用户id
        Integer uid=getUserid(session);
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
    public Map<String,Object> findTool(Integer page, Integer pageSize,HttpSession session){
        //假装有一个用户id
        //获得所有的文章
        PageInfo<Post> allposts = postService.getAllposts(getUserid(session), page, pageSize);
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
    public Map<String,Object> xxlt(Integer id,HttpSession session){
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
        maps.put("mg",postandUse.get("user_img"));
        maps.put("l",getUserid(session));//设置用户ID


        return maps;
    }


    /**
     * 根据帖子id找到所有的评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllTs")
    @ResponseBody
    public List<Tcomment> getTs(Integer id,Integer page,Integer pageSize,HttpSession session){
        //根据id获取所欲的评论
        List<Map<String,Object>> allTandUser = postService.getAllTandUser(id);
        //将评论分级，装进集合
        List<Tcomment> tcomments=new ArrayList<>();
        for (Map<String, Object> map : allTandUser) {
            //if((Integer) map.get("tcomment_fid")==0){
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
            user1.setUserLastTime(map.get("sl").toString());
            //回复对应的用户名 。。我回复他（用户名）
            User user3=new User();
            user3.setUserName(map.get("uname2").toString());
            tcomment.setUser(user1);
            tcomment.setUser2(user3);

            tcomments.add(tcomment);
        }
        //}


        return tcomments;
    }


    /**
     * 获取用户id
     * @param session
     * @return
     */
    @RequestMapping(value = "/getuid")
    @ResponseBody
     int getUsersid(HttpSession session){
        int userid = getUserid(session);
        return userid;
     }




    /**
     * 发表评论
     * @param tcomment
     * @return
     */
    @RequestMapping(value = "/addTcomment")
    @ResponseBody
    public int addTct(Tcomment tcomment,HttpSession session){
        //根据id获取用户名
        String s = postService.queryUserByid(tcomment.getTcommentUid2());
        //获取用户ID
        Integer uid=getUserid(session);
        //回复者的id，设置
        tcomment.setTcommentUid(uid);
        //设置评论时间
        tcomment.setTcommentTime(new Date().toLocaleString());
        //如果评论父id为0时说明时对贴主的评论
        if (tcomment.getTcommentFid()==0){
            //设置为0,是第一级评论
            tcomment.setTcommentFid(0);
            //获取最大的楼主id
            int maxlouZnum = postService.getMaxlouZnum(tcomment.getTcommentCid());
            //如果最大楼主为空的话则设置1楼
            if (maxlouZnum==0){
                tcomment.setTcommentLounum(1);
            }
            //开始给楼数赋值
            //如果》最大楼主大于0的话，说明已经有楼主了。则+1
            if (maxlouZnum>0) {
                tcomment.setTcommentLounum(maxlouZnum + 1);
            }
            //获取方法，开始增加
            int i = postService.addTcomment(tcomment);

            //添加日志
            messageUtil.addMessage(SessionUtil.getUserById(),tcomment.getTcommentUid2(),tcomment.getTcommentCid(),s+"评论了你");
            //如果i>0的话，则返回i,否者是0,发表失败
            return i>0?i:0;
        }
        //否者的话则是回复评论
        if(tcomment.getTcommentFid()!=0) {
            //设置楼主为null
            tcomment.setTcommentLounum(null);
            //调用方法
            int i = postService.addTcomment(tcomment);
            //添加日志
            messageUtil.addMessage(SessionUtil.getUserById(),tcomment.getTcommentUid2(),tcomment.getTcommentCid(),s+"回复了你");
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
    public int queryGive(Integer id,HttpSession session){
        //获取uid
        Integer uid=getUserid(session);
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
    public int addordeleteGive(Integer id,HttpSession session){
        //获取用户的id
        Integer uid=getUserid(session);
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
    public int addCollection(Integer id,HttpSession session){
        //假设一个用户id
        //查询是否已经收藏了
        Integer uid=getUserid(session);
        Hoarding hoarding=new Hoarding();
        hoarding.setCollectionUid(uid);
        hoarding.setCollectionState(2);
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
            hoarding1.setCollectionState(2);
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
    public int queryColle(Integer id,HttpSession session){
        //获取用户id
        Integer uid=getUserid(session);
        Hoarding hoarding=new Hoarding();
        hoarding.setCollectionUid(uid);
        hoarding.setCollectionState(2);
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
    public int updatePostCate(Integer pzt,Integer pid,HttpSession session){
        //获取用户ID
        Integer uid=getUserid(session);
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
    public int updaPoSta(Integer id,HttpSession session){
        //获取用户id
        Integer uid=getUserid(session);
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
     * 根据uid找到自己所有的收藏帖子
     * @return
     */
    @RequestMapping(value = "/getAllpbid")
    @ResponseBody
    public Map<String,Object> getAllPostByUID(Integer page,Integer pageSize,HttpSession session){
        //假设uid=3
        Integer uid=getUserid(session);
        PageInfo<Map<String, Object>> allpstByUId = postService.getAllpstByUId(page, pageSize, uid);
        Map<String,Object> m=new HashMap<>();
        m.put("msg","");
        m.put("code",0);
        m.put("count",allpstByUId.getTotal());
        m.put("data",allpstByUId.getList());

        return m;
    }


    /**
     * 根据uid删除收藏的uid
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletecollec")
    @ResponseBody
    public int deleteCollection(Integer id,HttpSession session){
        //获取uid
        Integer uid=getUserid(session);
        Hoarding hoarding=new Hoarding();
        hoarding.setCollectionUid(uid);
        hoarding.setCollectionZpid(id);
        hoarding.setCollectionState(2);
        int i = postService.deleteCollention(hoarding);

        return i;
    }

    /**
     * 获取所有的分类，分页
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getPCTS")
    @ResponseBody
    public Map<String,Object> getallPcts(Integer page,Integer limit){
        PageInfo<Pcata> getpcts = postService.getps(page, limit);

        Map<String,Object> m=new HashMap<>();
        m.put("msg","");
        m.put("code",0);
        m.put("count",getpcts.getTotal());
        m.put("data",getpcts.getList());
        return m;
    }


    /**
     * 批量删除分类
     * @param arr
     * @return
     */
    @RequestMapping(value = "/deltePCSSS")
    @ResponseBody
    public int delpctss(@RequestParam(value = "arr[]") List<Integer> arr){
        int i = postService.deletePcts(arr);
        return i;
    }


    /**
     * 添加分类
     * @param pname
     * @return
     */
    @RequestMapping(value = "/addPca")
    @ResponseBody
    public int addPc(String pname){
        int i = postService.addPcts(pname);
        return i;
    }


    /**
     * 根据id修改分类
     * @param id
     * @param pcname
     * @return
     */
    @RequestMapping(value = "/updatePca")
    @ResponseBody
    public int updatePc(Integer id,String pcname ){
        int i = postService.updatePcts(id, pcname);
        return i;
    }

    /**
     * 根据id删除单个分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delPc")
    @ResponseBody
    public int delPc(Integer id){
        int i = postService.deletePc(id);
        return i;
    }


    /**
     * 获取后台所有的帖子
     * @param page
     * @param limit
     * @param puname
     * @param pname
     * @param pcname
     * @return
     */
    @RequestMapping(value = "/gethtpsot")
    @ResponseBody
    public Map<String,Object> getHtpost(Integer page,Integer limit,String puname,String pname ,String pcname){
        if (puname==""){
            puname=null;
        }
        if (pname==""){
            pname=null;
        }
        if (pcname==""){
            pcname=null;
        }

        PageInfo<Map<String, Object>> hqhtpost = postService.hqhtpost(page, limit, puname, pname, pcname);

        Map<String,Object> m=new HashMap<>();
        m.put("msg","");
        m.put("code",0);
        m.put("count",hqhtpost.getTotal());
        m.put("data",hqhtpost.getList());
        return m;
    }

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping(value = "/updatePstate")
    @ResponseBody
    public int updatePstate(Integer id,boolean state){
        //如果是显示的，则修改为隐藏
        if (state==false){
            int i = postService.updatepSta3(id);
            return i;
        }
        //如果是true则修改为显示
        else {
            int i = postService.updatepSta0(id);
            return i;
        }

    }


    /**
     * 根据id删除帖子表，以及删除对应的评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletePost")
    @ResponseBody
    public int deletePost(Integer id){
        //先删除评论
        int i = postService.deleteTcomments(id);

        //开始删除帖子
        int deletepost = postService.deletepost(id);
        return deletepost;



    }

    /**
     * 批量删除帖子
     * @param arr
     * @return
     */
    @RequestMapping(value = "/deletePandTcoms")
    @ResponseBody
    public  int deletepostandTcoms(@RequestParam(value = "arr[]") List<Integer> arr){
        //首先开始删除帖子下面的所有评论
        int i = postService.deleteTcommetsBypid(arr);
        //开始删除帖子
        int i1 = postService.deletePosts(arr);
        return i1;
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
    @RequestMapping(value = "/userinfo/{id}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ModelAndView tz(@PathVariable("id") Integer id,ModelAndView mv){
        mv.addObject("id",id);
        mv.setViewName("user/questions/show");
        return mv;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/addtz")
    public String t(){
        return "user/questions/post";
    }

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/qindex")
    public String tlzym(Model model){
        model.addAttribute("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        return "user/questions/index";
    }

    @RequestMapping(value = "/aa")
    public String ta(){
        return "user/userinfo/userinfo";
    }


    @RequestMapping(value = "/lbindex")
    public String ioioio(){
        return "admin/post/index";
    }

    @RequestMapping(value = "/pkp")
    public String taat(){
        return "admin/post/postManager";
    }
























    /*************************************************************************/



}
