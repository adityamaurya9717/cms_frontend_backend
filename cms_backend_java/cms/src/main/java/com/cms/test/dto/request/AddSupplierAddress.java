package com.cms.test.dto.request;


import lombok.Data;

@Data
public class AddSupplierAddress {

    private String country;
    private String state;
    private String address;
    private String pincode;
}
