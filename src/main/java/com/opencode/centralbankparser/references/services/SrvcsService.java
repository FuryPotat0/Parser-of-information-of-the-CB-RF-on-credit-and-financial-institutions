package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.SrvcsDao;
import com.opencode.centralbankparser.references.entities.SrvcsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrvcsService implements ServiceReferencesInterface<SrvcsEntity>{
    @Autowired
    private SrvcsDao dao;

    @Override
    public List<SrvcsEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(SrvcsEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(SrvcsEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<SrvcsEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public SrvcsEntity findById(Long id) {
        return dao.findById(id);
    }
}

