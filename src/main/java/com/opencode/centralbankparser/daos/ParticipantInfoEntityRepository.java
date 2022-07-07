package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.ParticipantInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoEntityRepository extends CrudRepository<ParticipantInfoEntity, Long> {
}