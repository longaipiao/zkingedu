package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.TitleDao;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.TitleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 题目接口  服务实现层
 */
@Service("titleService")
public class TitleServiceImpl implements TitleService {
    @Resource
    private TitleDao titleDao;

    @Override
    public List<Title> getAll(Title title) {
        return titleDao.getAll(title);
    }

    @Override
    public Integer addTitle(Title title) {
        return titleDao.addTitle(title);
    }

    @Override
    public Integer updatekq(Integer tid) {
        return titleDao.updatekq(tid);
    }

    @Override
    public Integer updategb(Integer tid) {
        return titleDao.updategb(tid);
    }

    @Override
    public Integer deletetim(Integer tid) {
        return titleDao.deletetim(tid);
    }

    @Override
    public List<Title> getAlls(Integer tid) {
        return titleDao.getAlls(tid);
    }

    @Override
    public Integer updatetim(Title title) {
        return titleDao.updatetim(title);
    }
}
