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
public class EmpServiceImpl implements
        EmpService {

    @Resource
    private EmpDao empDao;

    @Override
    public List<Emp> getemps() {
        return empDao.getemps();
    }

    @Override
    public Emp login(String name,String pass) {
        return empDao.login(name,pass);
    }

    @Override
    public Emp findByEmpName(String empname) {
        return empDao.findByEmpName(empname);
    }
}
