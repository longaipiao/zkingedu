package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;

import java.util.List;

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


}
