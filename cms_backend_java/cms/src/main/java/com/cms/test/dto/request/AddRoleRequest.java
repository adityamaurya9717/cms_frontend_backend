package com.cms.test.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddRoleRequest {

    private String roleName;
    private List<String> authorityList;
}
