package com.ramon.service;

import java.util.List;

public interface UserService<T> {
    List<T> getAll();
    T save(T element) throws Exception;
    T getById(Long id);
    T update(T element) throws Exception;
    void deleteById(Long id);
}
