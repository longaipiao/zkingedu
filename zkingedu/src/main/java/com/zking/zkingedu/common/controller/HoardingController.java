package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.HoardingDao;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.HoardingService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 收藏
 * yan
 */
@Slf4j
@Controller
@RequestMapping("/hoarding")
public class HoardingController {

    @Autowired
    private HoardingService hoardingService;


    /**
     * 获取登录后的用户 收藏的课程信息  分页
     * yan
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCourseHoarding")
    public ResultUtil getHoardings(Integer page,Integer limit){
        Integer uid = SessionUtil.getUserById();
        PageBean<Integer> pageBean = new PageBean<>();
        pageBean.setT(uid);
        pageBean.setPageIndex(page);
        pageBean.setPageSize(limit);
        return hoardingService.getHoardingsAndPageByUid(pageBean);
    }


    /**
     * 用户收藏课程
     * 颜
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addHoardingAndCourse")
    public ResultUtil userAndHoardingCourse(Integer cid){
        try {
            Integer userById = SessionUtil.getUserById();//用户id
            int i = hoardingService.addHoardingAndCourse(userById,cid);
            if(i>0){
                return ResultUtil.ok("收藏课程成功");
            }
            else{
                return ResultUtil.error("请登录后操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }

    }
}
