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
    public PageInfo<Tool> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Tool> tools = toolDao.findAll();
        return new PageInfo<>(tools);
    }
}
