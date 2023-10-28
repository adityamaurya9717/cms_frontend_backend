package com.cms.test.service.impl;

import com.cms.test.dto.request.AddProductRequest;
import com.cms.test.dto.request.GetProductRequest;
import com.cms.test.dto.request.UpdateProductRequest;
import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongodocument.ProductDocument;
import com.cms.test.mongorepo.BrandDocumentRepo;
import com.cms.test.mongorepo.ProductDocumentRepo;
import com.cms.test.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDocumentRepo productDocumentRepo;
    @Autowired
    private BrandDocumentRepo brandDocumentRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    @Override
    public ResponseEntity<?> getProducts(GetProductRequest getProductRequest) {

        Query query = new Query();
        if(!ObjectUtils.isEmpty(getProductRequest.getProductId())){
            query.addCriteria(Criteria.where("productId").is(getProductRequest.getProductId()));
        }
        if(!ObjectUtils.isEmpty(getProductRequest.getBrandId())){
            query.addCriteria(Criteria.where("brandId").is(getProductRequest.getBrandId()));
        }
        if(!ObjectUtils.isEmpty(getProductRequest.getCategoryCode())){
            query.addCriteria(Criteria.where("categoryCode").is(getProductRequest.getCategoryCode()));
        }
        query.with(PageRequest.of(getProductRequest.getPageNo(),getProductRequest.getSize()));
        List<ProductDocument> productDocuments = mongoTemplate.find(query,ProductDocument.class);

        return ResponseEntity.ok(productDocuments);
    }
}
