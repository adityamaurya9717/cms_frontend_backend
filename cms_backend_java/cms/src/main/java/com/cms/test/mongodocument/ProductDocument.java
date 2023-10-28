package com.cms.test.mongodocument;


import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.ProductPrice;
import com.cms.test.dto.request.UpdateProductRequest;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductDocument {

    @MongoId
    private String id;

    @Field(name = "productId")
    @Indexed(unique = true)
    private String productId;

    @Field(name = "productName")
    private String productName;

    @Field(name = "description")
    private String description;

    @Field(name = "brandId",targetType = FieldType.STRING)
    private String brandId;

    private List<String> countriesAvailableIn = new ArrayList<>();

    @Field(name = "categoryName")
    private String categoryName;

    @Field(name = "categoryCode")
    private String categoryCode="";

    @Field(name = "active",targetType = FieldType.BOOLEAN)
    private boolean active;

    @DBRef
    private BrandDocument brandDetail;

    private ProductPrice productPrice;



    public  ProductDocument(AddProductRequest request){
        this.productId = request.getProductId();
        this.productName = request.getProductName();
        this.description = request.getProductDescription();
        this.categoryCode = request.getCategoryId();
        this.productPrice = request.getProductPrice();
        this.brandId = request.getBrandId();
        this.countriesAvailableIn = request.getCountriesAvailableIn();
        this.active = true;
    }

    // for product Update
    public void updateProduct(UpdateProductRequest updateProductRequest){
        this.productName = updateProductRequest.getProductName();
        this.active = updateProductRequest.isActive();
        this.description = updateProductRequest.getProductDescription();
        this.categoryCode = updateProductRequest.getCategoryId();
        this.productPrice = updateProductRequest.getProductPrice();
        this.brandId = updateProductRequest.getBrandId();

    }



}
