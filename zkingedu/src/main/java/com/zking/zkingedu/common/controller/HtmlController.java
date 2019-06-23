package com.zking.zkingedu.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 后台界面url
 */
@Controller
@Slf4j
@RequestMapping("/html")
public class HtmlController {


    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "admin/html/welcome";
    }
    @RequestMapping(value = "/level-list")
    public String levlist(){
        return "admin/html/level-list";
    }
    @RequestMapping(value = "/integral-list")
    public String levlis(){
        return "admin/html/integral-list";
    }
    @RequestMapping(value = "/recharge-list")
    public String levis(){
        return "admin/html/recharge-list";
    }
    @RequestMapping(value = "/member-edit")
    public String leis(){
        return "admin/html/member-edit";
    }
    @RequestMapping(value = "/member-password")
    public String levs(){
        return "admin/html/member-password";
    }


}
