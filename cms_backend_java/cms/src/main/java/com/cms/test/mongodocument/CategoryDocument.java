package com.cms.test.mongodocument;

import com.cms.test.constant.CategoryLevel;
import com.cms.test.dto.request.AddCategoryRequest;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class CategoryDocument {

    @MongoId
    private String id;

    @Field(name = "parentId")
    private String parentId;

    @Field(name = "categoryId")
    @Indexed(unique = true)
    private String categoryId;

    @Field(name = "categoryName")
    @Indexed(unique = true)
    private String categoryName;

    @Field(name = "categoryLevel")
    private Integer categoryLevel;

    @Field(name = "createdOn")
    private LocalDateTime createdOn;

    @Field(name = "updatedOn")
    private LocalDateTime updatedOn;

    @Field(name = "active")
    private boolean active;

    private String description;

    public CategoryDocument(AddCategoryRequest addCategoryRequest){
        this.categoryName = addCategoryRequest.getCategoryName();
        this.parentId = null;
        this.active=true;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
        this.description = addCategoryRequest.getCategoryDescription();
    }
}
