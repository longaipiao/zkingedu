package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.SectionDao;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.SectionService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 根据课程id获取所有的章节视频  模糊查询 分页
     * @param cid
     * @param pageBean
     * @return
     */
    @Override
    public ResultUtil getSectionByCidAndPageSearch(Integer cid, PageBean<Section> pageBean) {
        ResultUtil result = new ResultUtil();
        try {
            Page<Object> objects = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<Section> sections = sectionDao.getSectionByCidAndPageSearch(pageBean.getT(), cid);
            result.setCode(0);
            result.setMsg("success");
            result.setCount(String.valueOf(objects.getTotal()));
            result.setData(sections);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
        return result;
    }


    /**
     * 添加课程
     * @param section
     * @return
     * yan
     */
    @Override
    public int addSection(Section section) {
        return sectionDao.addSection(section);
    }



    /**
     * 根据id  查询章节信息
     * yan
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Section getSectionById(Integer id) {
        return sectionDao.getSectionById(id);
    }

    /**
     * 修改章节
     * yan
     * @param section
     * @return
     */
    @Transactional
    @Override
    public int updateSection(Section section) {
        return sectionDao.updateSection(section);
    }


    /**
     * 批量删除章节
     * yan
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public int delSections(List<Integer> ids) {
        return sectionDao.delSections(ids);
    }
}
