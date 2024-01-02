package com.cms.test.jpa.repository;

import com.cms.test.jpa.entity.AuthorityEntity;
import com.cms.test.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity,Long> {

    @Query(value = " SELECT a FROM AuthorityEntity a WHERE LOWER(TRIM(authorityName)) = LOWER(TRIM(?1))")
    Optional<AuthorityEntity> findByAuthorityNameCaseInsenstive(String authorityName);

}
