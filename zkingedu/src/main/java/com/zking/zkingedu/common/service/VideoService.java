package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Video;

/**
 * 视频接口
 */
public interface VideoService {

    /**
     * 根据章节id  查询视频
     * @param sid  章节id
     * @return
     * yan
     */
    Video getVideoById(Integer sid);
}
