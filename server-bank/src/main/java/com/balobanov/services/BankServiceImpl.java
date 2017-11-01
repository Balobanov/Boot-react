package com.balobanov.services;

import com.balobanov.models.Bank;
import com.balobanov.services.abstraction.BankService;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Transactional
public class BankServiceImpl extends AbstractBaseService<Bank, Long> implements BankService {

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Future<Bank> save(Bank t) {
        return CompletableFuture.supplyAsync(() -> {
            Bank saved = dao.save(t);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ObjectIdentity oid = new ObjectIdentityImpl(saved.getClass(), saved.getId());
            MutableAcl acl = mutableAclService.createAcl(oid);
            acl.insertAce(0, BasePermission.READ, new GrantedAuthoritySid("ROLE_USER"), true);
            acl.insertAce(1, BasePermission.READ, new PrincipalSid(user.getUsername()), true);
            acl.insertAce(2, BasePermission.WRITE, new PrincipalSid(user.getUsername()), true);
            acl.insertAce(3, BasePermission.DELETE, new PrincipalSid(user.getUsername()), true);
            acl.insertAce(4, BasePermission.ADMINISTRATION, new PrincipalSid(user.getUsername()), true);
            mutableAclService.updateAcl(acl);
           return saved;
        });
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Future<Bank> delete(Bank bank) {
        return super.delete(bank);
    }
}
