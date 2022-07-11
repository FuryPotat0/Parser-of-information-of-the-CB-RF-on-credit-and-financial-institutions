package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.data.entities.ParticipantInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoEntityRepository extends CrudRepository<ParticipantInfoEntity, Long> {
}