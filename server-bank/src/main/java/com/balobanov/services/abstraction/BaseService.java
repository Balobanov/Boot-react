package com.balobanov.services.abstraction;

import com.balobanov.models.base.BaseModel;

import java.util.List;
import java.util.concurrent.Future;

public interface BaseService<T extends BaseModel> {
    Future<List<T>> getAll();
    T save(T t);
    void save(List<T> list);
    Future<T> update(T t);
    Future<T> delete(T t);
}
