package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.InfoTypeCodeDao;
import com.opencode.centralbankparser.references.entities.InfoTypeCodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoTypeCodeService implements ServiceReferencesInterface<InfoTypeCodeEntity> {
    @Autowired
    private InfoTypeCodeDao dao;

    @Override
    public List<InfoTypeCodeEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(InfoTypeCodeEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(InfoTypeCodeEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<InfoTypeCodeEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public InfoTypeCodeEntity findById(Long id) {
        return dao.findById(id);
    }
}

