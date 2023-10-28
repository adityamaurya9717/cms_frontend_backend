package com.cms.test.controller;


import com.cms.test.dto.request.AddCategoryRequest;
import com.cms.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequest){
      return categoryService.addCategory(addCategoryRequest);
    }

}
