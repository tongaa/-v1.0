package com.mengxugu2.demo.config.shiroconfig;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: shiro配置类
 * @author: tgw
 * @create: 2020-09-20 17:42
 */
@Configuration
public class ShiroConfig {
//3、ShiroFilterFactoryBean

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
/*       添加shiro过滤器
        anon:无需认证就可以访问
        authc:必须认证 了才能让问
        user:必须拥有记住我功能才能用
        perms:拥有对某 个资源的权限才能访间; .
        role: 拥有某个角色权限才能访问
*/
    Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/logout", "logout");
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问

        //excludePathPatterns("/", "/index.html", "/login","/css/*", "/img/*", "/js/*");

        filterMap.put("/css/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/main/**", "anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterMap); //
        //需要登录的页面
        bean.setLoginUrl("/toLogin");
        //登录成功的页面
       // bean.setSuccessUrl("/index");
       // bean.setUnauthorizedUrl("");

        return bean;
    }

    //2、DafauLtWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//    关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //1、创建realm对象
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }
}
