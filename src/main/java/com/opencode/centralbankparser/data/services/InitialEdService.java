package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.InitialEdDao;
import com.opencode.centralbankparser.data.entities.InitialEdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitialEdService implements ServiceDataInterface<InitialEdEntity> {
    @Autowired
    private InitialEdDao dao;

    @Override
    public List<InitialEdEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(InitialEdEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(InitialEdEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public InitialEdEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

