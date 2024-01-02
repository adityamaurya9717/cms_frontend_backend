package com.cms.test.jpa.repository;

import com.cms.test.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

  Optional<RoleEntity> findByRoleName(String roleName);
  @Query(value = " SELECT r FROM RoleEntity r WHERE LOWER(TRIM(roleName)) = LOWER(TRIM(?1))")
  Optional<RoleEntity> findByRoleNameCaseInsenstive(String roleName);



}
