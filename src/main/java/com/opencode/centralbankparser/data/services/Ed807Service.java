package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.Ed807Dao;
import com.opencode.centralbankparser.data.entities.Ed807Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ed807Service implements ServiceDataInterface<Ed807Entity> {
    @Autowired
    private Ed807Dao dao;

    @Override
    public List<Ed807Entity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Ed807Entity entity) {
        dao.save(entity);
    }

    @Override
    public void update(Ed807Entity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Ed807Entity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

