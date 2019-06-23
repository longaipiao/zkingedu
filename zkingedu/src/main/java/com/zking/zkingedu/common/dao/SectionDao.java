package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Section;

import java.util.List;

/**
 * 章节接口
 */
public interface SectionDao {


    /**
     * 根据课程id获取所有的章节
     * @param cid
     * @return
     * yan
     */
    List<Section> getSectionsBycid(Integer cid);

}
