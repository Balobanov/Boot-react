package com.balobanov.services;

import com.balobanov.models.base.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
abstract public class AbstractBaseService<T extends BaseModel, I extends Serializable> implements com.balobanov.services.abstraction.BaseService<T> {


    private JpaRepository<T, I> dao;

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
