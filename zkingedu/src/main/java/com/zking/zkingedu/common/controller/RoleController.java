package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRoles")
    @ResponseBody
    public Object getMenus(int page,int limit){
        Page<Object> objects = PageHelper.startPage(page, limit);
        List<Role> roles = roleService.getRoles();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count",objects.getTotal());
        map.put("data",roles);
        return map;
    }

    @RequestMapping(value = "/role/del")
    @ResponseBody
    public Object delrole(@RequestParam("roleID") String roleID){
        int i = roleService.delRoleByID(Integer.parseInt(roleID));
        roleService.delMenuRoleByID(Integer.parseInt(roleID));
        if(i>0)
            return true;
        else
            return false;
    }


}
