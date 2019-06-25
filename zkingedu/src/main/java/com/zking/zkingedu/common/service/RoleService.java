package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Role;

import java.util.List;

/**
 * 角色接口
 */
public interface RoleService {
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

}
