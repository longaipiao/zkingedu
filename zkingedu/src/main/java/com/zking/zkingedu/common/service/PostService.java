package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.model.Tcomment;
import com.zking.zkingedu.common.model.Tool;

import java.util.List;
import java.util.Map;

/**
 * 帖子接口
 */

public interface PostService {

    /**
     * 增加贴子
     * @param post
     * @return
     */
    public int addPost(Post post);

    /**
     * 获得所有的分类帖子
     */
    List<Pcata> getpctae();

    /**
     * 根据uid找到所有的帖子
     * @param uid
     * @return
     */
    public List<Post> getAllPosts(Integer uid);


    /**
     * 分页的我的文章
      * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    public PageInfo<Post> getAllposts(Integer uid,Integer page, Integer pageSize);


    /**
     * 根据id获取帖子的具体信息
     * @param id
     * @return
     */
    Map<String,Object> getPostandUse(Integer id);


    /**
     * 根据帖子id找到所有的评论
     * @param id
     * @return
     */
    List<Tcomment> getTcomAll(Integer id);

    /**
     * 找到所有的评论和用户信息根据id
     * @param id
     * @return
     */
    List<Map<String,Object>> getAllTandUser(Integer id);


    /**
     * 获取最大的楼主数
     * @return
     */
    int getMaxlouZnum();


    /**
     * 帖子评论
     */
    int addTcomment(Tcomment tcomment);


}
