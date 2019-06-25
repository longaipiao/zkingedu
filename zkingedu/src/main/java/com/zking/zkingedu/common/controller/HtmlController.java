package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


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
    @RequestMapping(value = "/admin-cate")
    public String les(HttpServletRequest request){
        String roleName = request.getParameter("roleName");
        request.setAttribute("rolename",roleName);
        return "admin/html/admin-cate";
    }
    @RequestMapping(value = "/admin-cate1")
    public String lsds(){
        return "admin/html/admin-cate1";
    }
    @RequestMapping(value = "/member-password")
    public String levs(){
        return "admin/html/member-password";
    }


}
