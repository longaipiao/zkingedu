package com.zking.zkingedu.common.controller;

import com.google.gson.Gson;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import com.zking.zkingedu.common.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/getMenus")
    @ResponseBody
    public Object getMenus(HttpServletRequest request){
        Emp emp =(Emp) request.getSession().getAttribute("emp");

        return emp;
    }

}
