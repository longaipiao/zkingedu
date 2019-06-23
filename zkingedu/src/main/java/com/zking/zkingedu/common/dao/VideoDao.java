package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Video;

/**
 * 视频接口
 */
public interface VideoDao {


    /**
     * 根据章节id  查询视频
     * @param sid
     * @return
     * yan
     */
    Video getVideoById(Integer sid);

}
