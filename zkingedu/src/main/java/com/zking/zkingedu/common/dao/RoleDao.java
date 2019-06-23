package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Role;

import java.util.List;

/**
 * 角色接口
 */
public interface RoleDao {
    /**
     * 获取所有角色
     * @return
     */
    List<Role> getRoles();
}
