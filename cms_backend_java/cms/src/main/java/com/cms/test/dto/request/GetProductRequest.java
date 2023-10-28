package com.cms.test.dto.request;


import lombok.Data;

@Data
public class GetProductRequest {

    private String productId;
    private String brandId;
    private String country;
    private String categoryCode;
    private String from;
    private int pageNo;
    private int size;

}
