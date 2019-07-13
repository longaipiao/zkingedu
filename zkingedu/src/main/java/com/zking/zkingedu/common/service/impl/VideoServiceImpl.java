package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.VideoDao;
import com.zking.zkingedu.common.model.Video;
import com.zking.zkingedu.common.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 视频接口  服务实现层
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {


    @Resource
    private VideoDao videoDao;

    /**
     * 根据章节id  查询视频
     *
     * @param sid
     * @return yan
     */
    @Override
    public Video getVideoById(Integer sid) {
        return videoDao.getVideoById(sid);
    }
}
