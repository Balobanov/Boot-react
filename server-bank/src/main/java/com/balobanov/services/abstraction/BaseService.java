package com.balobanov.services.abstraction;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T save(T t);
    T update(T t);
    T delete(T t);
}
