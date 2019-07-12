package com.zking.zkingedu.common.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 *
 * @ClassName Role
 * @Author likai
 **/
@ToString
@Component
public class Role implements Serializable {
    private static final long serialVersionUID = 1812009755321021468L;
    //角色id
    private Integer roleID;
    //角色名
    private String roleName;
    //用户
    private Set<Emp> emps = new HashSet<>();
    //菜单
    private Set<Menu> menus = new HashSet<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Role() {
    }

    public Role(Integer roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
