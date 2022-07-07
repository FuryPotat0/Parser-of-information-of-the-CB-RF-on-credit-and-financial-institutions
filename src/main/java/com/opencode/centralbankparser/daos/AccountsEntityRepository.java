package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.AccountsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsEntityRepository extends CrudRepository<AccountsEntity, Long> {
}