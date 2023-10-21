package com.cms.test.service.impl;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {
        return null;
    }
}
