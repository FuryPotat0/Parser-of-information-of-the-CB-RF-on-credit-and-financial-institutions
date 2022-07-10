package com.opencode.centralbankparser.references.services;

import com.opencode.centralbankparser.references.daos.ParticipantStatusDao;
import com.opencode.centralbankparser.references.entities.ParticipantStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantStatusService implements ServiceReferencesInterface<ParticipantStatusEntity> {
    @Autowired
    private ParticipantStatusDao dao;

    @Override
    public List<ParticipantStatusEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(ParticipantStatusEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(ParticipantStatusEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        dao.delete(id);
    }

    @Override
    public Optional<ParticipantStatusEntity> findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public ParticipantStatusEntity findById(Long id) {
        return dao.findById(id);
    }
}

