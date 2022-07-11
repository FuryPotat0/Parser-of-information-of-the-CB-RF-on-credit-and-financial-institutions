package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.AccountStatusDao;
import com.opencode.centralbankparser.references.entities.AccountStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusService implements ServiceReferencesInterface<AccountStatusEntity> {
    @Autowired
    private AccountStatusDao dao;

    @Override
    public List<AccountStatusEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(AccountStatusEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(AccountStatusEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<AccountStatusEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public AccountStatusEntity findById(Long id) {
        return dao.findById(id);
    }
}

