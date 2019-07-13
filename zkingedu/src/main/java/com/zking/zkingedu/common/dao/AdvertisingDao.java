package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告接口
 */
public interface AdvertisingDao {
    //查询所有的广告信息并且分页
    public List<Advertising> getAll(Advertising advertising);

    //增加广告
    Integer addAdvertsing(Advertising advertising);

    //查询所有
    List<Advertising> getAlls();

    //根据id修改广告
    Integer updateadvertsing(Advertising advertising);

    //根据广告id修改广告
    Integer deleteadvertising(@Param("aid") Integer aid);

    //根据广告id修改广告的上架状态
    Integer updatesjState(@Param("aid") Integer aid);

    //根据广告id修改广告的下架状态
    Integer updatexjState(@Param("aid") Integer aid);

    //查询所有的广告状态上架的状态不能超过五条
    Integer getsjzt();

}
