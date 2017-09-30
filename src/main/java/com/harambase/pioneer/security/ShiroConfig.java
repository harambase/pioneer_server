package com.harambase.pioneer.security;

import java.util.*;

import com.harambase.pioneer.security.ShiroDbRealm;
import com.harambase.pioneer.security.properties.ShiroSessionListener;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig{


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        /**
         * 默认的登陆访问url
         */
        shiroFilter.setLoginUrl("/auth");
        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/welcome");
        /**
         * 没有权限跳转的url
         */
        shiroFilter.setUnauthorizedUrl("/403");
        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         */
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("/styles/**", "anon");
        hashMap.put("/scripts/**", "anon");
        hashMap.put("/plugins/**", "anon");
        hashMap.put("/assets/img/**", "anon");
        hashMap.put("/fonts/**", "anon");
        hashMap.put("/auth", "anon");
        hashMap.put("/", "anon");
        hashMap.put("/admin/login", "anon");
        hashMap.put("/request/user/register", "anon");
        hashMap.put("/reg", "anon");
//        hashMap.put("/kaptcha", "anon");
        hashMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(hashMap);

        return shiroFilter;
    }
    /**
     * 项目自定义的Realm
     */
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ShiroDbRealm myRealm = new ShiroDbRealm();
        securityManager.setRealm(myRealm);
        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        //ehCacheManager.setCacheManagerConfigFile("classpath:encache.xml");
//        return ehCacheManager;
//    }

}