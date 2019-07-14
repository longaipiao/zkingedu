package com.zking.zkingedu.common.Aop;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component
public class WebLogAcpect {

    @Resource
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private  HttpServletResponse response;



    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(* com.zking.zkingedu.common.controller.*.*(..))")
    public void  test(){

    }


    @Before("test()")
    public boolean doBefore(){
        request.getSession().setAttribute("s2","a");
        HttpSession session = request.getSession();

        if(session.getAttribute("user")==null){//为空不执行下面的操作
            return false;
        }
        User user=(User)session.getAttribute("user");
        User user1 = userService.getUser(user.getUserID());
        if(user1!=null){
            if(!IpAddress.getIpAddr(request).equals(user1.getUserIP())){
                try {
                    //重定向提交
                    request.getSession().setAttribute("s2","b");
                    response.sendRedirect("/");
                    return false;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return  true;

    }

}
