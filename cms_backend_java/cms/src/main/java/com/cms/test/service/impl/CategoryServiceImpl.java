package com.cms.test.service.impl;

import com.cms.test.constant.CategoryLevel;
import com.cms.test.dto.request.AddCategoryRequest;
import com.cms.test.mongodocument.CategoryDocument;
import com.cms.test.mongorepo.CategoryDocumentRepo;
import com.cms.test.mongorepo.CounterDocumentRepo;
import com.cms.test.service.CategoryService;
import com.cms.test.utils.CategoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CategoryDocumentRepo categoryDocumentRepo;

    @Autowired
    private CounterDocumentRepo counterDocumentRepo;

    @Autowired
    private CategoryUtil categoryUtil;
    @Override
    public ResponseEntity<?> addCategory(AddCategoryRequest addCategoryRequest) {

        // Its a LEVEL one category
        if(CategoryLevel.LEVEL_ONE.getLevel() == addCategoryRequest.getCategoryLevel().getLevel()){
            CategoryDocument categoryDocument = new CategoryDocument(addCategoryRequest);
            categoryDocument.setCategoryId("CAT1000"+categoryUtil.CategoryIdMaker());
            categoryDocument = categoryDocumentRepo.save(categoryDocument) ;
            return ResponseEntity.ok(categoryDocument);
        }
        return null;
    }

    public void addCategoryDocument(AddCategoryRequest addCategoryRequest){

    }
}
