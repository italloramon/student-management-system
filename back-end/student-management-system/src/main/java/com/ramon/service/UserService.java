package com.ramon.service;

import java.util.List;

public interface UserService<T> {
    List<T> getAll();
    T save(T element);
    T getById(Long id);
    T update(T element);
    void deleteById(Long id);
}
