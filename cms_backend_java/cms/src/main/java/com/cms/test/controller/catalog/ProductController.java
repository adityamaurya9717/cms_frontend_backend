package com.cms.test.controller.catalog;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.GetProductRequest;
import com.cms.test.dto.request.UpdateProductRequest;
import com.cms.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);

    }
    @PostMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateProductRequest){
        return productService.updateProduct(updateProductRequest);
    }
    @PostMapping("/update-product-price")
    public ResponseEntity<?> updateProductPrice(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }
    @PostMapping("/get-product")
    public ResponseEntity<?> getProduct(@RequestBody GetProductRequest getProductRequest){
        return productService.getProducts(getProductRequest);
    }

}
