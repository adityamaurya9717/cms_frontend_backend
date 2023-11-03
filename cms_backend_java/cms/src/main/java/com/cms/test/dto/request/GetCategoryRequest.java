package com.cms.test.dto.request;

import com.cms.test.constant.CategoryLevel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetCategoryRequest {
    private String parentId;
    private String categoryId;
    private String categoryName;
    private Boolean active;
    private CategoryLevel categoryLevel;
    private String categoryDescription;

    private LocalDate from;

    private int pageNo;
    private int size;
}
