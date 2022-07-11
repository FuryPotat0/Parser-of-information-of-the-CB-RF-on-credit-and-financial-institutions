package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.PtTypeDao;
import com.opencode.centralbankparser.references.entities.PtTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PtTypeService implements ServiceReferencesInterface<PtTypeEntity> {
    @Autowired
    private PtTypeDao dao;

    @Override
    public List<PtTypeEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(PtTypeEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(PtTypeEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<PtTypeEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public PtTypeEntity findById(Long id) {
        return dao.findById(id);
    }
}

