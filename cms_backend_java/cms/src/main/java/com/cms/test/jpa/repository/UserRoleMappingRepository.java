package com.cms.test.jpa.repository;

import com.cms.test.jpa.entity.UserRoleMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMappingEntity,Long> {

    public List<UserRoleMappingEntity> findByUserId(Long id);
}
