package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帖子接口  实现层
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource
    private PostDao postDao;

    /**
     * 增加贴子
     * @param post
     * @return
     */
    @Override
    public int addPost(Post post) {

        return postDao.addPost(post);
    }

    @Override
    public List<Pcata> getpctae() {
        return postDao.getpctae();
    }
}
