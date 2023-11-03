package com.cms.test.dto.response;

import com.cms.test.constant.CategoryLevel;
import com.cms.test.mongodocument.CategoryDocument;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryInfoResponse {
    private String parentId;
    private String categoryId;
    private String categoryName;
    private boolean active;
    private CategoryLevel categoryLevel;
    private String categoryDescription;

    public CategoryInfoResponse(CategoryDocument data) {
        BeanUtils.copyProperties(data,this);
    }
}
