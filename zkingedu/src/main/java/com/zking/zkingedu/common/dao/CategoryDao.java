package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题库类别接口
 */
public interface CategoryDao {
    //查询所有的题库
    List<Category> getAll();
    //根据父id查询子id
    List<Category> getAllfid(@Param("fid") Integer fid);

    //增加体系
    Integer addCategory(Category category);
    //修改体系停用状态
    Integer updatety(@Param("aid") Integer aid);
    //修改体系开启状态
    Integer updatekq(@Param("aid") Integer aid);
    //删除体系下面的所有
    Integer delete(@Param("aid") Integer aid,@Param("fid") Integer fid);
    //根据id修改体系名字
    Integer updateName(@Param("aid") Integer aid,@Param("name") String name,@Param("fid") Integer fid);
    //根据id修改java初级名字
    Integer updateNames(@Param("aid") Integer aid,@Param("fid") Integer fid,@Param("name") String name);

}
