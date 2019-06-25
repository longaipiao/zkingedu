package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.RoleDao;
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
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public int delRoleByID(Integer roleID) {
        return roleDao.delRoleByID(roleID);
    }

    @Override
    public int delMenuRoleByID(Integer roleID) {
        return roleDao.delMenuRoleByID(roleID);
    }
}
