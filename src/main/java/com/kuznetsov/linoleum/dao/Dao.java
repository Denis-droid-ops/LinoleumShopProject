package com.kuznetsov.linoleum.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T,K>{
    T save(T entity);
    Optional<T> findById(K id);
    List<T> findAll();
    void update(T entity);
    boolean delete(K id);

}
