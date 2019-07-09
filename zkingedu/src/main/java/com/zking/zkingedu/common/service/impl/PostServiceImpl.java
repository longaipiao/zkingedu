package com.zking.zkingedu.common.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.PipedOutputStream;
import java.lang.System;
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
    public int addpostNum(Integer id) {
        return postDao.addpostNum(id
        );
    }

    @Override
    public int queryGive(Give give) {
        return postDao.queryGive(give
        );
    }

    @Override
    public int addGive(Give give) {
        return postDao.addGive(give);
    }

    @Override
    public int delGive(Give give) {
        return postDao.delGive(give);
    }

    @Override
    public int addCollection(Hoarding hoarding) {
        return postDao.addCollection(hoarding);
    }

    @Override
    public int deleteCollention(Hoarding hoarding) {
        return postDao.deleteCollention(hoarding);
    }

    @Override
    public int queryCollection(Hoarding hoarding) {
        return postDao.queryCollection(hoarding);
    }

    @Override
    public int update(Integer uid, Integer yx,Integer pid) {
        return postDao.update(uid,yx,pid);
    }

    @Override
    public int queryCate(Integer uid, Integer pid) {
        return postDao.queryCate(uid,pid);
    }

    @Override
    public int updatePostzt(Integer uid, Integer zt,Integer id) {
        return postDao.updatePostzt(uid,zt,id);
    }

    @Override
    public List<Map<String, Object>> getPandU(Integer cid, String nr) {
        return postDao.getPandU(cid,nr);
    }

    @Override
    public PageInfo<Map<String, Object>> getAllLt(Integer page, Integer pageSize, Integer cid, String nr) {
        //开始调用方法
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> pandU = postDao.getPandU(cid, nr);
        return new PageInfo<>(pandU);
    }

    @Override
    public List<Map<String, Object>> getApstByUId(Integer uid) {
        return getApstByUId(uid);
    }

    @Override
    public PageInfo<Map<String, Object>> getAllpstByUId(Integer page, Integer pageSize, Integer uid) {
        //开始调用方法
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> apstByUId = postDao.getApstByUId(uid);//获取所有的值
        return new PageInfo<>(apstByUId);
    }

    @Override
    public PageInfo<Pcata> getps(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Pcata> getpctae = postDao.getpctae();
        return new PageInfo<>(getpctae);
    }

    @Override
    public int deletePcts(List<Integer> id) {
        return postDao.deletePcts(id
        );
    }

    @Override
    public int addPcts(String pname) {
        return postDao.addPcts(pname);
    }

    @Override
    public int updatePcts(Integer id, String pname) {
        return postDao.updatePcts(id,pname);
    }

    @Override
    public int deletePc(Integer id) {
        return postDao.deletePc(id);
    }

    @Override
    public List<Map<String, Object>> gethtpost(String puname, String pname, String pcname) {
        return postDao.gethtpost(puname,pname,pcname);
    }

    @Override
    public PageInfo<Map<String, Object>> hqhtpost(Integer page, Integer pageSize, String puname, String pname, String pcname) {
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> gethtpost = postDao.gethtpost(puname, pname, pcname);
        return new PageInfo<>(gethtpost);
    }

    @Override
    public int updatepSta3(Integer id) {
        return postDao.updatepSta3(id);
    }

    @Override
    public int updatepSta0(Integer id) {
        return postDao.updatepSta0(id);
    }

    @Override
    public int deletepost(Integer id) {
        return postDao.deletepost(id);
    }

    @Override
    public int deleteTcomments(Integer id) {
        return postDao.deleteTcomments(id);
    }

    @Override
    public String queryUserByid(Integer id) {
        return postDao.queryUserByid(id);
    }

    @Override
    public int deletePosts(List<Integer> id) {
        return postDao.deletePosts(id);
    }

    @Override
    public int deleteTcommetsBypid(List<Integer> id) {
        return postDao.deleteTcommetsBypid(id);
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
    public int getMaxlouZnum(Integer id) {
        return postDao.getMaxlouZnum(id);
    }

    @Override
    public int addTcomment(Tcomment tcomment) {
        return postDao.addTcomment(tcomment);
    }




}
