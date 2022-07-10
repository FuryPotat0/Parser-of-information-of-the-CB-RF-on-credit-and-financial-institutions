package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.AccRstrDao;
import com.opencode.centralbankparser.references.entities.AccRstrEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccRstrService implements ServiceReferencesInterface<AccRstrEntity> {
    @Autowired
    private AccRstrDao dao;

    @Override
    public List<AccRstrEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(AccRstrEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(AccRstrEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<AccRstrEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public AccRstrEntity findById(Long id) {
        return dao.findById(id);
    }
}

