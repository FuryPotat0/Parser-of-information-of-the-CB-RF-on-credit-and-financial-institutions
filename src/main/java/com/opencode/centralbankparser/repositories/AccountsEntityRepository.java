package com.opencode.centralbankparser.repositories;

import com.opencode.centralbankparser.data.entities.AccountsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsEntityRepository extends CrudRepository<AccountsEntity, Long> {
}