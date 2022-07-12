package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.BicDirectoryEntryDao;
import com.opencode.centralbankparser.data.entities.BicDirectoryEntryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicDirectoryEntryService implements ServiceDataInterface<BicDirectoryEntryEntity> {
    @Autowired
    private BicDirectoryEntryDao dao;

    @Override
    public List<BicDirectoryEntryEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(BicDirectoryEntryEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(BicDirectoryEntryEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public BicDirectoryEntryEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    public List<BicDirectoryEntryEntity> findByEd807Id(Long edId){
        return dao.findByEd807Id(edId);
    }
}

