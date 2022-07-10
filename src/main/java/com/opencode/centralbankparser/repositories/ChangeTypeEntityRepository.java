package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.ChangeTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeTypeEntityRepository extends CrudRepository<ChangeTypeEntity, Long> {
}