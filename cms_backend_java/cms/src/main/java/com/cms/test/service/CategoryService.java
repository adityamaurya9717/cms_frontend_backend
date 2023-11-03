package com.cms.test.service;

import com.cms.test.constant.CategoryLevel;
import com.cms.test.dto.request.AddCategoryRequest;
import com.cms.test.dto.request.GetCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    ResponseEntity<?> getCategory(GetCategoryRequest addCategoryRequest);
    ResponseEntity<?> addCategory(AddCategoryRequest addCategoryRequest);

    ResponseEntity<?> getAllLevel3Category(CategoryLevel categoryLevel);

}
