package com.zking.zkingedu.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zking.zkingedu.common.shiro.AuthRealm;
import com.zking.zkingedu.common.shiro.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro的配置类
 *
 * @author Administrator
 */
@Configuration
public class ShiroConfiguration {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/admin");
        bean.setSuccessUrl("/admin/index");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/admin/login", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/css", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/extends", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/fonts", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/images", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/js", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/lib/layui", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/WEB-INF", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin/favicon.ico", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/admin", "anon");
        filterChainDefinitionMap.put("/admin/logOut", "anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/admin/index", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/admin/*", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/admin/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/user/findUser", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/User/updateTool", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/User/addTool", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/findTool", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/pst/deletePandTcoms", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/pst/deletePost", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/pst/updatePstate", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/pst/gethtpsot", "authc");//表示需要认证才可以访问
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
//        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * 配置shiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}