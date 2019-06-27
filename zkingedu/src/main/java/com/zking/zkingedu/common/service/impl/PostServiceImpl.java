package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.Pcata;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.model.Tcomment;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Post> getAllPosts(Integer uid) {
        return postDao.getAllPosts(uid);
    }

    /**
     * 我的文章的所有分页功能
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Post> getAllposts(Integer uid, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Post> allPosts = postDao.getAllPosts(uid);
        return new PageInfo<>(allPosts);
    }

    @Override
    public PageInfo<Tcomment> getAllTcomments(Integer page, Integer pageSize,List<Tcomment> tcomments) {
            PageHelper.startPage(page,pageSize);
        return new PageInfo<>(tcomments);
    }

    @Override
    public int deletePl(Integer id) {
        return postDao.deletePl(id);
    }

    @Override
    public int deleteFpl(Integer id) {
        return postDao.deleteFpl(id);
    }


    @Override
    public Map<String, Object> getPostandUse(Integer id) {
        return postDao.getPostandUse(id);
    }

    @Override
    public List<Tcomment> getTcomAll(Integer id) {
        return postDao.getTcomAll(id);
    }

    @Override
    public List<Map<String, Object>> getAllTandUser(Integer id) {
        return postDao.getAllTandUser(id
        );
    }

    @Override
    public int getMaxlouZnum() {
        return postDao.getMaxlouZnum();
    }

    @Override
    public int addTcomment(Tcomment tcomment) {
        return postDao.addTcomment(tcomment);
    }


}
