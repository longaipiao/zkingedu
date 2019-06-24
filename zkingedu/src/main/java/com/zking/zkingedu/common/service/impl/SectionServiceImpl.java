package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SectionDao;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.SectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 章节接口  实现层
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {


    @Resource
    private SectionDao sectionDao;

    /**
     * 根据课程id获取所有的章节
     * @param cid 章节id
     * @return
     * yan
     */
    @Override
    public List<Section> getSectionsBycid(Integer cid) {
        return sectionDao.getSectionsBycid(cid);
    }

    /**
     * 根据章节的id查询单个章节下的积分
     * @param sectionID   章节id
     * @return
     */
    @Override
    public int findSectInteg(Integer sectionID) {
        return sectionDao.findSectInteg(sectionID);
    }
}
