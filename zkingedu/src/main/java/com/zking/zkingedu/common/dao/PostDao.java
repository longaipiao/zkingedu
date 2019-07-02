package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.*;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
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


    /**
     * 根据评论id
     * @param id
     * @return
     */
    int deletePl(Integer id);

    /**
     * 删除父评论
     * @param id
     * @return
     */
    int deleteFpl(Integer id);


    /**
     * 增加浏览量
     * @param id
     * @return
     */
    int addpostNum(Integer id);

    /**
     * 根据uid查找点赞表
     * @param give
     * @return
     */
    int queryGive(Give give);

    /**
     * 增加点赞表
     * @param give
     * @return
     */
    int addGive(Give give);


    /**
     * 根据uid,和帖子id删除点赞
     * @param give
     * @return
     */
    int delGive(Give give);

    /**
     * 增加收藏
     * @param hoarding
     * @return
     */
    int addCollection(Hoarding hoarding);

    /**
     * 取消收藏
     * @param hoarding
     * @return
     */
    int deleteCollention(Hoarding hoarding);

    /**
     * 查看帖子是否收藏
     * @param hoarding
     * @return
     */
    int queryCollection(Hoarding hoarding);


    /**
     * 根据uid修改展示和隐藏
     * @param uid
     * @param yx
     * @return
     */
    int update(@Param("uid") Integer uid, @Param("yx") Integer yx,@Param("pid") Integer pid);


    /**
     * 根据用户id和uid找到时隐藏还是显示
     * @param uid
     * @param pid
     * @return
     */
    int queryCate(@Param("uid")Integer uid,@Param("pid") Integer pid);

    /**
     * 根据uid修改帖子状态
     * @param uid
     * @param zt
     * @return
     */
    int updatePostzt(@Param("uid") Integer uid,@Param("zt") Integer zt,@Param("id") Integer id);

    /**
     * 根据类别id和标题查找帖子
     * @param cid
     * @param nr
     * @return
     */
    List<Map<String,Object>> getPandU(@Param("cid") Integer cid,@Param("nr") String nr);



}
