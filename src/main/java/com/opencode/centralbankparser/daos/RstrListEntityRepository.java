package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.RstrListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RstrListEntityRepository extends CrudRepository<RstrListEntity, Long> {
}