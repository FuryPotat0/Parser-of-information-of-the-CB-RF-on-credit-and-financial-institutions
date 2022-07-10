package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.PtTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PtTypeEntityRepository extends CrudRepository<PtTypeEntity, Long> {
}