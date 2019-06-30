package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Menu;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuService {
    /**
     * 根据角色ID获取单个角色的权限
     */
    List<Menu> getMenuByRoleID(Integer roleID);
    /**
     * 获取所有菜单项
     */
    List<Menu> getMenus();
}
