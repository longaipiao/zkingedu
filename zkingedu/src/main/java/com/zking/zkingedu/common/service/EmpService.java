package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Emp;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpService {

    /**
     * 得到所有员工
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
    /**
     *根据id修改员工状态
     * @param id,state
     * @return int
     */
    int updateStateByEmpID(Integer id,Integer state);
    /**
     *根据id删除员工和关系表
     * @param id,state
     * @return int
     */
    int delByEmpID(Integer id);
    int delEmpRoleByEmpID(Integer id);

    /**
     * 根据id更新员工密码和关系表
     * @param id
     * @return
     */
    int updateEmpByEmpID(Integer id,String password);
    int updateEmpRoleByEmpID(Integer id,Integer roleid);
    /**
     * 根据empid获取roleid
     * @param empid
     * @return
     */
    int getRoleIDByEmpID(Integer empid);


}
