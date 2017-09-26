package com.balobanov.config.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

@Configuration
@EnableCaching
public class AclConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ObjectIdentityRetrievalStrategyImpl objectIdentityRetrievalStrategy() {
        return new ObjectIdentityRetrievalStrategyImpl();
    }

    @Bean
    public MutableAclService aclService() {
        DefaultPermissionGrantingStrategy permissionGrantingStrategy = new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());

        AclAuthorizationStrategyImpl authorizationStrategy = new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));

        SpringCacheBasedAclCache aclCache = new SpringCacheBasedAclCache(new ConcurrentMapCache("aclCache"), permissionGrantingStrategy, authorizationStrategy);

        BasicLookupStrategy lookupStrategy = new BasicLookupStrategy(dataSource, aclCache, authorizationStrategy, permissionGrantingStrategy);

        JdbcMutableAclService jdbcMutableAclService = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
        jdbcMutableAclService.setClassIdentityQuery("SELECT @@IDENTITY");
        jdbcMutableAclService.setSidIdentityQuery("SELECT @@IDENTITY");

        return jdbcMutableAclService;
    }

    @Bean
    @Autowired
    public AclPermissionEvaluator aclPermissionEvaluator(AclService aclService) {
        return new AclPermissionEvaluator(aclService);
    }
}
