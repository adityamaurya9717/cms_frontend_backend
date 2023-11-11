package com.cms.test.dto.request;

import lombok.Data;

@Data
public class AddCustomerRequest {

    private String firstName;
    private String lastName;
    private String  email;
    private String password;
    private String gender;
    private String phone;
    private String designation;


}
