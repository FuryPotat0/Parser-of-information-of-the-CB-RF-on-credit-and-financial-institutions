package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.InitialEdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialEdEntityRepository extends CrudRepository<InitialEdEntity, Long> {
}