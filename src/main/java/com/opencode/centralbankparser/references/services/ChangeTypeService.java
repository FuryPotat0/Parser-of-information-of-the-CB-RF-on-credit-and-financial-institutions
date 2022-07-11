package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.ChangeTypeDao;
import com.opencode.centralbankparser.references.entities.ChangeTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChangeTypeService implements ServiceReferencesInterface<ChangeTypeEntity> {
    @Autowired
    private ChangeTypeDao dao;

    @Override
    public List<ChangeTypeEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(ChangeTypeEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(ChangeTypeEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<ChangeTypeEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public ChangeTypeEntity findById(Long id) {
        return dao.findById(id);
    }
}

