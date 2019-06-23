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
}
