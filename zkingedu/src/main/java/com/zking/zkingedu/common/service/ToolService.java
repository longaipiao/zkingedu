package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Tool;

import java.util.List;

/**
 * 开发者工具接口
 */
public interface ToolService {
    /**
     * 查询所有的工具信息
     * @param
     * @return
     */
    public PageInfo<Tool> findAll(Integer page, Integer pageSize);
}
