package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.model.MenuRole;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Slf4j
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time=dateFormat.format(new Date());

    /**
     * 获取所有角色，并赋予layui-table格式
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getRoles")
    @ResponseBody
    public Object getMenus(int page, int limit, HttpServletRequest request) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        String roleName = request.getParameter("roleName");
        if(roleName==null)
            roleName="";
        List<Role> roles = roleService.getRoles("%"+roleName+"%");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", objects.getTotal());
        map.put("data", roles);
        return map;
    }

    /**
     * 获取所有角色（增加/修改 用户需要）
     * @return
     */
    @RequestMapping(value = "/getRolesByEmp")
    @ResponseBody
    public Object addEmpgetRoles() {
        List<Role> roles = roleService.getRoles("%%");
        return roles;
    }

    /**
     * 删除单个角色
     * @param roleID
     * @return
     */
    @Transactional
    @RequestMapping(value = "/role/del")
    @ResponseBody
    public Object delrole(@RequestParam("roleID")Integer roleID) {
        if(roleID==1)
            return false;
        int i = roleService.delRoleByID(roleID);
        roleService.delMenuRoleByID(roleID);
        if (i > 0)
            return true;
        else
            return false;
    }

    /**
     * 更新角色和重新授权权限
     * @param roleID
     * @param roleName
     * @param menus
     * @param menuJson
     * @return
     */
    @Transactional
    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public Object updateRole(@RequestParam("roleID") String roleID, @RequestParam("roleName") String roleName, @RequestParam("menus") String menus, @RequestParam("menuJson") String menuJson) {
        int n = roleService.updateRoleByID(Integer.parseInt(roleID), roleName);//修改用户名
        ArrayList<Map> list = JSON.parseObject(menus, new TypeReference<ArrayList<Map>>() {
        });//把json转为list
        List<String> list2 = new ArrayList<>();
        for (Map map : list) {
            list2.add(map.get("id").toString());//只要ID
            String children = map.get("children").toString();//获取所有子选项
            ArrayList<Map> list1 = JSON.parseObject(children, new TypeReference<ArrayList<Map>>() {
            });//子选项的值
            for (Map map1 : list1) {
                list2.add(map1.get("id").toString());//只要ID
            }
        }
        String[] split = menuJson.split(",");//获取原始的所有id（修改前的数据）
        String[] strArray = list2.toArray(new String[list2.size()]);//修改后的数据

        if (!Arrays.toString(strArray).equals(Arrays.toString(split))){//判断是否修改过
            roleService.delMenuRoleByID(Integer.parseInt(roleID));//删除现有的所有权限
            List<MenuRole> list3 =  new ArrayList<>();
            for (String s : list2) {
                list3.add(new MenuRole(Integer.parseInt(roleID),Integer.parseInt(s)));
            }
            roleService.addMenuRoleByID(list3);//重新附权限
        }
            if (n > 0)
                return true;
            else
                return false;
    }

    /**
     * 增加一个角色和授权权限
     * @param roleName
     * @param menus
     * @return
     */
    @Transactional
    @RequestMapping(value = "/addRole")
    @ResponseBody
    public Object addRole(@RequestParam("roleName") String roleName, @RequestParam("menus") String menus) {
        Role role = new Role(roleName);
        int n = roleService.addRole(role);//增加用户
        int roleID = role.getRoleID();
        System.err.println(roleID);
        ArrayList<Map> list = JSON.parseObject(menus, new TypeReference<ArrayList<Map>>() {});//把json转为list
        List<String> list2 = new ArrayList<>();
        for (Map map : list) {
            list2.add(map.get("id").toString());//只要ID
            String children = map.get("children").toString();//获取所有子选项
            ArrayList<Map> list1 = JSON.parseObject(children, new TypeReference<ArrayList<Map>>() {
            });//子选项的值
            for (Map map1 : list1) {
                list2.add(map1.get("id").toString());//只要ID
            }
        }
            List<MenuRole> list3 =  new ArrayList<>();
            for (String s : list2) {
                list3.add(new MenuRole(roleID,Integer.parseInt(s)));
            }
            roleService.addMenuRoleByID(list3);//重新附权限
            if (n > 0)
                return true;
            else
                return false;
    }


}