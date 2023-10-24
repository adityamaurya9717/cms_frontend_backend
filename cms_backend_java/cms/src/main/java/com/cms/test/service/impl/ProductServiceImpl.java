package com.cms.test.service.impl;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.UpdateProductRequest;
import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongodocument.ProductDocument;
import com.cms.test.mongorepo.BrandDocumentRepo;
import com.cms.test.mongorepo.ProductDocumentRepo;
import com.cms.test.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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

    @Override
    public ResponseEntity<?> updateProduct(UpdateProductRequest updateProductRequest) {
        Optional<ProductDocument> optional   = productDocumentRepo.findById(updateProductRequest.getProductId());
        if(!optional.isPresent()){
            return ResponseEntity.badRequest().body("product Not found for Update");
        }
        BrandDocument brandDocument  = brandDocumentRepo.findById(updateProductRequest.getBrandId())
                .orElseThrow(()->new RuntimeException("brand Detail not found"));

        ProductDocument productDocument = optional.get();
        productDocument.updateProduct(updateProductRequest);
        productDocument.setBrandDetail(brandDocument);
        productDocument =  productDocumentRepo.save(productDocument);
        return ResponseEntity.ok(productDocument) ;
    }
}
