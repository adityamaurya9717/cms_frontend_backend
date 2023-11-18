package com.cms.test.jpa.entity;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
@EntityListeners(AuditingEntityListener.class)
public class UserRoleMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_user_id",nullable = false)
    private Long userId;

    @Column(name = "fk_role_id",nullable = false)
    private Long roleId;

    @Column(name = "has_write_access")
    private Integer hasWriteAcces=1;

    @Column(name = "active")
    private Integer active=1;

    public UserRoleMappingEntity(Long userId , Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }


}
