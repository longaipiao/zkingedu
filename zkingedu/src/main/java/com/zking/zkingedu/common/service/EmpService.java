package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Emp;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpService {

    /**
     * 测试二级缓存
     * @return
     */
    List<Emp> getemps();

    /**
     * 登录
     */
    Emp login(String name,String pass);

    /**
     *根据emp名查emp
     * @param empname
     * @return emp
     */
    Emp findByEmpName(String empname);

}
