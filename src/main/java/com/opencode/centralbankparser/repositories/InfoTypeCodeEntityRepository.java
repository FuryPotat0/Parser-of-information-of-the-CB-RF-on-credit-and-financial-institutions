package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.InfoTypeCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTypeCodeEntityRepository extends CrudRepository<InfoTypeCodeEntity, Long> {
}