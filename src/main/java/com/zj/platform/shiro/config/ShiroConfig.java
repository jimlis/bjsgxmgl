package com.zj.platform.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zj.platform.common.web.listener.BDSessionListener;
import com.zj.platform.shiro.filter.JWTAuthenticationFilter;
import com.zj.platform.shiro.realm.JWTAuthorizingRealm;
import com.zj.platform.shiro.realm.ModularRealm;
import com.zj.platform.shiro.realm.SysUserAuthorizingRealm;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * <pre>
 * . cache ehcache
 * . realm(cache)
 * . securityManager（realm）
 * . ShiroFilterFactoryBean 注册
 */
@Configuration
public class ShiroConfig {

    
    @Bean
    SessionDAO sessionDAO() {
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO);
        
        sessionManager.setSessionIdCookieEnabled(true);
        SimpleCookie simpleCookie=new SimpleCookie("shiro.sesssion");
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }
    
    @Bean
    public CacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        CacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }
    
    @Bean
    SysUserAuthorizingRealm userRealm(CacheManager cacheManager) {
        SysUserAuthorizingRealm userRealm = new SysUserAuthorizingRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }
    
    @Bean
    JWTAuthorizingRealm jwtAuthorizingRealm() {
        JWTAuthorizingRealm jwtAuthorizingRealm = new JWTAuthorizingRealm();
        return jwtAuthorizingRealm;
    }

    @Bean
    ModularRealm authenticator(SysUserAuthorizingRealm userRealm, JWTAuthorizingRealm jwtAuthorizingRealm) {
        ModularRealm authenticator = new ModularRealm();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(jwtAuthorizingRealm);
        authenticator.setRealms(realms);
        return authenticator;
    }
    
    @Bean
    Authorizer authorizer(SysUserAuthorizingRealm userRealm,JWTAuthorizingRealm jwtAuthorizingRealm) {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(jwtAuthorizingRealm);
        authorizer.setRealms(realms);
        return authorizer;
    }
    
    @Bean
    SecurityManager securityManager(SysUserAuthorizingRealm userRealm,ModularRealm authenticator,
    		 Authorizer authorizer,CacheManager cacheManager,SessionManager sessionManager,JWTAuthorizingRealm jwtAuthorizingRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(jwtAuthorizingRealm);
        manager.setRealms(realms);
        manager.setAuthenticator(authenticator);
        manager.setAuthorizer(authorizer);
        manager.setCacheManager(cacheManager);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        // 添加jwt过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/405");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/api/**", "jwt"); // api
        filterChainDefinitionMap.put("/getToken", "anon"); // token
        filterChainDefinitionMap.put("/getDDUser", "anon"); //
        filterChainDefinitionMap.put("/getDDUserInfo", "anon"); //
        filterChainDefinitionMap.put("/swagger-ui.html**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/shiro/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/view/pc/html/sys/login/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/pc/login.html", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
