package com.balobanov.services;

import com.balobanov.models.base.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
abstract public class AbstractBaseService<T extends BaseModel, I extends Serializable> implements com.balobanov.services.abstraction.BaseService<T> {

    private JpaRepository<T, I> dao;
    private MutableAclService mutableAclService;
    private ObjectIdentityRetrievalStrategy identityRetrievalStrategy;

    @Autowired
    public void setIdentityRetrievalStrategy(ObjectIdentityRetrievalStrategy identityRetrievalStrategy) {
        this.identityRetrievalStrategy = identityRetrievalStrategy;
    }

    @Autowired
    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Autowired
    public void setDao(JpaRepository<T, I> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public T save(T t) {
        T saved = dao.save(t);
        ObjectIdentity oid = new ObjectIdentityImpl(t.getClass(), t.getId());
        MutableAcl acl = mutableAclService.createAcl(identityRetrievalStrategy.getObjectIdentity(t));
        acl.insertAce(0, BasePermission.ADMINISTRATION, new PrincipalSid(
                "admin@gmail.com"), true);
        acl.insertAce(1, BasePermission.DELETE, new GrantedAuthoritySid(
                "ROLE_ADMIN"), true);
        mutableAclService.updateAcl(acl);
        return saved;
    }

    @Override
    public T update(T t) {
        return dao.save(t);
    }

    @Override
    public T delete(T t) {
        dao.delete(t);
        return t;
    }
}
