package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.AccountsDao;
import com.opencode.centralbankparser.data.entities.AccountsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsService implements ServiceDataInterface<AccountsEntity> {
    @Autowired
    private AccountsDao dao;
    @Override
    public List<AccountsEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(AccountsEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(AccountsEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public AccountsEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

