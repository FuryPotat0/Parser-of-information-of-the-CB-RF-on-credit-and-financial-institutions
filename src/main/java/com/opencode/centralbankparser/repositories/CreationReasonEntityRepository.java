package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.CreationReasonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreationReasonEntityRepository extends CrudRepository<CreationReasonEntity, Long> {
}