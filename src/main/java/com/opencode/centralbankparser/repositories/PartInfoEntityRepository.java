package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.PartInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartInfoEntityRepository extends CrudRepository<PartInfoEntity, Long> {
}