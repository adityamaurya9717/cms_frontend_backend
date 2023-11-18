package com.cms.test.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
@DynamicUpdate
@Data
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @PrePersist
    private void toUpperCase(){
        Objects.requireNonNull(this.roleName,"roleName cannot be null");
        this.roleName = this.roleName.toUpperCase();
    }
}
