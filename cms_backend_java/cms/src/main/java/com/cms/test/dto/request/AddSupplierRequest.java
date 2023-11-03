package com.cms.test.dto.request;


import lombok.Data;

@Data
public class AddSupplierRequest {

    private String firstName;
    private String lastName;
    private String companyName;
    private String gstNo;
    private String email;
    private String mobile;
    private String alternativeMobileTwo;
    private AddSupplierAddress address;

}
