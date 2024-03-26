package com.cms.test.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class AuditModel {

    @Column(name = "created_by")
    private String createdBy;
    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private Date createdAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_at")
    private Date updatedAt;
}
