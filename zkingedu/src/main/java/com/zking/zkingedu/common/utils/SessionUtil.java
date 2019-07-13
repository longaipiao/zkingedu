package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session 工具类
 * yan
 */
public class SessionUtil {







    /**
     * 从session中获取员工信息
     *
     * @return yan
     */
    public static Emp getEmp() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession  session = request.getSession();
        /**
         * 模拟登陆员工为
         */
        Emp emp = (Emp) session.getAttribute("emp");
        return emp;
    }


    /**
     * 获取用户  模拟登陆 用户为2的
     *
     * @return
     */
    public static User getUser() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession  session = request.getSession();
//        User user = new User();
//        user.setUserID(2);
        return (User)session.getAttribute("user");
    }


    /**
     * 获取为 的用户  模拟
     * @return
     */
    /**
     * 获取为2 的用户  模拟
     *
     * @return
     */
    public static Integer getUserById() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return user.getUserID();
        }
        return null;
    }
}
