package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.model.Tcomment;

import java.util.List;
import java.util.Map;

/**
 * 帖子接口
 */
public interface PostDao {

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





}
