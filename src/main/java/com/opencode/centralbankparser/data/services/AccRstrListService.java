package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.AccRstrListDao;
import com.opencode.centralbankparser.data.entities.AccRstrListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccRstrListService implements ServiceDataInterface<AccRstrListEntity> {
    @Autowired
    private AccRstrListDao dao;

    @Override
    public List<AccRstrListEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(AccRstrListEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(AccRstrListEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public AccRstrListEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

