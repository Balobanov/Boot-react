package com.balobanov.services.abstraction;

import java.util.List;
import java.util.concurrent.Future;

public interface BaseService<T> {
    Future<List<T>> getAll();
    Future<T> save(T t);
    Future<T> update(T t);
    Future<T> delete(T t);
}
