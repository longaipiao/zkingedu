package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * session 工具类
 * yan
 */
public class SessionUtil {


    private RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

    /**
     * 从session中获取员工信息
     * @return
     * yan
     */
    public static Emp getEmp(){
        /**
         * 模拟登陆员工为1
         */
        Emp emp = new Emp();
        emp.setEmpID(1);
        return emp;
    }


    /**
     * 获取用户  模拟登陆 用户为2的
     * @return
     */
    public static User getUser(){
        User user = new User();
        user.setUserID(2);
        return user;
    }


    /**
     * 获取为2 的用户  模拟
     * @return
     */
    public static Integer getUserById(){
        return 2;
    }
}
