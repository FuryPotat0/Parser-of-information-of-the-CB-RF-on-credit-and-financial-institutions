package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.SrvcsEntity;
import org.springframework.data.repository.CrudRepository;

public interface SrvcsEntityRepository extends CrudRepository<SrvcsEntity, Long> {
}