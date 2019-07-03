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

    /**
     * 首页
     * @return
     */
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
    /**
     *角色管理
     */
    @RequestMapping(value = "/recharge-list")
    public String levis(){
        return "admin/html/recharge-list";
    }

    /**
     *修改角色权限
     */
    @RequestMapping(value = "/member-edit")
    public String leis(HttpServletRequest request){
        String roleID = request.getParameter("roleID");
        String roleName = request.getParameter("roleName");
        request.setAttribute("rolename",roleName);
        request.setAttribute("roleid",roleID);
        return "admin/html/member-edit";
    }
    /**
     *增加角色和权限
     */
    @RequestMapping(value = "/admin-cate")
    public String les(){
        return "admin/html/admin-cate";
    }
    /**
     *增加角色和权限
     */
    @RequestMapping(value = "/emp-cate")
    public String empcate(){
        return "admin/html/emp-cate";
    }
    /**
     *员工管理
     */
    @RequestMapping(value = "/emp-list")
    public String less(){
        return "admin/html/emp-list";
    }
    /**
     *员工修改
     */
    @RequestMapping(value = "/emp-edit")
    public String empedit(HttpServletRequest request){
        String empID = request.getParameter("empID");
        String empPassword = request.getParameter("empPassword");
        String EmpName = request.getParameter("EmpName");
        request.setAttribute("empname",EmpName);
        request.setAttribute("password",empPassword);
        request.setAttribute("empid",empID);
        return "admin/html/emp-edit";
    }
    @RequestMapping(value = "/admin-cate1")
    public String lsds(){
        return "admin/html/admin-cate1";
    }
    @RequestMapping(value = "/member-password")
    public String levs(){
        return "admin/html/member-password";
    }


    /**
     * 后台
     * 跳转至课程体系管理
     * yan
     * @return
     */
    @RequestMapping("/adminCourse")
    public String adminCourseManager(){
        return "admin/course/courseSystem";
    }


    /**
     * admin
     * 跳转至add体系添加
     * @return
     * yan
     */
    @RequestMapping("/pageSystemAdd")
    public String adminPageAddCourse(){return "admin/course/systemAdd";}

    /**
     * 员工个人中心
     */
    @RequestMapping("/mymessage")
    public String mymessage(){return "admin/html/mymessage";}
    /**
     * 员工修改个人密码
     */
    @RequestMapping("/updpwd")
    public String updpwd(){return "admin/html/updpwd";}


}
