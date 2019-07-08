package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CategoryDao;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题库类别接口服务层
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categorydao;

    @Override
    public List<Category> getAll() {
        return categorydao.getAll();
    }

    @Override
    public List<Category> getAllfid(Integer fid) {
        return categorydao.getAllfid(fid);
    }

    @Override
    public Integer addCategory(Category category) {
        return categorydao.addCategory(category);
    }

    @Override
    public Integer updatety(Integer aid) {
        return categorydao.updatety(aid);
    }

    @Override
    public Integer updatekq(Integer aid) {
        return categorydao.updatekq(aid);
    }

    @Override
    public Integer delete(Integer aid, Integer fid) {
        return categorydao.delete(aid, fid);
    }

    @Override
    public Integer updateName(Integer aid, String name,Integer fid) {
        return categorydao.updateName(aid,name,fid);
    }

    @Override
    public Integer updateNames(Integer aid, Integer fid, String name) {
        return categorydao.updateNames(aid, fid, name);
    }
}
