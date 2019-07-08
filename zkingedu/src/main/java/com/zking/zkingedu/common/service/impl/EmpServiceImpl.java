package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台员工接口  实现层
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpDao empDao;

    @Override
    public List<Emp> getemps(String empName) {
        return empDao.getemps(empName);
    }

    @Override
    public Emp login(String name,String pass) {
        return empDao.login(name,pass);
    }

    @Override
    public Emp findByEmpName(String empname) {
        return empDao.findByEmpName(empname);
    }

    @Override
    public int updateStateByEmpID(Integer id, Integer state) {
        return empDao.updateStateByEmpID(id,state);
    }

    @Override
    public int delByEmpID(Integer id) {
        return empDao.delByEmpID(id);
    }

    @Override
    public int delEmpRoleByEmpID(Integer id) {
        return empDao.delEmpRoleByEmpID(id);
    }

    @Override
    public int updateEmpByEmpID(Integer id,String name) {
        return empDao.updateEmpByEmpID(id,name);
    }

    @Override
    public int updateEmpRoleByEmpID(Integer id,Integer roleid) {
        return empDao.updateEmpRoleByEmpID(id,roleid);
    }

    @Override
    public int getRoleIDByEmpID(Integer empid) {
        return empDao.getRoleIDByEmpID(empid);
    }

    @Override
    public int addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public int addEmpRole(Integer empID, Integer roleID) {
        return empDao.addEmpRole(empID,roleID);
    }
}
