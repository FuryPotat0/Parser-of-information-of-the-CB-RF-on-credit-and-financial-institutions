package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.RegulationAccountTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationAccountTypeEntityRepository extends CrudRepository<RegulationAccountTypeEntity, Long> {
}