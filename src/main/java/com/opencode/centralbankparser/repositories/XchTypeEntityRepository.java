package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.XchTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XchTypeEntityRepository extends CrudRepository<XchTypeEntity, Long> {
}