package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.AccountStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusEntityRepository extends CrudRepository<AccountStatusEntity, Long> {
}