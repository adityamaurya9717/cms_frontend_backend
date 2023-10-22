package com.cms.test.service.impl;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongodocument.ProductDocument;
import com.cms.test.mongorepo.BrandDocumentRepo;
import com.cms.test.mongorepo.ProductDocumentRepo;
import com.cms.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDocumentRepo productDocumentRepo;

    @Autowired
    private BrandDocumentRepo brandDocumentRepo;

    @Override
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {
        Optional<BrandDocument> optional = brandDocumentRepo.findById(addProductRequest.getBrandId());
        if( !optional.isPresent() ){
            return ResponseEntity.badRequest().body("Brand Not Present For Mapping for this Product");
        }
        ProductDocument productDocument = new ProductDocument(addProductRequest);
        productDocument.setBrandDetail(optional.get());
        productDocument =  productDocumentRepo.save(productDocument);
        return ResponseEntity.ok(productDocument);
    }
}
