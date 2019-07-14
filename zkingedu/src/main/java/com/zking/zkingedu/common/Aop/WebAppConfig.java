package com.zking.zkingedu.common.Aop;


import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Configuration
public class WebAppConfig implements HandlerInterceptor {



    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("哈哈哈");
        HttpSession session = request.getSession();


        if(session.getAttribute("user")==null){//如果为空直接进入首页
            response.sendRedirect("/");
            return false;
        }
       else{
            User user = (User)request.getSession().getAttribute("user");
            User user2 = userService.getUser(user.getUserID());
            if (!IpAddress.getIpAddr(request).equals(user2.getUserIP())){
                //重定向提交
                response.sendRedirect("/");
                return false;
            }
        }




        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
