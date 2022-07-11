package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.AccRstrEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccRstrEntityRepository extends CrudRepository<AccRstrEntity, Long> {
}