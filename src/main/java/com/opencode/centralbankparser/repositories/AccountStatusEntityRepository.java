package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.references.entities.AccountStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusEntityRepository extends CrudRepository<AccountStatusEntity, Long> {
}