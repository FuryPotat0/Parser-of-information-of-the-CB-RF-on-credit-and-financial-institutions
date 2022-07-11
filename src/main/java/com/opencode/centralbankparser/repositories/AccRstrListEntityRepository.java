package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.data.entities.AccRstrListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccRstrListEntityRepository extends CrudRepository<AccRstrListEntity, Long> {
}