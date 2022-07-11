package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.RstrListDao;
import com.opencode.centralbankparser.data.entities.RstrListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RstrListService implements ServiceDataInterface<RstrListEntity> {
    @Autowired
    private RstrListDao dao;

    @Override
    public List<RstrListEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(RstrListEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(RstrListEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public RstrListEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

