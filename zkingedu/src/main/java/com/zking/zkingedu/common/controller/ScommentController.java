package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Scomment;
import com.zking.zkingedu.common.service.ScommentService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程评论
 * 颜
 */
@RequestMapping("/scomment")
@Controller
public class ScommentController {


    @Autowired
    private ScommentService scommentService;


    /**
     * 根据课程id  查询课程下所有评论
     *
     * @param cid 颜
     * @return
     */
    @ResponseBody
    @RequestMapping("/getScomment")
    public ResultUtil getScommentByCid(@RequestParam("cid") Integer cid, Integer page, Integer limit) {
        try {
            PageBean<Integer> pageBean = new PageBean<>();
            pageBean.setPageIndex(page);
            pageBean.setPageSize(limit);
            pageBean.setT(cid);
            return scommentService.getScommentByPage(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("数据加载异常");
        }
    }


    /**
     * 前台用户评论课程
     * yan
     *
     * @param scomment
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/addScomment")
    public ResultUtil UserAddScomment(Scomment scomment) {
        try {
            int i = scommentService.UseraddScomment(scomment);
            if (i > 0) {
                return ResultUtil.ok("评论成功");
            }
            return ResultUtil.error("您的操作过于频繁");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 根据课程评论id删除评论
     * yan
     *
     * @param id
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/delScommentById")
    public ResultUtil UserDelScommentById(@Param("id") Integer id) {
        try {
            int i = scommentService.delScommentById(id);
            if (i > 0) {
                return ResultUtil.ok("删除评论成功");
            }
            return ResultUtil.error("您的操作过于频繁");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }
}
