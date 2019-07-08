package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.AdvertisingDao;
import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告接口实现  实现
 */
@Service("advertisingService")
public class AdvertisingServiceImpl implements AdvertisingService {
    @Resource
    private AdvertisingDao advertisingDao;

    @Override
    public PageInfo<Advertising> getAll(Advertising advertising, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Advertising> all = advertisingDao.getAll(advertising);
        return new PageInfo<>(all);
    }

    @Override
    public Integer addAdvertsing(Advertising advertising) {
        return advertisingDao.addAdvertsing(advertising);
    }

    @Override
    public List<Advertising> getAlls() {
        return advertisingDao.getAlls();
    }

    @Override
    public Integer updateadvertsing(Advertising advertising) {
        return advertisingDao.updateadvertsing(advertising);
    }

    @Override
    public Integer deleteadvertising(Integer aid) {
        return advertisingDao.deleteadvertising(aid);
    }

    @Override
    public Integer updatesjState(Integer aid) {
        return advertisingDao.updatesjState(aid);
    }

    @Override
    public Integer updatexjState(Integer aid) {
        return advertisingDao.updatexjState(aid);
    }

    @Override
    public Integer getsjzt() {
        return advertisingDao.getsjzt();
    }
}
