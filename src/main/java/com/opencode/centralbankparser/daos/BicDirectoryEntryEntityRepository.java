package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.entities.BicDirectoryEntryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicDirectoryEntryEntityRepository extends CrudRepository<BicDirectoryEntryEntity, Long> {
}