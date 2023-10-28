package com.cms.test.service;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.GetProductRequest;
import com.cms.test.dto.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ResponseEntity<?> addProduct(AddProductRequest addProductRequest);
    ResponseEntity<?> updateProduct(UpdateProductRequest addProductRequest);

    ResponseEntity<?> getProducts(GetProductRequest getProductRequest);
}
