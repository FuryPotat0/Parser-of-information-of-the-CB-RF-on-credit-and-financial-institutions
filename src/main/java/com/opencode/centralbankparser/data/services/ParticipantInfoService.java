package com.opencode.centralbankparser.data.services;

import com.opencode.centralbankparser.data.daos.ParticipantInfoDao;
import com.opencode.centralbankparser.data.entities.ParticipantInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantInfoService implements ServiceDataInterface<ParticipantInfoEntity> {
    @Autowired
    private ParticipantInfoDao dao;

    @Override
    public List<ParticipantInfoEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(ParticipantInfoEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(ParticipantInfoEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public ParticipantInfoEntity findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}

