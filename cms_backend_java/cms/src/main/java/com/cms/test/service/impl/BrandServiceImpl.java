package com.cms.test.service.impl;

import com.cms.test.dto.request.AddBrandRequest;
import com.cms.test.dto.request.GetBrandRequest;
import com.cms.test.dto.response.BrandInfoResponse;
import com.cms.test.dto.response.BrandResponse;
import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongorepo.BrandDocumentRepo;
import com.cms.test.service.BrandService;

import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private BrandDocumentRepo brandDocumentRepo;

    /**
     *  method to add a brand
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public ResponseEntity<?> addBrand(AddBrandRequest request, HttpServletRequest httpServletRequest) {
        try {
            BrandDocument brandDocument = new BrandDocument(request);
            brandDocument = brandDocumentRepo.save(brandDocument);
            brandDocument.setBrandId(brandDocument.getId());
            brandDocument = brandDocumentRepo.save(brandDocument);
            return ResponseEntity.ok(brandDocument);

        }
        catch (MongoWriteException mongoWriteException){
            return ResponseEntity.badRequest().body(mongoWriteException.toString());
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.toString());
        }

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
/*
    {"brandName":{"$regex":"Nokia.*","$options":'i'},"active":true}

    { "$or" : [ "$and" :{} ] }

 */
    @Override
    public ResponseEntity<?> getBrands(GetBrandRequest request, HttpServletRequest httpServletRequest) {
        List<Criteria> criteria = new ArrayList<>();
        Query query = new Query();
        if(!ObjectUtils.isEmpty(request.getBrandName())){
            Criteria criteria1   =  Criteria.where("brandName")
                     .regex(request.getBrandName() +".*","i");
            criteria.add(criteria1);
        }
        if(!ObjectUtils.isEmpty(request.getActive())){
            Criteria criteria1 = Criteria.where("active")
                    .is(request.getActive());
            criteria.add(criteria1);
        }
         Criteria BrandCri = Criteria.where("brandId").is(request.getBrandId());
        //query.addCriteria(new Criteria().orOperator(new Criteria().andOperator(criteria),BrandCri));
        if(criteria.size()>0){
            query.addCriteria(new Criteria().andOperator(criteria));
        }

        long totalDocs  =  mongoTemplate.count(query,BrandDocument.class);
        //int offset = (request.getPageNo()-1) * request.getSize();
        query.with(PageRequest.of(request.getPageNo(),request.getSize()));
//        List<Document> documents = new ArrayList<>();
//         mongoTemplate.executeQuery(query, "brand", new DocumentCallbackHandler() {
//             @Override
//             public void processDocument(Document document) throws MongoException, DataAccessException {
//                documents.add(document);
//             }
//         });
        BasicQuery basicQuery = new BasicQuery(query.getQueryObject(), query.getFieldsObject());
        List<BrandDocument> brandDocuments  = mongoTemplate.find(query,BrandDocument.class);
        List<BrandInfoResponse> brandInfoResponse = brandDocuments.stream().map(data-> new BrandInfoResponse(data)).collect(Collectors.toList());
        BrandResponse brandResponse = new BrandResponse(request.getPageNo(),totalDocs,brandInfoResponse);
        log.info("query={}",query.getQueryObject().toJson());
        
        return ResponseEntity.ok(brandResponse);
    }

    /**
     *  method to getting All Brands
     * @param httpServletRequest
     * @return
     */
    @Override
    public ResponseEntity<?> getAllBrands(HttpServletRequest httpServletRequest) {
        List<BrandDocument> brandDocumentList   =  brandDocumentRepo.findAll();

        List<BrandInfoResponse> brandInfoRespons = new ArrayList<>();
        for(BrandDocument brandDocument : brandDocumentList){
            BrandInfoResponse brandInfoResponse = new BrandInfoResponse();
            BeanUtils.copyProperties(brandDocument, brandInfoResponse);
            brandInfoRespons.add(brandInfoResponse);
        }
        return ResponseEntity.ok(brandInfoRespons);
    }
}
