package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.data.entities.SwbicsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwbicsEntityRepository extends CrudRepository<SwbicsEntity, Long> {
}