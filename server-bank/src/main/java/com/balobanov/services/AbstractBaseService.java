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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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
    public Future<List<T>> getAll() {
        return CompletableFuture.supplyAsync(() -> dao.findAll());
    }

    @Override
    public Future<T> save(T t) {
        return CompletableFuture.supplyAsync(() -> dao.save(t));
    }

    @Override
    public Future<T> update(T t) {
        return CompletableFuture.supplyAsync(() -> dao.save(t));
    }

    @Override
    public Future<T> delete(T t) {
        return CompletableFuture.supplyAsync(() -> {dao.delete(t); return t;});
    }
}
