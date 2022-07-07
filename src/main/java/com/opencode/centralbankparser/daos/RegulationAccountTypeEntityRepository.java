package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.RegulationAccountTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationAccountTypeEntityRepository extends CrudRepository<RegulationAccountTypeEntity, Long> {
}