package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Emp;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpDao {

    /**
     * 测试二级缓存
     * @return
     */
    List<Emp> getemps();

}
