package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.entities.Ed807Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ed807EntityRepository extends CrudRepository<Ed807Entity, Long> {
}