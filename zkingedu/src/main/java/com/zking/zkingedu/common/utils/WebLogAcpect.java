package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class WebLogAcpect {

    @Resource
    private UserDao userDao;

    @Autowired
    private HttpServletRequest request;

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(* com.zking.zkingedu.common.controller.*.*(..))")
    public void  test(){

    }

    @Before("test()")
    public boolean doBefore(){
        System.out.println("切入成功");
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            System.out.println("空");
            return false;
        }
        User user=(User) session.getAttribute("user");
        System.out.println(user.getUserPassword()+"dasdasdsadasdasd");
        User user1 = userDao.getUser(user.getUserID());
        if(user1!=null){
            System.out.println(user1.getUserIP()+"   SDASD"+user.getUserIP());
            if(user.getUserIP().equals(user1.getUserIP())){
                session.setAttribute("user",null);

            }
        }
        return  true;

    }

}
