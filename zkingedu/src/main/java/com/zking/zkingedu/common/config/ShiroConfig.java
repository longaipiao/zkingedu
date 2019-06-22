package com.zking.zkingedu.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import com.zking.zkingedu.common.shiro.MyRealm;
import com.zking.zkingedu.common.utils.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String,String> filterChainDefinitionMap = new HashMap<String,String>();
        shiroFilterFactoryBean.setLoginUrl("/login");
        /*shiroFilterFactoryBean.setUnauthorizedUrl("/index");
        shiroFilterFactoryBean.setSuccessUrl("/yesRole");*/


        //filterChainDefinitionMap.put("/admin","perms[查看用户,增加用户]");
        filterChainDefinitionMap.put("/*","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);
        //散列次数
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);
        System.out.println();
        hashedCredentialsMatcher.toString();
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm shiroRealm() {
        MyRealm shiroRealm = new MyRealm();
        System.out.println();
        //shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher()); // 原来在这里
        return shiroRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }



    @Bean
    public PasswordHelper passwordHelper(){
        return new PasswordHelper();
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
