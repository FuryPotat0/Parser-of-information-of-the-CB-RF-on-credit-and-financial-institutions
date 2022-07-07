package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.RstrEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RstrEntityRepository extends CrudRepository<RstrEntity, Long> {
}