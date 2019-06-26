package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/admin")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 登录成功把emp放进session
     * @param request
     * @return emp
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object alogin(HttpServletRequest request){
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        System.err.println(name+pass);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, pass);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken); //完成登录
            Emp emp = (Emp) subject.getPrincipal();
            request.getSession().setAttribute("emp",emp);
            return emp;
        } catch (Exception e) {
            return new Emp();
        }
    }

    /**
     *注销
     * @param request
     * @return
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("emp");
        return "admin/login";
    }


}
