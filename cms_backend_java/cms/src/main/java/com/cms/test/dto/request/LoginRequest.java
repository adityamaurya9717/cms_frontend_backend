package com.cms.test.dto.request;


import com.cms.test.validation.ToJson;
import lombok.Data;

@Data
@ToJson
public class LoginRequest {

    private String email;
    private String password;

}
