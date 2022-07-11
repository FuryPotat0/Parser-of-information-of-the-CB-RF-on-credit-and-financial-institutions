package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.PartInfoDao;
import com.opencode.centralbankparser.data.entities.PartInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartInfoService implements ServiceDataInterface<PartInfoEntity> {
    @Autowired
    private PartInfoDao dao;

    @Override
    public List<PartInfoEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(PartInfoEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(PartInfoEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public PartInfoEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

