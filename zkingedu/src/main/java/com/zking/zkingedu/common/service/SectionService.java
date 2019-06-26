package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Section;

import java.util.List;

/**
 * 章节接口
 */
public interface SectionService {


    /**
     * 根据课程id获取所有的章节
     * @param cid
     * @return
     * yan
     */
    List<Section> getSectionsBycid(Integer cid);

    /**
     * 根据章节的id查询单个章节下的积分
     */
    public int findSectInteg(Integer sectionID);
}
