package com.cms.test.jpa.entity;

import com.cms.test.config.listener.AuditListener;
import com.cms.test.dto.model.RoleDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
@DynamicUpdate
@Data
@EntityListeners(AuditListener.class)
@NoArgsConstructor
public class RoleEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable( name = "role_authority",
            joinColumns = @JoinColumn(name = "fk_role_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_authority_id"))
    Set<AuthorityEntity> authorityEntitySet;


    public RoleEntity(RoleDto roleDto){
        this.roleName = roleDto.getRoleName().trim();
    }

    @PrePersist
    private void toUpperCase(){
        Objects.requireNonNull(this.roleName,"roleName cannot be null");
        this.roleName = this.roleName.toUpperCase();
    }
}
