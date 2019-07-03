package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.RoleDao;
import com.zking.zkingedu.common.model.MenuRole;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色接口服务层  实现
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Override
    public List<Role> getRoles(String roleName) {
        return roleDao.getRoles(roleName);
    }

    @Override
    public int delRoleByID(Integer roleID) {
        return roleDao.delRoleByID(roleID);
    }

    @Override
    public int delMenuRoleByID(Integer roleID) {
        return roleDao.delMenuRoleByID(roleID);
    }

    @Override
    public int updateRoleByID(Integer roleID, String roleName) {
        return roleDao.updateRoleByID(roleID,roleName);
    }

    @Override
    public int addMenuRoleByID(List<MenuRole> list) {
        return roleDao.addMenuRoleByID(list);
    }

    @Override
    public int addRole(Role role) {
        return roleDao.addRole(role);
    }
}
