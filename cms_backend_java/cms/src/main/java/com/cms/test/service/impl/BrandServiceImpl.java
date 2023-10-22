package com.cms.test.service.impl;

import com.cms.test.dto.request.AddBrandRequest;
import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongorepo.BrandDocumentRepo;
import com.cms.test.service.BrandService;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDocumentRepo brandDocumentRepo;
    @Override
    public ResponseEntity<?> addBrand(AddBrandRequest request, HttpServletRequest httpServletRequest) {
        BrandDocument brandDocument = new BrandDocument(request);
            brandDocument = brandDocumentRepo.save(brandDocument);
            brandDocument.setBrandId(brandDocument.getId());
            brandDocument = brandDocumentRepo.save(brandDocument);
            return ResponseEntity.ok(brandDocument);
    }

    @Override
    public ResponseEntity<?> updateBrand(AddBrandRequest request, HttpServletRequest httpServletRequest) {
       Optional<BrandDocument> optional = brandDocumentRepo.findById(request.getBrandId());
       if( !optional.isPresent() ){
           return ResponseEntity.badRequest().body("BrandId not Found cannot able to Update");

       }
        BrandDocument brandDocument = optional.get();
        brandDocument.setActive(request.isActive());
        brandDocument.setDescription(ObjectUtils.isEmpty(request.getDescription())?brandDocument.getDescription() : request.getDescription());
        brandDocument = brandDocumentRepo.save(brandDocument);
        return ResponseEntity.ok(brandDocument);

    }
}
