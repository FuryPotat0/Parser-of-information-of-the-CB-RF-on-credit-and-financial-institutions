package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.AccRstrEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccRstrEntityRepository extends CrudRepository<AccRstrEntity, Long> {
}