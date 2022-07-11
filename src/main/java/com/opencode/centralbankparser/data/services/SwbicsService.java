package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.SwbicsDao;
import com.opencode.centralbankparser.data.entities.SwbicsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwbicsService implements ServiceDataInterface<SwbicsEntity> {
    @Autowired
    private SwbicsDao dao;

    @Override
    public List<SwbicsEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(SwbicsEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(SwbicsEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public SwbicsEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

