package com.baijia.lhy.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean(步骤3)
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
         * anon：无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须用有了 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/*", "anon");

        bean.setFilterChainDefinitionMap(filterMap);
//        bean.setLoginUrl("/toLogin");//没有认证，跳转到的页面
//        bean.setUnauthorizedUrl("/unauthorizedUrl");//没有授权，跳转到的页面
        return bean;
    }

    //DefaultWebSecurityManager(步骤2)
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;

    }

    //创建realm对象(步骤1)
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
