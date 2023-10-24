package com.cms.test.dto.request;

import lombok.Data;

@Data
public class AddProductRequest {

    private String productId;
    private String categoryId;
    private String productName;
    private String productDescription;
    private String brandId;
    private ProductAttributes productAttributes;
    private ProductPrice productPrice;


}
