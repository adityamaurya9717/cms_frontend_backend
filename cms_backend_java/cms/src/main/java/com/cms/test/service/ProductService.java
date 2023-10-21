package com.cms.test.service;

import com.cms.test.dto.request.AddProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ResponseEntity<?> addProduct(AddProductRequest addProductRequest);
}
