package com.cms.test.mongodocument;


import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.ProductPrice;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

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

    @Field(name = "categoryName")
    private String categoryName;

    @Field(name = "categoryCode")
    private String categoryCode="";

    @DBRef
    private BrandDocument brandDetail;

    private ProductPrice productPrice;



    public  ProductDocument(AddProductRequest request){
        this.productId = request.getProductId();
        this.productName = request.getProductName();
        this.description = request.getProductDescription();
        this.categoryCode = request.categoryId;
        this.productPrice = request.getProductPrice();
    }



}
