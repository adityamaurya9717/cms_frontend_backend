package com.cms.test.jpa.entity;


import com.cms.test.dto.request.AddCustomerRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "original_password", nullable = false)
    private String originalPassword;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dob")
    private String dob;

    @Column(name = "designation")
    private String designation;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "active")
    private Boolean active = true;


    public  UserEntity(AddCustomerRequest request,String encodedPassword){
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.gender= request.getGender();
        this.designation = request.getDesignation();
        this.active = true;
        this.password = encodedPassword;
        this.originalPassword = request.getPassword();
    }

}
