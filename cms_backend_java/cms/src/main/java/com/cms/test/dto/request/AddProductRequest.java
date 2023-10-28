package com.cms.test.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddProductRequest {

    private String productId;
    private String categoryId;
    private String productName;
    private String productDescription;
    private String brandId;
    private List<String> countriesAvailableIn;
    private ProductAttributes productAttributes;
    private ProductPrice productPrice;


}
