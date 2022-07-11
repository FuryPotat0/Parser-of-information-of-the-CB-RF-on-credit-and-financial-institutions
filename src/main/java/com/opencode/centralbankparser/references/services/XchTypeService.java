package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.XchTypeDao;
import com.opencode.centralbankparser.references.entities.XchTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XchTypeService implements ServiceReferencesInterface<XchTypeEntity>{
    @Autowired
    private XchTypeDao dao;

    @Override
    public List<XchTypeEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(XchTypeEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(XchTypeEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<XchTypeEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public XchTypeEntity findById(Long id) {
        return dao.findById(id);
    }
}

