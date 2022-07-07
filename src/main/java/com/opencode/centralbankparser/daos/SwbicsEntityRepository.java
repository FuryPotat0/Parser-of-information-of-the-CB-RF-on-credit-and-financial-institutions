package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.SwbicsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwbicsEntityRepository extends CrudRepository<SwbicsEntity, Long> {
}