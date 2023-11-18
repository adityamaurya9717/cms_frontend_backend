package com.cms.test.dto.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class MapRoleRequest {

    @NotNull(message = "userId cannot be null")
    @Positive(message = "userID always be positive")
    private Long userId;
    // send All The RoleId which to Active
    @Valid
    @NotNull(message = "roleIds cannot be null")
    private List<Long>  roleIds;


}
