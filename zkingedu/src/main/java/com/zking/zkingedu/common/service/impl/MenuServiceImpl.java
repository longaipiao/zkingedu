package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.MenuDao;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单接口  实现层
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuByRoleID(Integer roleID) {
        return menuDao.getMenuByRoleID(roleID);
    }

    @Override
    public List<Menu> getMenus() {
        return menuDao.getMenus();
    }
}
