package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.MenuRole;
import com.zking.zkingedu.common.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色接口
 */
public interface RoleDao {
    /**
     * 获取所有角色
     * @return
     */
    List<Role> getRoles();
    /**
     * 删除单个角色
     * 并且删除对应权限关联表数据
     * @return
     */
    int delRoleByID(Integer roleID);
    int delMenuRoleByID(Integer roleID);

    /**
     * 修改单个角色名
     * @return
     */
    int updateRoleByID(Integer roleID,String roleName);
    /**
     * 增加单个角色权限
     * @return
     */
    int addMenuRoleByID(List<MenuRole> list);
    /**
     * 增加单个角色名
     * @return
     */
    int addRole(Role role);

}
