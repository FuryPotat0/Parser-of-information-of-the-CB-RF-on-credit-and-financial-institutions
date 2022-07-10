package com.opencode.centralbankparser.data.daos;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface DaoDataInterface<T> {
    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(Long id) throws DataIntegrityViolationException;

    T findById(Long id);

    void deleteAll();
}
