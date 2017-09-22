package com.balobanov.services.abstraction;

import java.util.List;

public interface BaseService<T> {
    public List<T> getAll();
    public T save(T t);
    public T update(T t);
    public T delete(T t);
}
