package com.cms.test.controller;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);

    }
    @PostMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }
    @PostMapping("/update-product-price")
    public ResponseEntity<?> updateProductPrice(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }

}
