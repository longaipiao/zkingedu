package com.zking.zkingedu.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 后台界面url
 */
@Controller
@Slf4j
public class UrlController {

    @RequestMapping(value = "/admin")
    public String alogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/admin/index")
    public String aindex(){
        return "admin/index";
    }


    /**
     * 后台开发者工具跳转路径
     */
    @RequestMapping(value = "/amdin/tool")
    public String toolhref(){
        return "admin/html/integral-list";
    }


    /**
     * 后台增加开发者工具跳转路径
     */
    @RequestMapping(value = "/amdin/addtool")
    public String addtool(){
        return "admin/html/admin-addtool";
    }


    /**
     * 后台的订单列表跳转路径
     */
    @RequestMapping(value = "/amdin/orderList")
    public String orderlist(){
        return "/admin/html/cate";
    }


}
