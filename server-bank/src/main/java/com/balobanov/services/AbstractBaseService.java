package com.balobanov.services;

import com.balobanov.models.base.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.List;

@Transactional
abstract public class AbstractBaseService<T extends BaseModel, I extends Serializable> implements com.balobanov.services.abstraction.BaseService<T> {

    protected JpaRepository<T, I> dao;
    protected MutableAclService mutableAclService;
    protected ObjectIdentityRetrievalStrategy identityRetrievalStrategy;

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
    @Transactional(readOnly = true)
    @RolesAllowed("ROLE_USER")
    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public T save(T t) {
        return dao.save(t);
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
