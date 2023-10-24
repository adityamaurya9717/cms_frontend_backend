package com.cms.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateProductRequest {
    @NotEmpty(message = "productId cannot be Empty")
    public String productId;
    public String categoryId;
    public String productName;
    public String productDescription;
    public String brandId;
    public ProductAttributes productAttributes;
    public ProductPrice productPrice;
    private boolean active;


}
