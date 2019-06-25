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

}
