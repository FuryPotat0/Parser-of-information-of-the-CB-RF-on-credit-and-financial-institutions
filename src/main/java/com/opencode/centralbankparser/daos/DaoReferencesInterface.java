package com.opencode.centralbankparser.daos;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public interface DaoReferencesInterface<T> {
    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(Long id) throws DataIntegrityViolationException;

    Optional<T> findByCode(String code);

    T findById(Long id);
}
