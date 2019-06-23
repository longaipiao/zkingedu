package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Tool;

import java.util.List;

/**
 * 开发者工具接口
 */
public interface ToolDao {

    /**
     * 查询所有的工具信息
     * @param
     * @return
     */
    public List<Tool> findAll();

}
