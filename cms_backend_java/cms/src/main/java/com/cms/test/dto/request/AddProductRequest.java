package com.cms.test.dto.request;

import lombok.Data;

@Data
public class AddProductRequest {

    public String productId;
    public String categoryId;
    public String productName;
    public String productDescription;
    public String brandId;
}
