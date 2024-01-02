package com.cms.test.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@DynamicUpdate
@Data
@EntityListeners(AuditingEntityListener.class)
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority_name")
    private String authorityName;
}
