package com.cms.test.service;

import com.cms.test.dto.request.AddCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    ResponseEntity<?> addCategory(AddCategoryRequest addCategoryRequest);
}
