package com.cms.test.dto.request;

import lombok.Data;

@Data
public class AddBrandRequest {
    private String brandId;
    private String brandName;
    public  String description;
    public  String brandCountry;
    public boolean active = true;

}
