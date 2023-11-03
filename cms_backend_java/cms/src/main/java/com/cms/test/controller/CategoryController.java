package com.cms.test.controller;


import com.cms.test.constant.CategoryLevel;
import com.cms.test.dto.request.AddCategoryRequest;
import com.cms.test.dto.request.GetCategoryRequest;
import com.cms.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/get-category")
    public ResponseEntity<?> getCategory(@RequestBody GetCategoryRequest request){
        return categoryService.getCategory(request);
    }

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequest){
      return categoryService.addCategory(addCategoryRequest);
    }

    @GetMapping("/get-all-category-bylevel")
    public ResponseEntity<?> getAllLevelThreeCategory(@RequestParam(value = "categoryLevel") CategoryLevel categoryLevel){
        return categoryService.getAllLevel3Category(categoryLevel);
    }

}
