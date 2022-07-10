package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.RegulationAccountTypeDao;
import com.opencode.centralbankparser.references.entities.RegulationAccountTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegulationAccountTypeService implements ServiceReferencesInterface<RegulationAccountTypeEntity> {
    @Autowired
    private RegulationAccountTypeDao dao;

    @Override
    public List<RegulationAccountTypeEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(RegulationAccountTypeEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(RegulationAccountTypeEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<RegulationAccountTypeEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public RegulationAccountTypeEntity findById(Long id) {
        return dao.findById(id);
    }
}

