package com.cms.test.service.impl;

import com.cms.test.constant.CategoryLevel;
import com.cms.test.dto.ResponseModel;
import com.cms.test.dto.request.AddCategoryRequest;
import com.cms.test.dto.request.GetCategoryRequest;
import com.cms.test.dto.response.BrandInfoResponse;
import com.cms.test.dto.response.CategoryInfoResponse;
import com.cms.test.dto.response.CategoryResponse;
import com.cms.test.mongodocument.CategoryDocument;
import com.cms.test.mongorepo.CategoryDocumentRepo;
import com.cms.test.mongorepo.CounterDocumentRepo;
import com.cms.test.service.CategoryService;
import com.cms.test.utils.CategoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getCategory(GetCategoryRequest addCategoryRequest) {
        Query query = new Query();
        if( !ObjectUtils.isEmpty(addCategoryRequest.getCategoryName())  && addCategoryRequest.getCategoryName().startsWith("CAT")){
            query.addCriteria(Criteria.where("categoryId").is(addCategoryRequest.getCategoryId()));
        }
        if(null!=addCategoryRequest.getActive()){
            query.addCriteria(Criteria.where("active").is(addCategoryRequest.getActive()));
        }
        if(null!=addCategoryRequest.getCategoryLevel()){
            query.addCriteria(Criteria.where("categoryLevel").is(addCategoryRequest.getCategoryLevel().getLevel()));
        }
        if(!ObjectUtils.isEmpty(addCategoryRequest.getCategoryName()) && !addCategoryRequest.getCategoryName().startsWith("CAT")){
            query.addCriteria(Criteria.where("categoryName").regex(addCategoryRequest.getCategoryName()+".*","i"));
        }
        query.with(PageRequest.of(addCategoryRequest.getPageNo(),addCategoryRequest.getSize()));

        long totalDocs  = mongoTemplate.count(query,CategoryDocument.class);
        List<CategoryDocument> documents  = mongoTemplate.find(query,CategoryDocument.class);
        List<CategoryInfoResponse> infoResponses = documents.stream().map(data->new CategoryInfoResponse(data)).collect(Collectors.toList());
        CategoryResponse categoryResponse = new CategoryResponse(addCategoryRequest.getPageNo(),totalDocs,infoResponses);

        ResponseModel responseModel = new ResponseModel(HttpStatus.OK,categoryResponse).success("success Fully Fetch");
        return ResponseEntity.ok(responseModel);
    }

    @Override
    public ResponseEntity<?> addCategory(AddCategoryRequest addCategoryRequest) {
        CategoryDocument categoryDocument = new CategoryDocument(addCategoryRequest);

        // Its a LEVEL one category
        if(CategoryLevel.LEVEL_ONE.getLevel() == addCategoryRequest.getCategoryLevel().getLevel()){
            categoryDocument.setCategoryId("CAT1000"+categoryUtil.CategoryIdMaker());
            categoryDocument.setCategoryLevel(CategoryLevel.LEVEL_ONE.getLevel());
        }
        if(CategoryLevel.LEVEL_TWO.getLevel() == addCategoryRequest.getCategoryLevel().getLevel()){
            categoryDocument.setCategoryId("CAT2000"+categoryUtil.CategoryIdMaker());
            categoryDocument.setCategoryLevel(CategoryLevel.LEVEL_TWO.getLevel());
        }
        if(CategoryLevel.LEVEL_THREE.getLevel() == addCategoryRequest.getCategoryLevel().getLevel()){
            categoryDocument.setCategoryId("CAT3000"+categoryUtil.CategoryIdMaker());
            categoryDocument.setCategoryLevel(CategoryLevel.LEVEL_THREE.getLevel());
        }
        categoryDocument = categoryDocumentRepo.save(categoryDocument) ;
        ResponseModel responseModel = new ResponseModel(HttpStatus.OK,categoryDocument).success("Add Category SuccessFullt");
        return ResponseEntity.ok(responseModel);
    }

    @Override
    public ResponseEntity<?> getAllLevel3Category(CategoryLevel categoryLevel) {
        return ResponseEntity.ok(categoryDocumentRepo.getLevelThreeCategory(categoryLevel.getLevel()));
    }

}
