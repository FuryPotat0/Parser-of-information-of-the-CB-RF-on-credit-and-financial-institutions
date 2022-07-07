package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.XchTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XchTypeEntityRepository extends CrudRepository<XchTypeEntity, Long> {
}