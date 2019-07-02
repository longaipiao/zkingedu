package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.ScommentDao;
import com.zking.zkingedu.common.model.Scomment;
import com.zking.zkingedu.common.service.ScommentService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程评论接口   实现
 */
@Service("scommentService")
public class ScommentServiceImpl implements ScommentService {

    @Resource
    private ScommentDao scommentDao;


    @Resource
    private UserService userService;

    /**
     * 根据课程id  查询课程下面所有评论
     * yan
     * @param cid
     * @return
     */
    @Override
    public List<Scomment> getScommentByCid(Integer cid) {
        return scommentDao.getScommentByCid(cid);
    }



    /**
     * 使用流加载  课程评论  类似分页
     * yan
     * @param pageBean
     * @return
     */
    @Override
    public ResultUtil getScommentByPage(PageBean<Integer> pageBean) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Page<Object> page = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
        List<Scomment> scomments = scommentDao.getScommentByCid(pageBean.getT());
        for (Scomment scomment : scomments) {
            Map<String, Object> map = new HashMap<>();
            map.put("scommentID",scomment.getScommentID());
            map.put("scommentCid",scomment.getScommentCid());
            map.put("scommentUid",scomment.getScommentUid());
            map.put("scommentContent",scomment.getScommentContent());
            map.put("scommentTime",scomment.getScommentTime());
            map.put("user",userService.getUserById(scomment.getScommentUid()));
            mapList.add(map);
        }
        ResultUtil result = new ResultUtil();
        result.setCode(0);
        result.setData(mapList);
        result.setCount(String.valueOf(page.getTotal()));
        result.setMsg("success");
        return result;
    }

    /**
     * 前台用户课程评论增加
     * yan
     * @param scomment
     * @return
     */
    @Override
    public int UseraddScomment(Scomment scomment) {
        return scommentDao.UseraddScomment(scomment);
    }



    /**
     * 删除课程评论
     * yan
     * @param id
     * @return
     */
    @Override
    public int delScommentById(Integer id) {
        return scommentDao.delScommentById(id);
    }
}
