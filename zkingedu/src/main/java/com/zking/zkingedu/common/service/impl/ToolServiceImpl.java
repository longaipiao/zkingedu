package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.ToolDao;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发者工具接口
 */
@Service("toolService")
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolDao toolDao;

    @Override
    public PageInfo<Tool> findAll(Tool tool,Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Tool> tools = toolDao.findAll(tool);
        return new PageInfo<>(tools);
    }

    /**
     * 增加工具的方法
     */
    @Override
    public int addTool(Tool tool) {
        return toolDao.addTool(tool);
    }

    /**
     * 根据工具id查询图片
     * @param toolID 工具id
     * @return
     */
    @Override
    public Tool findImg(Integer toolID) {
        return toolDao.findImg(toolID);
    }

    /**
     *  根据工具id修改的方法
     * @param tool 接受的对象
     * @return
     */
    @Override
    public int updateTool(Tool tool) {
        return toolDao.updateTool(tool);
    }

    /**
     * 根据工具id生成的方法
     * @param tid 工具id
     * @return
     */
    @Override
    public int deleteToolById(Integer tid) {
        return toolDao.deleteToolById(tid);
    }
}




