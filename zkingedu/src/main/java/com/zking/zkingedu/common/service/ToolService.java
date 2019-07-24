package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Tool;
import org.apache.ibatis.annotations.Param;

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
    public PageInfo<Tool> findAll(Tool tool,Integer page, Integer pageSize);


    /**
     * 增加工具的方法
     */
    public int addTool(Tool tool);

    /**
     * 根据工具id查询图片
     */
    public Tool findImg(Integer toolID);

    /**
     * 根据工具id修改的方法
     */
    public int updateTool(Tool tool);

    //根据工具id生成的方法
    public int deleteToolById(Integer tid);
}
