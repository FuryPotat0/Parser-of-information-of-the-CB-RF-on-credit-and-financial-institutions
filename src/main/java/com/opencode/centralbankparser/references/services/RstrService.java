package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.RstrDao;
import com.opencode.centralbankparser.references.entities.RstrEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RstrService implements ServiceReferencesInterface<RstrEntity> {
    @Autowired
    private RstrDao dao;

    @Override
    public List<RstrEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(RstrEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(RstrEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<RstrEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public RstrEntity findById(Long id) {
        return dao.findById(id);
    }
}

