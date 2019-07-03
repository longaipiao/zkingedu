package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 登录成功把emp放进session
     * @param request
     * @return emp
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object alogin(HttpServletRequest request){
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, pass);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken); //完成登录
            Emp emp = (Emp) subject.getPrincipal();
            if(emp.getEmpState()==0) {
                request.getSession().setAttribute("emp", emp);
                return emp;
            }else
                return new Emp();
        } catch (Exception e) {
            return new Emp();
        }
    }

    /**
     *注销
     * @param request
     * @return
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("emp");
        return "admin/login";
    }
    /**
     *获取登录员工的旧密码
     */
    @ResponseBody
    @RequestMapping("/getSessionEmpPwd")
    public String getSessionEmpPwd(HttpServletRequest request) {
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        return emp.getEmpPassword();
    }
    /**
     *得到所有员工
     */
    @ResponseBody
    @RequestMapping("/getEmps")
    public Object getEmps(int page, int limit,HttpServletRequest request) {
        Page<Object> objects = PageHelper.startPage(page, limit);
        String empName = request.getParameter("empName");
        if(empName==null)
            empName="";
        List<Emp> emps = empService.getemps("%"+empName+"%");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", objects.getTotal());
        map.put("data", emps);
        return map;
    }
    /**
     *更新员工状态
     */
    @ResponseBody
    @RequestMapping("/updateEmpState")
    public Object updateEmpState(@RequestParam("id") Integer id,@RequestParam("state") Integer state) {
        int i = empService.updateStateByEmpID(id, state);
        if(i>0)
            return true;
        else
            return false;
    }
    /**
     *删除员工
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/delEmp")
    public Object delEmp(@RequestParam("empID") Integer id) {
        int i = empService.delByEmpID(id);
        i+= empService.delEmpRoleByEmpID(id);
        if(i>1)
            return true;
        else
            return false;
    }
    /**
     *修改员工
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateEmp")
    public Object updateEmp(@RequestParam("empID") Integer empid,@RequestParam("empPassword") String password,@RequestParam("roleID")Integer roleid) {
        int i = empService.updateEmpByEmpID(empid, password);
        i += empService.updateEmpRoleByEmpID(empid,roleid);
        if(i>1)
            return true;
        else
            return false;
    }
    /**
     *获取员工当前角色
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/getEmpRole")
    public int getEmpRole(@RequestParam("empID")Integer empid) {
        return empService.getRoleIDByEmpID(empid);
    }

    /**
     *添加员工
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/addEmp")
    public Object addEmp(@RequestParam("empName") String empname,@RequestParam("empPassword") String password,@RequestParam("roleID")Integer roleid) {
        Emp emp = new Emp();
        emp.setEmpName(empname);
        emp.setEmpPassword(password);
        emp.setEmpState(0);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        emp.setEmpTime(dateFormat.format(date));
        int i = empService.addEmp(emp);
        i += empService.addEmpRole(emp.getEmpID(),roleid);
        if(i>1)
            return true;
        else
            return false;
    }
    /**
     *员工修改自己密码
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateEmppwd")
    public Object updateEmppwd(@RequestParam("emppwd") String emppwd,HttpServletRequest request) {
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        int i = empService.updateEmpByEmpID(emp.getEmpID(), emppwd);
        logOut(request);
        if(i>0)
            return true;
        else
            return false;
    }


}
