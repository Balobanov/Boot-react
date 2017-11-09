package com.balobanov.config.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class GlobalSecurityConfig extends GlobalMethodSecurityConfiguration {

    private AclPermissionEvaluator aclPermissionEvaluator;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        FutureMethodSecurityExpressionHandler futureMethodSecurityExpressionHandler = new FutureMethodSecurityExpressionHandler();
        futureMethodSecurityExpressionHandler.setPermissionEvaluator(aclPermissionEvaluator);
        return futureMethodSecurityExpressionHandler;
    }

    private class FutureMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

        @Override
        public Object filter(Object filterTarget, Expression filterExpression, EvaluationContext ctx) {
            if(filterTarget instanceof Future) {
                try {
                    Object o = ((Future) filterTarget).get();
                    Object filtered = super.filter(o, filterExpression, ctx);
                    return CompletableFuture.supplyAsync(() -> filtered);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            return super.filter(filterTarget, filterExpression, ctx);
        }
    }

    @Autowired
    public void setAclPermissionEvaluator(AclPermissionEvaluator aclPermissionEvaluator) {
        this.aclPermissionEvaluator = aclPermissionEvaluator;
    }
}