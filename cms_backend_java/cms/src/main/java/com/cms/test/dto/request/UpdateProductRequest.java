package com.cms.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UpdateProductRequest {
    @NotEmpty(message = "id cannot be Empty")
    public String id;
    public String productId;
    public String categoryId;
    public String productName;
    public String productDescription;
    public String brandId;
    public List<String> countriesAvailableIn;
    public ProductAttributes productAttributes;
    public ProductPrice productPrice;
    private boolean active;


}
