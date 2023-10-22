package com.cms.test.mongodocument;
import com.cms.test.dto.request.AddBrandRequest;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "brand")
public class BrandDocument {

    @MongoId
    private String id;

    @Field(name = "brandId")
    private String brandId;

    @Field(name = "brandName")
    @Indexed(unique = true)
    private String brandName;

    @Field(name = "active")
    private boolean active;

    private String description;

    public BrandDocument(AddBrandRequest request){
        this.brandName = request.getBrandName().trim();
        this.description = request.getDescription();
        this.active = true;
    }


}
