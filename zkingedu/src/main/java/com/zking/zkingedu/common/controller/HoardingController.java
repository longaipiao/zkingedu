package com.zking.zkingedu.common.controller;

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
     * 用户收藏业务
     * 如果用户收藏了课程  则取消收藏
     * 如果用户没收藏课程   则收藏
     * yan
     * @param  用户id
     * @param cid
     * @return  code=1 收藏  code=2取消收藏  500未登录||异常
     */
    @ResponseBody
    @RequestMapping(value = "/addHoardingAndCourse")
    public ResultUtil userAndHoardingCourse(Integer cid){
        Integer userById = SessionUtil.getUserById();//用户id
        if(userById!=null){
            return hoardingService.UserAddHoardingAnddelByUidAndCid(userById, cid);
        }
        return ResultUtil.error("请登陆后收藏");
    }
}
