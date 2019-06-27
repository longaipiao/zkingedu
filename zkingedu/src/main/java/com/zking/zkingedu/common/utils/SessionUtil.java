package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.Emp;
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
}
