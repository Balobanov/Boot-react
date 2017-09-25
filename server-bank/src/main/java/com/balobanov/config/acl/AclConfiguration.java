package com.balobanov.config.acl;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.acls.AclPermissionCacheOptimizer;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

@Configuration
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class AclConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public AclPermissionCacheOptimizer permissionCacheOptimizer() {
        return new AclPermissionCacheOptimizer(aclService());
    }

    @Bean
    public AclPermissionEvaluator permissionEvaluator() {
        return new AclPermissionEvaluator(aclService());
    }


    @Bean
    public JdbcMutableAclService aclService() {
        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
    }

    @Bean
    public BasicLookupStrategy lookupStrategy() {
        return new BasicLookupStrategy(dataSource, aclCache(), aclAuthorizationStrategy(), aclPermissionGrantingStrategy());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Bean
    public PermissionEvaluator aclPermissionEvaluator() {
        return new AclPermissionEvaluator(aclService());
    }

    @Bean
    public MethodSecurityExpressionHandler aclExpressionHandler(){
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        try {
            expressionHandler.setPermissionEvaluator(aclPermissionEvaluator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        expressionHandler.setRoleHierarchy(aclRoleHierarchy());
        return expressionHandler;
    }

    @Bean
    public RoleHierarchy aclRoleHierarchy() {
        RoleHierarchyImpl rh = new RoleHierarchyImpl();
        rh.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return rh;
    }

    @Bean
    public AuditLogger aclAuditLogger() {
        return new ConsoleAuditLogger();
    }

    @Bean
    public PermissionGrantingStrategy aclPermissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(aclAuditLogger());
    }

    @Bean
    public AclCache aclCache() {
        return new EhCacheBasedAclCache(ehcache(), aclPermissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    public Ehcache ehcache(){
        EhCacheFactoryBean factory = new EhCacheFactoryBean();
        factory.setCacheManager(cacheManager());
        factory.setCacheName("aclCache");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public CacheManager cacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setCacheManagerName(CacheManager.DEFAULT_NAME);
        factory.setShared(true);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
