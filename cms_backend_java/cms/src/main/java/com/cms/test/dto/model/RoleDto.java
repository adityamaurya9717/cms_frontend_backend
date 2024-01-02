package com.cms.test.dto.model;

import com.cms.test.jpa.entity.AuthorityEntity;
import com.cms.test.jpa.entity.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    private Long roleId;
    @NotEmpty(message = "RoleName cannot Be Empty")
    private String roleName;
    @Valid
    private List<AuthorityEntity> authority = new ArrayList<>();

    public RoleDto(RoleEntity roleEntity){
        this.roleId = roleEntity.getId();
        this.roleName = roleEntity.getRoleName();
        if(roleEntity.getAuthorityEntitySet()!=null){
            this.authority.addAll(roleEntity.getAuthorityEntitySet());
        }
    }
}
