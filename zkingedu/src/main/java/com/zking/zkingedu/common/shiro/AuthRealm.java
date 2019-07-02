package com.zking.zkingedu.common.shiro;


import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.EmpService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private EmpService empService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //System.err.println("进入认证");
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String empname = utoken.getUsername();
        Emp emp = empService.findByEmpName(empname);
        return new SimpleAuthenticationInfo(emp, emp.getEmpPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //System.err.println("进入授权");
        Emp emp=(Emp) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<Role> roles = emp.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Menu> menuss = role.getMenus();
                if(menuss.size()>0) {
                    for(Menu menu : menuss) {
                        permissions.add(menu.getMenuName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }
}