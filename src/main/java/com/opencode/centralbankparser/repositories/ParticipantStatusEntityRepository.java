package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.ParticipantStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantStatusEntityRepository extends CrudRepository<ParticipantStatusEntity, Long> {
}