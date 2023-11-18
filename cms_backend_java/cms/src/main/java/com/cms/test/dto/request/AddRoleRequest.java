package com.cms.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddRoleRequest {

    @NotEmpty(message = "roleName cannot be Empty")
    private String RoleName;
}
