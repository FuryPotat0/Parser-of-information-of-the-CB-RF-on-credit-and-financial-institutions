package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.CreationReasonDao;
import com.opencode.centralbankparser.references.entities.CreationReasonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreationReasonService implements ServiceReferencesInterface<CreationReasonEntity> {
    @Autowired
    private CreationReasonDao dao;

    @Override
    public List<CreationReasonEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(CreationReasonEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(CreationReasonEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<CreationReasonEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public CreationReasonEntity findById(Long id) {
        return dao.findById(id);
    }
}

