package com.amela.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGeneralRepository<T> {
    Page<T> findAll(Pageable pageable);
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void delete(int id);
}
