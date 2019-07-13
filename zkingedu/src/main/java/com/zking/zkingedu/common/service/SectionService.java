package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;

import java.util.List;

/**
 * 章节接口
 */
public interface SectionService {


    /**
     * 根据课程id获取所有的章节
     *
     * @param cid
     * @return yan
     */
    List<Section> getSectionsBycid(Integer cid);

    /**
     * 根据章节的id查询单个章节下的积分
     */
    public int findSectInteg(Integer sectionID);


    /**
     * 根据课程id获取所有的章节视频  模糊查询 分页
     *
     * @param cid
     * @param pageBean
     * @return
     */
    ResultUtil getSectionByCidAndPageSearch(Integer cid, PageBean<Section> pageBean);


    /**
     * 添加课程
     *
     * @param section
     * @return yan
     */
    int addSection(Section section);


    /**
     * 根据id  查询章节信息
     * yan
     *
     * @param id
     * @return
     */
    Section getSectionById(Integer id);


    /**
     * 修改章节
     * yan
     *
     * @param section
     * @return
     */
    int updateSection(Section section);


    /**
     * 批量删除章节
     * yan
     *
     * @param ids
     * @return
     */
    int delSections(List<Integer> ids);


}
