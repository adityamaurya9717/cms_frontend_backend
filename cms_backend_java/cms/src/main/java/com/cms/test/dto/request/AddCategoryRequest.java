package com.cms.test.dto.request;

import com.cms.test.constant.CategoryLevel;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;


@Data
public class AddCategoryRequest {
    private String parentId;

    private String categoryId;
    private String categoryName;

    private boolean active;

    private CategoryLevel categoryLevel;
    private String description;
}
