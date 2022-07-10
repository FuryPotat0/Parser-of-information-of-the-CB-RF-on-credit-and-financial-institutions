package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.RstrEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RstrEntityRepository extends CrudRepository<RstrEntity, Long> {
}