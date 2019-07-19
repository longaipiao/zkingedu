package com.zking.zkingedu.common.controller;
import	java.util.function.IntFunction;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.service.EmpService;
import com.zking.zkingedu.common.service.LogService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private LogService logService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time=dateFormat.format(new Date());

    /**
     * 登录成功把emp放进session
     * @param request
     * @return emp
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object alogin(@RequestParam("name")String name,@RequestParam("pass")String pass, HttpServletRequest request){
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
    @Transactional
    @ResponseBody
    @RequestMapping("/updateEmpState")
    public Object updateEmpState(@RequestParam("id") Integer id,@RequestParam("state") Integer state,HttpServletRequest request) {
        //获取当前登录用户，用户不能对自己进行删除和封禁操作
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        if(emp.getEmpID()==id)
            return false;
        int i = empService.updateStateByEmpID(id, state);
        if(i>0) {
            //放入日志
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "把ID为" + id + "的员工的状态修改为:");
            if (state == 0)
                stringBuilder.append("启用");
            else
                stringBuilder.append("封禁");
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return true;
        }
        else
            return false;
    }
    /**
     *删除员工
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/delEmp")
    public Object delEmp(@RequestParam("empID") Integer id,HttpServletRequest request) {
        //获取当前登录用户，用户不能对自己进行删除和封禁操作
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        if(emp.getEmpID()==id)
            return false;
        int i = empService.delByEmpID(id);
        i+= empService.delEmpRoleByEmpID(id);

        if(i>1) {
            //放入日志
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName()+"把ID为"+id+"的员工删除了");
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return true;
        }
        else
            return false;
    }
    /**
     *修改员工
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateEmp")
    public Object updateEmp(@RequestParam("empID") Integer empid,@RequestParam("empPassword") String password,@RequestParam("roleID")Integer roleid,HttpServletRequest request) {
        int i = empService.updateEmpByEmpID(empid, password);
        i += empService.updateEmpRoleByEmpID(empid,roleid);
        Emp emp =(Emp) request.getSession().getAttribute("emp");
        if(i>1) {
            //放入日志
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "给ID为" + empid + "的员工修改了密码和角色,角色ID为:" + roleid);
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return true;
        }
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
    public Object addEmp(@RequestParam("empName") String empname,@RequestParam("empPassword") String password,@RequestParam("roleID")Integer roleid,HttpServletRequest request) {
        Emp emp = new Emp();
        emp.setEmpName(empname);
        emp.setEmpPassword(password);
        emp.setEmpState(0);
        emp.setEmpTime(time);
        int i = empService.addEmp(emp);
        i += empService.addEmpRole(emp.getEmpID(),roleid);
        Emp emp1 =(Emp) request.getSession().getAttribute("emp");
        if(i>1) {
            //放入日志
            mylog.setEmp(emp1);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp1.getEmpName() + "添加了一个员工，ID为" + emp.getEmpID());
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return true;
        }
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
        if(i>0) {
            //放入日志
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName() + "修改了自己的密码");
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return true;
        }
        else
            return false;
    }



}
