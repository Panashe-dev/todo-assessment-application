package com.lupart.technologies.TODO.service;

import java.util.List;

public interface BaseInterface<T,E> {
    T save(E e);
    T update(Integer i,E e);
    List<T> findAll();
    T findById(Integer id);
    boolean deleteById(Integer id);
}
