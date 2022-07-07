package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.AccRstrListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccRstrListEntityRepository extends CrudRepository<AccRstrListEntity, Long> {
}